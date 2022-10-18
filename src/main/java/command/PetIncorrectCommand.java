package command;

public class PetIncorrectCommand extends Command{
    private String prompt;

    public PetIncorrectCommand(String prompt){
        this.prompt = prompt;
    }

    @Override
    public void execute() {
        System.out.println(prompt);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
