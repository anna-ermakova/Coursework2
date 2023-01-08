public class ValidateUtils {
    public static String checkString(String value) throws WrongInputException{
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new WrongInputException("Некорректный ввод");
        }else {
            return value;
        }
    }
}
