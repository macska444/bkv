package hu.macska.bkv.ticket;

public class TicketFelismeres {
    public void validateTicketNumber(String ticketNumber) {
        if (ticketNumber == null)
            throw new TicketNumberIsNull();

    }
}