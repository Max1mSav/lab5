package Commands;

import exceptions.CollectionEmptyException;
import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

/**
 * Discount sorting.
 */

public class DiscountInAscendingOrder extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketChecker ticketChecker;

    public DiscountInAscendingOrder(CollectionManager collectionManager, TicketChecker ticketChecker) {
        super("print_field_ascending_discount", "вывести значения поля discount всех элементов в порядке возрастания");
        this.collectionManager = collectionManager;
        this.ticketChecker = ticketChecker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            collectionManager.sortDiscount();
            return true;
        } catch (WrongArgumentInputException e) {
            e.printStackTrace();
        } catch (CollectionEmptyException exception) {
            System.err.println("Коллекция пуста!");
        }
        return false;
    }
}
