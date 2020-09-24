package hu.macska.bkv.ticket;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TicketFelismeresTest {
    private TicketFelismeres ticketFelismero;

    @Test(expected = TicketNumberIsNull.class)
    public void validateTicketNumberNullTest() {
        ticketFelismero = new TicketFelismeres();
        ticketFelismero.validateTicketNumber(null);
    }

    @Test(expected = TicketNumberIsTooLarge.class)
    public void validateTicketLargeTest() {
        ticketFelismero = new TicketFelismeres();
        ticketFelismero.validateTicketNumber("EzegymarhahosszústringEzegymarhahosszústring");
    }

    @Test(expected = TicketNumberIsTooShort.class)
    public void validateTicketNumberShortTest() {
        ticketFelismero = new TicketFelismeres();
        ticketFelismero.validateTicketNumber("123456789");
    }

    @Test(expected = TicketNumberHasInvalidCharacter.class)
    public void validateTicketNumberHasInvalidCharacterTest() {
        ticketFelismero = new TicketFelismeres();
        ticketFelismero.validateTicketNumber("Macska jegye123");
    }

    @Test
    public void validateTicketNumberValidSyntacticallyTest() {
        ticketFelismero = new TicketFelismeres();
        boolean valid = ticketFelismero.validateTicketNumber("0643xxx911281305");
        assertTrue(valid);
    }


}
