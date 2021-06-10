package utility;

import data.Ticket;
import main.FileHelper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CollectionManager {
    private HashSet<Ticket> ticketCollection = new HashSet<Ticket>();
    private LocalDateTime lastSaveTime;
    private LocalDateTime lastInitializationTime;
    private FileHelper fileHelper;

    public CollectionManager(FileHelper fileHelper) {
        this.lastInitializationTime = null;
        this.lastSaveTime = null;
        this.fileHelper = fileHelper;
    }

    public HashSet<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public LocalDateTime getLastInitializationTime() {
        return lastInitializationTime;
    }

    public String collectionType() {
        return ticketCollection.getClass().getName();
    }

    public Ticket getLastElement() {
        //return ticketCollection.toArray().length;
        final Iterator itr = ticketCollection.iterator();
        Ticket lastElement = (Ticket) itr.next();
        while(itr.hasNext()) {
            lastElement = (Ticket) itr.next();
        }
        return lastElement;
    }
    public int collectionSize() {
        return ticketCollection.size();
    }
    public Ticket getById(Long id) {
        for (Ticket ticket : ticketCollection) {
            if (ticket.getId() == id) return ticket;
        }
        return null;
    }

    public long createNextId() {
        if (ticketCollection.isEmpty()) return 1L;
        return ticketCollection.size() + 1;
    }

    public void removeFromCollection(Ticket ticket) {
        ticketCollection.remove(ticket);
    }

    public Ticket getByValue(Ticket ticketValue) {
        for (Ticket ticket : ticketCollection) {
            if (ticket.equals(ticketValue)) return ticket;
        }
        return null;
    }

    public void removeGreater(Ticket ticketCompare) {
        ticketCollection.removeIf(marine -> marine.compareTo(ticketCompare) > 0);
    }

    public void addToCollection(Ticket ticket) {
        ticketCollection.add(ticket);
    }

    public void deleteFromCollection(Ticket ticket) {
        ticketCollection.remove(ticket);
    }

    public void deleteCollection() {
        ticketCollection.clear();
    }

    public String nameFilter(String nameFilter) {
        String info = "";
        for (Ticket ticket : ticketCollection) {
            if (ticket.getName().equals(nameFilter)) {
                info += ticket + "\n\n";
            }
        }
        return info.trim();
    }

    public String priceLess(double priceLess) {
        String info = "";
        for (Ticket ticket : ticketCollection) {
            if (ticket.getPrice() < priceLess) {
                info += ticket + "\n\n";
            }
        }
        return info.trim();
    }

    public void sortDiscount() {
        int minDiscount = 100;
        int i = 0;
        int[] discounts = new int[ticketCollection.size()-1];
        for (Ticket ticket : ticketCollection) {
            discounts[i] = ticket.getDiscount();
            i++;
        }
        for (i = 0; i < discounts.length; i++){
            int min = discounts[i];
            int min_i = i;
            for (int j = i + 1; j < discounts.length; j++) {
                if (discounts[i] < min) {
                    min =discounts[i];
                    min_i = j;
                }
            }
            if (i != min_i) {
                int tmp = discounts[i];
                discounts[i] = discounts[min_i];
                discounts[min_i] = tmp;
            }
        for (i = 0; i <discounts.length; i++) {
            System.out.println(discounts[i]);
        }
        }
    }

    public void saveCollection() {
        fileHelper.saveCollectionToFile(ticketCollection);
        lastSaveTime = LocalDateTime.now();
    }

    public String toString() {
        if (ticketCollection.isEmpty()) return "Коллекция пуста!";

        String info = "";
        for (Ticket ticket : ticketCollection) {
            info += ticket;
            if (!ticket.equals(null)) info += "\n\n";
        }
        return info;
    }

}
