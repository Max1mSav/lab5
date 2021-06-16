package Commands;

import data.Ticket;
import exceptions.CollectionEmptyException;
import exceptions.EmptyException;
import exceptions.IncorrectInputException;
import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

import java.time.LocalDateTime;

/**
 * Remove all greater tickets.
 */

public class RemoveGreater extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketChecker ticketChecker;

    public RemoveGreater(CollectionManager collectionManager, TicketChecker ticketChecker) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.ticketChecker = ticketChecker;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            Ticket ticketToFind = new Ticket(collectionManager.createNextId(), ticketChecker.checkName(),
                    ticketChecker.returnCoordinates(), LocalDateTime.now(), ticketChecker.checkPrice(),
                    ticketChecker.checkDiscount(), ticketChecker.checkTicketType(), ticketChecker.returnPerson());
            Ticket ticketToDelete = collectionManager.getByValue(ticketToFind);
            if (ticketToDelete == null) throw new EmptyException();
            collectionManager.removeGreater(ticketToFind);
            System.out.println("Билеты удалены");
            return true;
        } catch (WrongArgumentInputException exception) {
            System.out.println("Использование: " + getName());
        } catch (CollectionEmptyException exception) {
            System.err.println("Коллекция пуста!");
        } catch (EmptyException exception) {
            System.err.println("Таких билетов в коллекции нет!");
        } catch (IncorrectInputException e) {}
        return false;
    }
}
