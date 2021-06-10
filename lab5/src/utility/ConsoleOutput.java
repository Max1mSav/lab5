package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Commands.TicketChecker;
import data.Ticket;
import exceptions.RecursionException;
import exceptions.RecursionException;
import main.Main;

/**
 * Operates command input.
 */
public class ConsoleOutput {
    private CommandManager commandManager;
    private Scanner userScanner;
    private TicketChecker ticketChecker;
    private List<String> scriptStack = new ArrayList<>();

    public ConsoleOutput(CommandManager commandManager, Scanner userScanner, TicketChecker ticketChecker) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.ticketChecker = ticketChecker;
    }

    /**
     * Mode for catching commands from user input.
     */
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            System.err.println("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            System.err.println("Непредвиденная ошибка!");
        }
    }

    /**
     * Mode for catching commands from a script.
     *
     * @param argument Its argument.
     * @return Exit code.
     */
    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = ticketChecker.getUserScanner();
            ticketChecker.setUserScanner(scriptScanner);
            ticketChecker.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                System.out.println("$" + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new RecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            ticketChecker.setUserScanner(tmpScanner);
            ticketChecker.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                System.out.println("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            System.err.println("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            System.err.println("Файл со скриптом пуст!");
        } catch (RecursionException exception) {
            System.err.println("Скрипты не могут вызываться рекурсивно!");
        } catch (IllegalStateException exception) {
            System.err.println("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return 1;
    }

    /**
     * Launchs the command.
     *
     * @param userCommand Command to launch.
     * @return Exit code.
     */
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandManager.add(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandManager.update(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clear(userCommand[1])) return 1;
                break;
            /*case "save":
                if (!commandManager.save(userCommand[1])) return 1;
                break;*/
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "add_if_max":
                if (!commandManager.addIfMax(userCommand[1])) return 1;
                break;
            case "remove_greater":
                if (!commandManager.removeGreater(userCommand[1])) return 1;
                break;
            case "history":
                if (!commandManager.history(userCommand[1])) return 1;
                break;
            case "filter_contains_name":
                if (!commandManager.filterContainsName(userCommand[1])) return 1;
                break;
            case "filter_less_than_price":
                if (!commandManager.filterLessPrice(userCommand[1])) return 1;
                break;
            case "print_field_ascending_discount":
                if (!commandManager.discountInAscendingOrder(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandManager.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commandManager.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }
}