package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author chunsheng.chung
 */
public class BarkingProcess {
    protected BarkedSound processBark(Animal animal) {
        breatheIn(animal);
        prepareAbdominalMuscle(animal);
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = doBark(animal, barkWord);
        return barkedSound;
    }

    protected BarkedSound doBark(Animal animal, String barkWord) {
        animal.downHitPoint();
        return new BarkedSound(barkWord);
    }

    protected void prepareAbdominalMuscle(Animal animal) {
        animal.logger.debug("...Using my abdominal muscle"); // dummy implementation
        animal.downHitPoint();
    }

    protected void breatheIn(Animal animal) {
        animal.logger.debug("...Breathing in"); // dummy implementation
        animal.downHitPoint();
    }
}
