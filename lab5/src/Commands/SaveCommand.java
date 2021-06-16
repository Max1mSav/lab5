package Commands;

import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

import java.io.IOException;

/**
 * Save collection to the file.
 */

public class SaveCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            collectionManager.saveCollection();
            return true;
        } catch (WrongArgumentInputException | IOException exception) {
            System.out.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
