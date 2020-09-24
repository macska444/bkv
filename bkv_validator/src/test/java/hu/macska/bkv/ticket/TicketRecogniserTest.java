package hu.macska.bkv.ticket;

import hu.macska.bkv.ticket.exception.TicketNumberHasInvalidCharacter;
import hu.macska.bkv.ticket.exception.TicketNumberIsNull;
import hu.macska.bkv.ticket.exception.TicketNumberIsTooLarge;
import hu.macska.bkv.ticket.exception.TicketNumberIsTooShort;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TicketRecogniserTest {

    private TicketRecogniser ticketRecogniser;

    @Before
    public void setUp()  {
        ticketRecogniser = new TicketRecogniser();
    }

    @Test(expected = TicketNumberIsNull.class)
    public void validateTicketNumberNullTest() {
        ticketRecogniser.validateTicketNumber(null);
    }

    @Test(expected = TicketNumberIsTooLarge.class)
    public void validateTicketLargeTest() {
        ticketRecogniser.validateTicketNumber("EzegymarhahosszústringEzegymarhahosszústring");
    }

    @Test(expected = TicketNumberIsTooShort.class)
    public void validateTicketNumberShortTest() {
        ticketRecogniser.validateTicketNumber("123456789");
    }

    @Test(expected = TicketNumberHasInvalidCharacter.class)
    public void validateTicketNumberHasInvalidCharacterTest() {
        ticketRecogniser.validateTicketNumber("Macska jegye123");
    }

    @Test
    public void validateTicketNumberValidSyntacticallyTest() {
        boolean valid = ticketRecogniser.validateTicketNumber("0643xxx911281305");
        assertTrue(valid);
    }

    @Test
    public void recogniseTransporterMetroTest() {
        Ticket ticket = ticketRecogniser.recogniseTransporter("0643xxx911281305");
        assertEquals(Transporter.METRO,ticket.transporter);
    }

    @Test
    public void recogniseTransporterTramOrBusTest() {
        Ticket ticket = ticketRecogniser.recogniseTransporter("5293111008172015");
        assertEquals(Transporter.TRUMORBUS,ticket.transporter);
    }

    @Test
    public void recogniseTransporterNightLineTest() {
        Ticket ticket = ticketRecogniser.recogniseTransporter("9293111008172015");
        assertEquals(Transporter.NIGHTLINE,ticket.transporter);
    }
}
