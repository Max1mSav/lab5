package Commands;

import data.Coordinates;
import data.Person;
import data.Ticket;
import data.TicketType;
import exceptions.CollectionEmptyException;
import exceptions.EmptyException;
import exceptions.IncorrectInputException;
import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

import java.time.LocalDateTime;

public class UpdateIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketChecker ticketChecker;

    public UpdateIdCommand(CollectionManager collectionManager, TicketChecker ticketChecker) {
        super("Update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.ticketChecker = ticketChecker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();

            Long id = Long.parseLong(argument);
            Ticket ticket = collectionManager.getById(id);
            if (ticket == null) throw new EmptyException();

            String name = ticket.getName();
            Coordinates coordinates = ticket.getCoordinates();
            LocalDateTime creationDate = ticket.getCreationDate();
            Double price = ticket.getPrice();
            int discount = ticket.getDiscount();
            TicketType ticketType = ticket.getType();
            Person person = ticket.getPerson();

            collectionManager.deleteFromCollection(ticket);
            if (ticketChecker.wantToChange("Хотите поменять что-то или оставить как было?")) {
                if (ticketChecker.wantToChange("Хотите поменять имя?")) name = ticketChecker.checkName();
                if (ticketChecker.wantToChange("Хотите поменять координаты?"))
                    coordinates = ticketChecker.returnCoordinates();
                if (ticketChecker.wantToChange("Хотите поменять цену?")) price = ticketChecker.checkPrice();
                if (ticketChecker.wantToChange("Хотите поменять скидку?")) discount = ticketChecker.checkDiscount();
                if (ticketChecker.wantToChange("Хотите поменять тип билета?"))
                    ticketType = ticketChecker.checkTicketType();
                if (ticketChecker.wantToChange("Хотите поменять какие-либо данные о человеке?"))
                    person = ticketChecker.returnPerson();
            }


            collectionManager.addToCollection(new Ticket(id, name, coordinates, creationDate, price, discount,
                        ticketType, person));
            System.out.println("Изменения загружены");
            return true;
            } catch(IncorrectInputException exception){
                System.out.println("Использование: " + getName());
            } catch(CollectionEmptyException exception){
                System.err.println("Коллекция пуста!");
            } catch(WrongArgumentInputException exception){
                System.err.println("ID должен быть целым числом!");
            } catch(EmptyException exception){
                System.err.println("Такого билета с таким id нет");
        }
        return false;
    }
}