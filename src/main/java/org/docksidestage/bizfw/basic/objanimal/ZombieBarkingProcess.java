package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author chunsheng.chung
 */
public class ZombieBarkingProcess extends BarkingProcess {
    @Override
    protected void breatheIn(Animal animal) {
        super.breatheIn(animal);
        ((Zombie)animal).zombieDiary.countBreatheIn();
    }
}
