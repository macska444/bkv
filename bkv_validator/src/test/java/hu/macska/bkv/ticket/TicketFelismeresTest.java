package hu.macska.bkv.ticket;

import org.junit.Test;

public class TicketFelismeresTest {
    private TicketFelismeres ticketFelismero;

    @Test(expected = TicketNumberIsNull.class)
    public void validateTicketNumberNullTest() {
        ticketFelismero = new TicketFelismeres();
        ticketFelismero.validateTicketNumber(null);
    }

    @Test(expected = TicketNumberIsTooLarge.class)
    public void validateTicketNumberInvalidLengthTest() {
        ticketFelismero = new TicketFelismeres();
        ticketFelismero.validateTicketNumber("EzegymarhahosszústringEzegymarhahosszústring");
    }
}
