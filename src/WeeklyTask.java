import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repeatable{

    public WeeklyTask(String title, String description, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, description, taskType, date);
    }

    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfWeek().equals(requestedDate.getDayOfWeek());
    }

    @Override
    public void setArchived(boolean archived) {

    }
}
