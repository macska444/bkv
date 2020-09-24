package hu.macska.bkv.ticket;

import hu.macska.bkv.ticket.exception.TicketNumberHasInvalidCharacter;
import hu.macska.bkv.ticket.exception.TicketNumberIsNull;
import hu.macska.bkv.ticket.exception.TicketNumberIsTooLarge;
import hu.macska.bkv.ticket.exception.TicketNumberIsTooShort;

public class TicketRecogniser {
    public boolean validateTicketNumber(String ticketNumber) {
        if (ticketNumber == null)
            throw new TicketNumberIsNull();
        if (ticketNumber.length() > 16)
            throw new TicketNumberIsTooLarge();
        if (ticketNumber.length() < 13)
            throw new TicketNumberIsTooShort();
        if (!ticketNumber.matches("(x|\\d)+"))
            throw new TicketNumberHasInvalidCharacter();
        return true;
    }

    public Ticket recogniseTicket(String ticketNumber) {
        Ticket ticket = new Ticket();
        if (ticketNumber.matches("\\d{4}xxx\\d{9}"))
            ticket.transporter = Transporter.METRO;
        if (ticketNumber.matches("[^9]\\d+"))
            ticket.transporter = Transporter.TRUMORBUS;
        return ticket;
    }
}
