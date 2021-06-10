package utility;

import java.io.*;
import java.util.*;
import Commands.Command;
import Commands.UpdateIdCommand;
import exceptions.EmptyException;

public class CommandManager {

    private final int COMMAND_HISTORY_SIZE = 8;
    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands = new ArrayList<>();
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addElementCommand;
    private Command updateIdCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command exit;
    private Command executeScriptCommand;
    private Command addIfMaxCommand;
    private Command removeGreaterCommand;
    private Command historyCommand;
    private Command filterContainsName;
    private Command filterLessPrice;
    private Command discountInAscendingOrder;

    public CommandManager(Command helpCommand, Command infoCommand, Command showCommand, Command addElementCommand,
        Command updateIdCommand, Command removeByIdCommand, Command clearCommand, Command exit, Command addIfMaxCommand,
                          Command removeGreaterCommand, Command historyCommand, Command filterContainsName,
                          Command filterLessPrice, Command discountInAscendingOrder, Command saveCommand,
                          Command executeScriptCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addElementCommand = addElementCommand;
        this.updateIdCommand = updateIdCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.exit = exit;
        this.addIfMaxCommand = addIfMaxCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.historyCommand = historyCommand;
        this.filterContainsName = filterContainsName;
        this.filterLessPrice = filterLessPrice;
        this.discountInAscendingOrder = discountInAscendingOrder;
        this.saveCommand = saveCommand;
        this.executeScriptCommand = executeScriptCommand;
        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addElementCommand);
        commands.add(updateIdCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(executeScriptCommand);
        commands.add(exit);
        commands.add(addIfMaxCommand);
        commands.add(removeGreaterCommand);
        commands.add(historyCommand);
        commands.add(filterContainsName);
        commands.add(filterLessPrice);
        commands.add(discountInAscendingOrder);

    }


    /**
     * @return The command history.
     */
    public String[] getCommandHistory() {
        return commandHistory;
    }

    /**
     * @return List of manager's commands.
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Adds command to command history.
     *
     * @param commandToStore Command to add.
     */
    public void addToHistory(String commandToStore) {

        for (Command command : commands) {
            if (command.getName().split(" ")[0].equals(commandToStore)) {
                for (int i = COMMAND_HISTORY_SIZE - 1; i > 0; i--) {
                    commandHistory[i] = commandHistory[i - 1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }

    /* public boolean noSuchCommand(String command) {
        Console.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    } */

    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            for (Command command : commands) {
                System.out.printf("%-37s%-1s%n", command.getName(), command.getInfo());
            }
            return true;
        } else return false;
    }

    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    public boolean add(String argument) {
        return addElementCommand.execute(argument);
    }

    public boolean update(String argument) {
        return updateIdCommand.execute(argument);
    }

    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }

    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

   public boolean save(String argument) {
        return saveCommand.execute(argument);
    }

    public boolean exit(String argument) {
        return exit.execute(argument);
    }

    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }

    public boolean addIfMax(String argument) {
        return addIfMaxCommand.execute(argument);
    }

    public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument);
    }

    public boolean filterContainsName(String argument) {
        return filterContainsName.execute(argument);
    }
    public boolean filterLessPrice(String argument) {
        return filterLessPrice.execute(argument);
    }
    public boolean discountInAscendingOrder(String argument) {
        return discountInAscendingOrder.execute(argument);
    }

    public boolean history(String argument) {
        if (historyCommand.execute(argument)) {
            try {
                if (commandHistory.length == 0) throw new EmptyException();

                System.out.println("Последние использованные команды:");
                for (int i = 0; i < commandHistory.length; i++) {
                    if (commandHistory[i] != null) System.out.println(" " + commandHistory[i]);
                }
                return true;
            } catch (EmptyException exception) {
                System.out.println("Ни одной команды еще не было использовано!");
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }

    public boolean noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }
}