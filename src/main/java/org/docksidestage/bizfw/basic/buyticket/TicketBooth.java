/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

// done chung add your author to javadoc please by jflute (2020/04/23)

import java.util.*;

/**
 * @author jflute
 * @author chunsheng.chung
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    // done chung せっかくTicketTypeをenumに切り出したら、pricesもTicketTypeにいればいいのではと思っています winkichanwi 20200520
    protected enum TicketType {
        ONE_DAY(7400),
        TWO_DAY(13200),
        FOUR_DAY(22400);

        private final int price;

        TicketType(int price) {
            this.price = price;
        }
        public int price() {
            return price;
        }
    }
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int[] quantities = {10,10,10};
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public TicketBuyResult buyOneDayPassport(int handedMoney) { // A wrapper for perchaseAndReturnChange method now
        return buyTicket(handedMoney,TicketType.ONE_DAY);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) { // A wrapper for perchaseAndReturnChange method now
        return buyTicket(handedMoney,TicketType.TWO_DAY);
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) { // A wrapper for perchaseAndReturnChange method now
        return buyTicket(handedMoney,TicketType.FOUR_DAY);
    }


    // done chung "Javadoc: Invalid param tag name" from IDE warning, @param does not need ":" by jflute (2020/04/23)
    // done Already "@return" is not only "change" (change method, change comment) by jflute (2020/04/23)
    /**
     * Buys the specified ticket and return changes.
     * @param handedMoney Handed money.
     * @param ticketType The Enum ticket type to buy.
     * @return Returns the amount of change in int type.
     */
    private TicketBuyResult buyTicket(int handedMoney, TicketType ticketType) {
        int ticketTypeId = ticketType.ordinal();
        int price = ticketType.price;

        if (quantities[ticketTypeId] <= 0) {
            throw new TicketSoldOutException("Sold out");
        }

        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price; // Fix for q6
        } else {
            salesProceeds = price; // Fix for q6
        }

        // Create buy result
        TicketBuyResult result = new TicketBuyResult(ticketType, price, handedMoney-price);

        --quantities[ticketTypeId];
        return result;
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // done chung ここの辺の処理は同じなので、共通点を考えて共通化してみてください winkichanwi 20200520
    public int getQuantity() {
        return getQuantity(TicketType.ONE_DAY);
    }

    public int getQuantity(TicketType ticketType) {
        return quantities[ticketType.ordinal()];
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
