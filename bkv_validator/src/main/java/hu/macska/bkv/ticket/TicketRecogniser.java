package hu.macska.bkv.ticket;

import hu.macska.bkv.ticket.exception.TicketNumberHasInvalidCharacter;
import hu.macska.bkv.ticket.exception.TicketNumberIsNull;
import hu.macska.bkv.ticket.exception.TicketNumberIsTooLarge;
import hu.macska.bkv.ticket.exception.TicketNumberIsTooShort;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public void recogniseTransporter(Ticket ticket) {
        String ticketNumber = ticket.ticketNumber;
        if (ticketNumber.matches("\\d{4}xxx\\d{9}"))
            ticket.transporter = Transporter.METRO;
        if (ticketNumber.matches("^[^9]\\d+"))
            ticket.transporter = Transporter.TRUMORBUS;
        if (ticketNumber.matches("^9\\d+"))
            ticket.transporter = Transporter.NIGHTLINE;
    }

    public void recogniseDate(Ticket ticket) {
        if (ticket.transporter == Transporter.METRO) {
            recogniseDateOfMetro(ticket);
        }
    }

    private void recogniseDateOfMetro(Ticket ticket) {
        String dateAndTime = ticket.ticketNumber.substring(7);
        String date = "201" + dateAndTime.substring(0, 5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        ticket.startDate = LocalDate.parse(date, formatter);
    }
}
