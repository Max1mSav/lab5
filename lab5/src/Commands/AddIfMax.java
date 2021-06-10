package Commands;

import data.Ticket;
import exceptions.IncorrectInputException;
import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

import java.time.LocalDateTime;

public class AddIfMax extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketChecker ticketChecker;

    public AddIfMax(CollectionManager collectionManager, TicketChecker ticketChecker) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.ticketChecker = ticketChecker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            Ticket ticketAdd = new Ticket(collectionManager.getTicketCollection().size(), ticketChecker.checkName(),
                    ticketChecker.returnCoordinates(), LocalDateTime.now(), ticketChecker.checkPrice(),
                    ticketChecker.checkDiscount(), ticketChecker.checkTicketType(), ticketChecker.returnPerson());
            if (collectionManager.collectionSize() == 0 || ticketAdd.compareTo(collectionManager.getLastElement()) < 0) {
                collectionManager.addToCollection(ticketAdd);
                System.out.println("Билет добавлен");
                return true;
            } else System.err.println("Значение билета меньше наибольшего");
        } catch (WrongArgumentInputException exception) {
            System.out.println("Использование: " + getName());
        } catch (IncorrectInputException e) {}
        return false;
    }
}
