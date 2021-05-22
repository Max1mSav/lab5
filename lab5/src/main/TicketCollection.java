package main;

import data.Ticket;

import java.time.LocalDateTime;
import java.util.*;

public class TicketCollection {
    private HashSet<Ticket> ticketCollection= new HashSet<>();
    private LocalDateTime lastSaveTime;
    private LocalDateTime lastInitTime;

    public TicketCollection(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;

    }

}
