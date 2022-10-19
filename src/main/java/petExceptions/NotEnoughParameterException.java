package petExceptions;

public class NotEnoughParameterException extends Exception{
    public NotEnoughParameterException(){}

    public String toString(){
        String errorMessage = "Invalid Input! Too little parameters entered for pet operation";
        return errorMessage;
    }
}
