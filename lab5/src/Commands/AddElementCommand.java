package Commands;

import data.Ticket;
import exceptions.IncorrectInputException;
import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

import java.time.LocalDateTime;

public class AddElementCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketChecker ticketChecker;

    public AddElementCommand (CollectionManager collectionManager, TicketChecker ticketChecker) {
        super("Add {element}", "Добавление элемента в коллекцию");
        this.collectionManager = collectionManager;
        this.ticketChecker = ticketChecker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            collectionManager.addToCollection(new Ticket(
                    collectionManager.createNextId(),
                    ticketChecker.checkName(),
                    ticketChecker.returnCoordinates(),
                    LocalDateTime.now(),
                    ticketChecker.checkPrice(),
                    ticketChecker.checkDiscount(),
                    ticketChecker.checkTicketType(),
                    ticketChecker.returnPerson()));
            System.out.println("Билет добавлен");
            return true;
        } catch (WrongArgumentInputException exception) {
            System.out.println("Использование: " + getName());
        } catch (IncorrectInputException exception) {}
        return false;
    }
}
