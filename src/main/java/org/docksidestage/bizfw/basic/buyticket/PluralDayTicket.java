package org.docksidestage.bizfw.basic.buyticket;

/**
 * ＠author chunsheng.chung
 */
public class PluralDayTicket implements Ticket {


    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int maxDaysAvailable;
    private final int displayPrice;
    private int remainingDays;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                           =========
    public PluralDayTicket(int maxDaysAvailable, int displayPrice) {
        this.maxDaysAvailable = maxDaysAvailable;
        this.displayPrice = displayPrice;
        remainingDays = this.maxDaysAvailable;
    }


    @Override
    public void doInPark() {
        if (remainingDays == 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        remainingDays--;
    }

    @Override
    public int getDisplayPrice() {
        return displayPrice;
    }

    @Override
    public boolean isAlreadyIn() {
        return (remainingDays == 0);
    }

    @Override
    public String getTicketType() {
        return maxDaysAvailable + "Day Ticket";
    }
}
