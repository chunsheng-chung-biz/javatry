package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private Ticket ticket;
    private int change;

    public TicketBuyResult(Ticket.TicketType ticketType, int displayPrice, int change) {
        this.ticket = new Ticket(ticketType, displayPrice);
        this.change = change;
    }

    public Ticket getTicket() {
        return ticket;
    }
    public int getChange() {
        return change;
    }
}