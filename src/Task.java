import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return archived == task.archived && Objects.equals(title, task.title) && Objects.equals(description, task.description) && taskType == task.taskType && Objects.equals(firstDate, task.firstDate) && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, taskType, firstDate, archived, id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", firstDate=" + firstDate +
                ", archived=" + archived +
                ", id=" + id +
                '}';
    }
}
