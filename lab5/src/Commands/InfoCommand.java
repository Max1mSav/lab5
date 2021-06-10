package Commands;

import exceptions.WrongArgumentInputException;
import utility.CollectionManager;
import java.time.LocalDateTime;

public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("Info", "Доступная информация о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentInputException();
            LocalDateTime lastInitTime = collectionManager.getLastInitializationTime();
            String lastInitTimeString = (lastInitTime == null) ? "инициализации не было" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "сохранения не было" :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            System.out.println("Информация о данной коллекции:");
            System.out.println(" Тип: " + collectionManager.collectionType());
            System.out.println(" Дата последней инициализации: " + lastInitTimeString);
            System.out.println(" Количество элементов: " + collectionManager.collectionSize());
            System.out.println(" Дата последнего сохранения: " + lastSaveTimeString);
            return true;
        } catch (WrongArgumentInputException exception) {
            System.out.println("Execution: '" + getName());
        }
        return false;
    }
}
