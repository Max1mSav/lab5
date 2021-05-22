package utility;
import data.Ticket;
import exceptions.IncorrectInputException;

import java.io.BufferedInputStream;

public class TicketChecker {
    private final int MIN_DISCOUNT = 1;
    private final int MAX_DISCOUNT = 100;
    private final double MIN_PRICE = 0;

    /*BufferedInputStream fileInput;

    public TicketChecker(BufferedInputStream fileInput) {
        this.fileInput = fileInput;
    }

    public String checkName() throws IncorrectInputException {
        String name;
        while (true) {
            try {
                Console.println("Введите имя:");
                Console.print(App.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }*/
}
