package main;
import Commands.*;
import data.Ticket;
import utility.CollectionManager;
import utility.CommandManager;
import utility.ConsoleOutput;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "Lab5";
            TicketChecker ticketChecker = new TicketChecker(userScanner);
            FileHelper fileHelper = new FileHelper(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileHelper);
            CommandManager commandManager = new CommandManager(new HelpCommand(), new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager), new AddElementCommand(collectionManager, ticketChecker),
                    new UpdateIdCommand(collectionManager, ticketChecker), new RemoveById(collectionManager),
                    new Clear(collectionManager), new Exit(), new AddIfMax(collectionManager, ticketChecker),
                    new RemoveGreater(collectionManager, ticketChecker), new History(), new FilterContainsName(collectionManager),
                    new FilterLessPrice(collectionManager, ticketChecker), new DiscountInAscendingOrder(collectionManager),
                    new SaveCommand(collectionManager), new ExecuteScriptCommand());

            ConsoleOutput console = new ConsoleOutput(commandManager, userScanner, ticketChecker);

            console.interactiveMode();
        }
    }
}
