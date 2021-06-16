package Commands;

import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

/**
 * Show information about every element of collection.
 */

public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public ShowCommand (CollectionManager collectionManager) {
        super("show", "Вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            System.out.println(collectionManager);
            return true;
        } catch (WrongArgumentInputException exception) {
            System.out.println("Выполнение: " + getName());
        }
        return false;
    }
}
