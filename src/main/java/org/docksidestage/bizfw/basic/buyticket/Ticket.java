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

/**
 * @author jflute
 */
public class Ticket {


    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private boolean alreadyIn;
    protected enum TicketType {ONE_DAY, TWO_DAY};
    private TicketType ticketType;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========

    public Ticket(int displayPrice) {
        this.displayPrice = displayPrice;
        // Auto detect type by price
        // A quick fix, can cause performance problem if there are too many ticket types
        // TODO Better solution should be using a map of price -> type, or remove this
        //  constructor and regulate other code?
        for (TicketType t: TicketType.values()) {
            if (TicketBooth.PRICES[t.ordinal()] == displayPrice) {
                this.ticketType = t;
                break;
            }
        }
        this.ticketType = ticketType;
    }

    public Ticket(TicketType ticketType, int displayPrice) {
        this.ticketType = ticketType;
        this.displayPrice = displayPrice;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    public String getTicketType() {
        switch (ticketType) {
        case ONE_DAY:
            return ("One Day Passport");
        case TWO_DAY:
            return ("Two Day Passport");
        default:
            return ("Unknown");
        }
    }
}
