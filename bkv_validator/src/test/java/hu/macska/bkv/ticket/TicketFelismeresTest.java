package hu.macska.bkv.ticket;

import org.junit.Test;

public class TicketFelismeresTest {
    private TicketFelismeres ticketFelismero;

    @Test(expected = TicketNumberIsNull.class)
    public void validateTicketNumberNullTest() {
        ticketFelismero = new TicketFelismeres();
        ticketFelismero.validateTicketNumber(null);
    }
}
