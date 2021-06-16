package Commands;

import exceptions.WrongArgumentInputException;


/**
 * Exit
 */
public class Exit extends AbstractCommand {
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
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
