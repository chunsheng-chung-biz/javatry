package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private Ticket ticket;
    private int change;

    public TicketBuyResult(int displayPrice, int change) {
        this.ticket = new Ticket(displayPrice);
        this.change = change;
    }

    public Ticket getTicket() {
        return ticket;
    }
    public int getChange() {
        return change;
    }
}