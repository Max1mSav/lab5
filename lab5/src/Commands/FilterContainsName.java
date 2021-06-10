package Commands;

import data.Ticket;
import exceptions.CollectionEmptyException;
import exceptions.EmptyException;
import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

public class FilterContainsName extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterContainsName(CollectionManager collectionManager) {
        super("filter_contains_name name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            String filteredElements = collectionManager.nameFilter(argument);
            if (!filteredElements.isEmpty()) {
                System.out.println(filteredElements);
                return true;
            } else System.out.println("В коллекции нет элементов с таким именем");
        } catch (WrongArgumentInputException exception) {
            System.out.println("Использование: " + getName());
        } catch (CollectionEmptyException exception) {
            System.err.println("Коллекция пуста!");
        }
        return false;
    }
}
