import java.time.LocalDateTime;
import java.util.Locale;

public abstract class Task {
    private String title;
    private String description;
    private TaskType taskType;
    private LocalDateTime firstDate;
    private boolean archived;
    private static Integer counter = 1;
    private final Integer id;

    public Task(String title, String description, TaskType taskType, LocalDateTime firstDate) throws WrongInputException {
        this.title = ValidateUtils.checkString(title);
        this.description = ValidateUtils.checkString(description);
        this.taskType = taskType;
        this.firstDate = firstDate;
        this.archived = false;
        id = counter++;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }
}
