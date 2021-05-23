package main;
import data.Ticket;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileHelper json = new FileHelper("C:\\Users\\User\\IdeaProjects\\lab5\\TicketCollection");
        HashSet<Ticket> collection = json.readCollectionFromFile();
    }
}
