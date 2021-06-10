package Commands;

import exceptions.WrongArgumentInputException;

public class ExecuteScriptCommand extends AbstractCommand{
    public ExecuteScriptCommand() {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
    }

    /**
     * Executes the command, but partially.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgumentInputException();
            System.out.println("Выполняю скрипт '" + argument + "'...");
            return true;
        } catch (WrongArgumentInputException exception) {
            System.out.println("Использование: " + getName());
        }
        return false;
    }
}
