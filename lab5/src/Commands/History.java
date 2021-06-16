package Commands;

import exceptions.WrongArgumentInputException;

/**
 * Last 8 commands
 */

public class History extends AbstractCommand {
    public History() {
        super("history", "вывести историю использованных команд");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            return true;
        } catch (WrongArgumentInputException exception) {
            System.out.println("Использование: " + getName());
        }
        return false;
    }
}
