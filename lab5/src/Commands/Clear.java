package Commands;

import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

public class Clear extends AbstractCommand {
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            collectionManager.deleteCollection();
            System.out.println("Коллекция очищена!");
            return true;
        } catch (WrongArgumentInputException exception) {
            System.out.println("Использование: " + getName());
        }
        return false;
    }
}
