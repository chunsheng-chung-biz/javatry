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
    private static final List<Integer> PRICES = Collections.unmodifiableList(Arrays.asList(7400, 13200)); // when 2019/06/15
    protected enum TicketType {ONE_DAY, TWO_DAY}
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int[] quantities = {10,10};
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
        int price = PRICES.get(ticketTypeId);

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
    public int getQuantity() {
        return quantities[TicketType.ONE_DAY.ordinal()];
    }
    public int getTwoDayQuantity() {
        return quantities[TicketType.TWO_DAY.ordinal()];
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
