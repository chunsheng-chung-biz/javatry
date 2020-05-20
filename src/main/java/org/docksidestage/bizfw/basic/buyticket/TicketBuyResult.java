package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private Ticket ticket;
    private int change;

    public TicketBuyResult(TicketBooth.TicketType ticketType, int displayPrice, int change) throws IllegalArgumentException {
        switch (ticketType) {
        case ONE_DAY:
            this.ticket = new OneDayTicket(displayPrice);
            break;
        case TWO_DAY:
            this.ticket = new PluralDayTicket(2, displayPrice);
            break;
        default:
            throw new IllegalArgumentException("Unsupported ticket type");
        }
        this.change = change;
    }

    public Ticket getTicket() {
        return ticket;
    }
    public int getChange() {
        return change;
    }
}
