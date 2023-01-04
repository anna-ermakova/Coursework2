import java.time.LocalDateTime;

public class MonthlyTask extends Task implements Repeatable{

    public MonthlyTask(String title, String description, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, description, taskType, date);
    }

    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().getMonth().equals(requestedDate.getMonth());
    }

    @Override
    public void setArchived(boolean archived) {

    }
}
