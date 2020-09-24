package hu.macska.bkv.ticket;

public class TicketFelismeres {
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
}
