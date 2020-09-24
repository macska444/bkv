package hu.macska.bkv.ticket;

import java.time.LocalDate;

public class Ticket {
    public final String ticketNumber;
    public Transporter transporter;
    public LocalDate startDate;

    public Ticket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
