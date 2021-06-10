package Commands;

import data.Ticket;
import exceptions.CollectionEmptyException;
import exceptions.EmptyException;
import exceptions.IncorrectInputException;
import exceptions.WrongArgumentInputException;
import utility.CollectionManager;

public class RemoveById extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgumentInputException();
            if (collectionManager.collectionSize() == 0) throw new CollectionEmptyException();
            Long id = Long.parseLong(argument);
            Ticket ticketRemove = collectionManager.getById(id);
            if (ticketRemove == null) throw new EmptyException();
            collectionManager.removeFromCollection(ticketRemove);
            System.out.println("Билет с таким id удалён");
            return true;
        } catch (WrongArgumentInputException exception) {
            System.out.println("Использование: " + getName());
        } catch (CollectionEmptyException exception) {
            System.err.println("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            System.out.println("ID должен быть целым числом");
        } catch (EmptyException exception) {
            System.out.println("Билета с таким id нет");
        }
        return false;
    }
}
