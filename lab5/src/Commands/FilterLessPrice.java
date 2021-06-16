package Commands;

import exceptions.CollectionEmptyException;
import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

/**
 * Filter by price
 */

public class FilterLessPrice extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketChecker ticketChecker;

    public FilterLessPrice(CollectionManager collectionManager, TicketChecker ticketChecker) {
        super("filter_less_than_price price", "вывести элементы, значение поля price которых меньше заданного");
        this.collectionManager = collectionManager;
        this.ticketChecker = ticketChecker;
    }

    @Override
    public boolean execute(String argument) {
        Double priceLess;
        try {
            if (argument.isEmpty()) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            priceLess = Double.parseDouble(argument);
            System.out.println(priceLess);
            String filteredElements = collectionManager.priceLess(priceLess);
            if (!filteredElements.isEmpty()) {
                System.out.println(filteredElements);
                return true;
            } else System.out.println("В коллекции нет билетов с ценой ниже " + priceLess);
        } catch (WrongArgumentInputException exception) {
            System.out.println("Использование: " + getName());
        } catch (CollectionEmptyException exception) {
            System.err.println("Коллекция пуста!");
        }
        return false;
    }
}
