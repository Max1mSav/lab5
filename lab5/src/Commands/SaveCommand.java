package Commands;

import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

public class SaveCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
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
            collectionManager.saveCollection();
            return true;
        } catch (WrongArgumentInputException exception) {
            System.out.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
