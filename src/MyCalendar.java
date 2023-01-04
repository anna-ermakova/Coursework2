import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import static com.sun.beans.introspect.PropertyInfo.Name.description;
public class MyCalendar {
    private static final Map<Integer,Repeatable> actualTasks=new HashMap<>();
    private static final Map<Integer,Repeatable>archivedTasks=new HashMap<>();

    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.print("Введите название задачи");
            String title = ValidateUtils.checkString(scanner.nextLine());
            System.out.print("Введите описание задачи");
            String description = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите тип задачи:  0-рабочая, 1-личная");
            TaskType taskType = TaskType.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи: 0-однократная, 1-ежедневная, 2-еженедльная, 3-ежемесячная, 4-ежегодная");
            int occurrence = scanner.nextInt();
            System.out.println("Введите дату в формате хх.xx.xxxx");
            scanner.nextLine();
            createEvent(scanner, title, description, taskType, occurrence);
            System.out.println("Для выхода нажмите Enter\n");
            scanner.nextLine();
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createEvent(Scanner scanner, String title, String description, TaskType taskType, int occurrence) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("xx.xx.xxxx"));
            Repeatable task = null;
            try {
                task = createTask(occurrence, title, description, taskType, eventDate);
                System.out.printf("Создана задача " + task);
            } catch (WrongInputException e) {
                System.out.println(e.getMessage());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте правильность введения даты хх.xx.xxxx");
            createEvent(scanner, title, description, taskType, occurrence);
        }
    }
    public static void editTask(Scanner scanner) {
        try {
            System.out.println("Редактирование задачи: введите id");
            printAcualTasks();
            int id = scanner.nextInt();
            if (!actualTasks.containsKey(id)) {
                throw new WrongInputException("Задача не найдена");
            }
            System.out.println("Редактирование 0-заголовок, 1-описание, 2-тип, 3-дата");
            int menuCase=scanner.nextInt();
            switch (menuCase) {
                case 0 -> {
                    scanner.nextLine();
                    System.out.println("Введите название задачи");

                }
            }
        }
    }

    private static List<Repeatable> findTasksByDate(LocalDate date) {
        List<Repeatable> tasks = new ArrayList<>();
        for (Repeatable task:actualTasks.values()) {
            if (task.checkOccurrence(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
    } return tasks;

    private static Repeatable createTask(int occurrence, String title, String description, TaskType taskType, LocalDateTime localDateTime) throws WrongInputException{
        return switch (occurrence){
            case 0 -> {
                OncelyTask oncelyTask = new OncelyTask(title, description, taskType, localDateTime);
                actualTasks.put(oncelyTask.getId(), oncelyTask);
                yield oncelyTask;
            }
            case 1 -> {
                DailyTask task=new DailyTask(title, description, taskType, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 2 -> {
                WeeklyTask task = new WeeklyTask(title, description, taskType, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 3 -> {
                MonthlyTask task = new MonthlyTask(title, description, taskType, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 4 -> {
                YearlyTask task=new YearlyTask(title, description, taskType, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            default -> null;
        };
    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи\n");
    }

    public static void getTasksByDay(Scanner scanner) {
        System.out.println("введите дату в формате хх.хх.хххх:");
        try {
            String date = scanner.next();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("хх.хх.хххх");
            LocalDate requestedDate = LocalDate.parse(date, dateFormatter);
            List<Repeatable> foundEvents = findTasksByDate(requestedDate);
            System.out.println("События на " + requestedDate + ":");
            for (Repeatable task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат даты хх.хх.хххх и попробуйте еще раз");
        }
        scanner.nextLine();
        System.out.println("Для ыхода нажмите Enter\n");
    }

}
