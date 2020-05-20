package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.climber.Climber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sloth extends Animal implements Climber {
    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Cat.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Sloth() {

    }

    @Override
    protected String getBarkWord() {
        return "aaah"; // https://www.youtube.com/watch?v=aaqzPMOd_1g
    }

    @Override
    public void climb() {
        logger.debug("Climbing...");
        downHitPoint();
    }

    public void upHitPoint() {
        hitPoint++;
    }

    // Sloths always sleep
    public void sleep() {
        logger.debug("zzz...");
        upHitPoint();
    }
}
