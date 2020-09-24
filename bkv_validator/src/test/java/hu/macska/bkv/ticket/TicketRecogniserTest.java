package hu.macska.bkv.ticket;

import hu.macska.bkv.ticket.exception.TicketNumberHasInvalidCharacter;
import hu.macska.bkv.ticket.exception.TicketNumberIsNull;
import hu.macska.bkv.ticket.exception.TicketNumberIsTooLarge;
import hu.macska.bkv.ticket.exception.TicketNumberIsTooShort;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        Ticket ticket = new Ticket("0643xxx911281305");
        ticketRecogniser.recogniseTransporter(ticket);
        assertEquals(Transporter.METRO,ticket.transporter);
    }

    @Test
    public void recogniseTransporterTramOrBusTest() {
        Ticket ticket = new Ticket("5293111008172015");
        ticketRecogniser.recogniseTransporter(ticket);
        assertEquals(Transporter.TRUMORBUS,ticket.transporter);
    }

    @Test
    public void recogniseTransporterNightLineTest() {
        Ticket ticket = new Ticket("9293111008172015");
        ticketRecogniser.recogniseTransporter(ticket);
        assertEquals(Transporter.NIGHTLINE,ticket.transporter);
    }

    @Test
    public void recogniseDateMetroTest() {
        Ticket ticket = new Ticket("0643xxx911281305");
        ticketRecogniser.recogniseTransporter(ticket);

        ticketRecogniser.recogniseDate(ticket);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String date = "2019.11.28";
        LocalDate startDate = LocalDate.parse(date, formatter);

        assertEquals(startDate,ticket.startDate);
    }
}
