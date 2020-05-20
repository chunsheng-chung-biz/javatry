package org.docksidestage.bizfw.basic.buyticket;

/**
 * ＠author chunsheng.chung
 */
public class OneDayTicket implements Ticket {


    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private boolean alreadyIn;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                           =========
    public OneDayTicket(int displayPrice) {
        this.displayPrice = displayPrice;
    }

    @Override
    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;
    }

    @Override
    public int getDisplayPrice() {
        return displayPrice;
    }

    @Override
    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    @Override
    public String getTicketType() {
        return "One Day Ticket";
    }
}
