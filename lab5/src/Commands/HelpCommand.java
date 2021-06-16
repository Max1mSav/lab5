package Commands;
import exceptions.WrongArgumentInputException;

/**
 * Help command
 */

public class HelpCommand extends AbstractCommand {
   public HelpCommand() {
       super("help", "Доступные команды");
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
