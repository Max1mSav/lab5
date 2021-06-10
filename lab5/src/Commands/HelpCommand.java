package Commands;
import exceptions.WrongArgumentInputException;

public class HelpCommand extends AbstractCommand {
   public HelpCommand() {
       super("Help", "Доступные команды");
   }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            return true;
        } catch (WrongArgumentInputException exception) {
            System.out.println("Execution: '" + getName());
        }
        return false;
    }
}
