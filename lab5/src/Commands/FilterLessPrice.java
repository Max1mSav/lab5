package Commands;

import exceptions.CollectionEmptyException;
import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

public class FilterLessPrice extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketChecker ticketChecker;

    public FilterLessPrice(CollectionManager collectionManager, TicketChecker ticketChecker) {
        super("filter_less_than_price price", "вывести элементы, значение поля price которых меньше заданного");
    }

    @Override
    public boolean execute(String argument) {
        Double priceLess;
        try {
            if (argument.isEmpty()) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            priceLess = Double.parseDouble(argument);
            String filteredElements = collectionManager.priceLess(priceLess);
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
