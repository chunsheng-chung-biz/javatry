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
package org.docksidestage.javatry.framework;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.supercar.SupercarDealer;
import org.docksidestage.bizfw.di.container.SimpleDiContainer;
import org.docksidestage.bizfw.di.nondi.*;
import org.docksidestage.bizfw.di.usingdi.*;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Dependency Injection (DI) as beginner level. <br>
 * Show answer by log() or write answer on comment for question of javadoc.
 * @author jflute
 * @author chunsheng.chung
 */
public class Step41DependencyInjectionBeginnerTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        Precondition
    //                                                                        ============
    /**
     * Search "Dependency Injection" by internet and learn it in thirty minutes. (study only) <br>
     * ("Dependency Injection" をインターネットで検索して、30分ほど学んでみましょう。(勉強のみ))
     */
    public void test_whatis_DependencyInjection() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // What is Dependency Injection?
        // - - - - - (your answer?)
        // Long story short, "Dependency Injection" means giving an object its instance variables,
        // instead of generating its own one in the implementation.
        // This way, The object can be decoupled to the inner object and become easier to test.
        //
        // _/_/_/_/_/_/_/_/_/_/
    }

    // ===================================================================================
    //                                                                 Non DI Code Reading
    //                                                                 ===================
    /**
     * What is the difference between NonDiDirectFirstAction and NonDiDirectSecondAction? <br>
     * (NonDiDirectFirstAction と NonDiDirectSecondAction の違いは？)
     */
    public void test_nondi_difference_between_first_and_second() {
        // your answer? => In callFriend() and wakeupMe() the dogs have different types and behaviors
        // Because when TooLazyDog barks it will also make its friend cat bark, because cat cannot
        // bark 3 times due to HP limit, there will be an exception.
        // And in goToOffice() and sendGift(), a log is added in NonDiDirectSecondAction.
        // Both should have the bug where the a kawaii face comes out from SupercarSteeringWheelComponentDB \(^_^)/
        // As DI is not applied, it is very confusing to trace and test these code...
        // and your confirmation code here freely

//        NonDiDirectFirstAction first = new NonDiDirectFirstAction();
//        NonDiDirectSecondAction second = new NonDiDirectSecondAction();
//
//        first.callFriend();
//        log("--------");
//        second.callFriend();
//        log("/////////////////////");
//        first.wakeupMe();
//        log("--------");
//        second.wakeupMe();
//        log("/////////////////////");
//        first.goToOffice();
//        log("--------");
//        second.goToOffice();
//        log("/////////////////////");
//        first.sendGift();
//        log("--------");
//        second.sendGift();
    }

    /**
     * What is the difference between NonDiDirectSecondAction and NonDiFactoryMethodAction? <br>
     * (NonDiDirectSecondAction と NonDiFactoryMethodAction の違いは？)
     */
    public void test_nondi_difference_between_second_and_FactoryMethod() {
        // your answer? => Factory method integrates the process of creating TooLazyDog and pleasing it,
        // and overriding makeSupercar, so the same procedures don't need to be repeated.
        // and your confirmation code here freely
        NonDiFactoryMethodAction factoryMethodAction = new NonDiFactoryMethodAction();
//        factoryMethodAction.goToOffice();
    }

    /**
     * What is the difference between NonDiFactoryMethodAction and NonDiIndividualFactoryAction? <br>
     * (NonDiFactoryMethodAction と NonDiIndividualFactoryAction の違いは？)
     */
    public void test_nondi_difference_between_FactoryMethod_and_IndividualFactory() {
        // your answer? => The factory methods are put into individual Factory classes.
        // and your confirmation code here freely
    }

    // ===================================================================================
    //                                                               Using DI Code Reading
    //                                                               =====================
    /**
     * What is the difference between UsingDiAccessorAction and UsingDiAnnotationAction? <br>
     * (UsingDiAccessorAction と UsingDiAnnotationAction の違いは？)
     */
    public void test_usingdi_difference_between_Accessor_and_Annotation() {
        // your answer? => In UsingDiAccessorAction, the animal and supercarDealer are provided with
        // setter methods, and in UsingDiAnnotationAction, they are injected with annotation.
        // CORRECT: Both approaches can be automated with tools.
        // In this way, the classes we are using can be manged in one single place, and making changes can
        // be much easier.
        // and your confirmation code here freely
        UsingDiAccessorAction diAccessorAction = new UsingDiAccessorAction();
        diAccessorAction.setAnimal(new Dog());
        diAccessorAction.setSupercarDealer(new SupercarDealer());
        diAccessorAction.callFriend();

        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();
        diContainer.registerModule(componentMap -> {
            componentMap.put(UsingDiAnnotationAction.class, new UsingDiAnnotationAction());
            componentMap.put(Animal.class, new Dog());
            componentMap.put(SupercarDealer.class, new SupercarDealer());
                }

        );

        diContainer.resolveDependency();

        UsingDiAnnotationAction diAnnotationAction =
                (UsingDiAnnotationAction) diContainer.getComponent(UsingDiAnnotationAction.class);
        diAnnotationAction.callFriend();
    }

    /**
     * What is the difference between UsingDiAnnotationAction and UsingDiDelegatingAction? <br>
     * (UsingDiAnnotationAction と UsingDiDelegatingAction の違いは？)
     *
     */
    public void test_usingdi_difference_between_Annotation_and_Delegating() {
        // your answer? => In UsingDiDelegatingAction, all the objects to be injected are handled by another object,
        // and all related methods are moved there too.
        // I think this enables more code recycling as other objects that have similar methods (but not suitable for
        // inheritance?) can use the helper object as well.
        // and your confirmation code here freely
        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();
        diContainer.registerModule(componentMap -> {
                    componentMap.put(UsingDiAnnotationAction.class, new UsingDiAnnotationAction());
                    componentMap.put(Animal.class, new Dog());
                    componentMap.put(SupercarDealer.class, new SupercarDealer());
                    componentMap.put(UsingDiDelegatingAction.class, new UsingDiDelegatingAction());
                    componentMap.put(UsingDiDelegatingLogic.class, new UsingDiDelegatingLogic());
                }

        );

        diContainer.resolveDependency();

        UsingDiAnnotationAction diAnnotationAction =
                (UsingDiAnnotationAction) diContainer.getComponent(UsingDiAnnotationAction.class);
        diAnnotationAction.callFriend();

        UsingDiDelegatingAction diDelegatingAction =
                (UsingDiDelegatingAction) diContainer.getComponent(UsingDiDelegatingAction.class);
        diDelegatingAction.callFriend();
    }

    // ===================================================================================
    //                                                           Execute like WebFramework
    //                                                           =========================
    /**
     * Execute callFriend() of accessor action by UsingDiWebFrameworkProcess. <br>
     * (accessor の Action の callFriend() を UsingDiWebFrameworkProcess 経由で実行してみましょう)
     */
    public void test_usingdi_UsingDiWebFrameworkProcess_callfriend_accessor() {
        // execution code here
        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();
        diContainer.registerModule(componentMap -> {
                    componentMap.put(UsingDiAccessorAction.class, new UsingDiAccessorAction());
                    componentMap.put(Animal.class, new Dog());
                    componentMap.put(SupercarDealer.class, new SupercarDealer());
                }

        );

        diContainer.resolveDependency();

        UsingDiWebFrameworkProcess usingDiWebFrameworkProcess =  new UsingDiWebFrameworkProcess();
        usingDiWebFrameworkProcess.requestAccessorCallFriend();

    }

    /**
     * Execute callFriend() of annotation and delegating actions by UsingDiWebFrameworkProcess.
     * (And you can increase hit-points of sleepy cat in this method) <br>
     * (annotation, delegating の Action の callFriend() を UsingDiWebFrameworkProcess 経由で実行してみましょう。
     * (眠い猫のヒットポイントをこのメソッド内で増やしてもOK))
     */
    public void test_usingdi_UsingDiWebFrameworkProcess_callfriend_annotation_delegating() {
        // execution code here
        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();
        diContainer.registerModule(componentMap -> {
                    componentMap.put(UsingDiAnnotationAction.class, new UsingDiAnnotationAction());
                    componentMap.put(UsingDiDelegatingAction.class, new UsingDiDelegatingAction());
                    componentMap.put(UsingDiDelegatingLogic.class, new UsingDiDelegatingLogic());
                    componentMap.put(Animal.class, new Dog());
                    componentMap.put(SupercarDealer.class, new SupercarDealer());
                }
        );

        diContainer.resolveDependency();

        UsingDiWebFrameworkProcess usingDiWebFrameworkProcess =  new UsingDiWebFrameworkProcess();
        usingDiWebFrameworkProcess.requestAnnotationCallFriend();
        usingDiWebFrameworkProcess.requestDelegatingCallFriend();
    }

    /**
     * What is concrete class of instance variable "animal" of UsingDiAnnotationAction? (when registering UsingDiModule) <br>
     * (UsingDiAnnotationAction のインスタンス変数 "animal" の実体クラスは？ (UsingDiModuleを登録した時))
     */
    public void test_usingdi_whatis_animal() {
        // your answer? => It will be the same class as the instance I bind the animal variable with.
        // and your confirmation code here freely
        SimpleDiContainer diContainer = SimpleDiContainer.getInstance();

        // Test Dog
        diContainer.registerModule(componentMap -> {
                    componentMap.put(UsingDiAnnotationAction.class, new UsingDiAnnotationAction());
                    componentMap.put(Animal.class, new Dog());
                    componentMap.put(SupercarDealer.class, new SupercarDealer());
                }
        );

        diContainer.resolveDependency();

        UsingDiAnnotationAction usingDiAnnotationAction = (UsingDiAnnotationAction)diContainer.getComponent(UsingDiAnnotationAction.class);
        usingDiAnnotationAction.checkAnimal();

        // Test Cat
        diContainer.registerModule(componentMap -> {
                    componentMap.put(UsingDiAnnotationAction.class, new UsingDiAnnotationAction());
                    componentMap.put(Animal.class, new Cat());
                    componentMap.put(SupercarDealer.class, new SupercarDealer());
                }
        );

        diContainer.resolveDependency();

        usingDiAnnotationAction = (UsingDiAnnotationAction)diContainer.getComponent(UsingDiAnnotationAction.class);
        usingDiAnnotationAction.checkAnimal();
    }

    // ===================================================================================
    //                                                                        DI Container
    //                                                                        ============
    /**
     * What is DI container? <br>
     * (DIコンテナとは？)
     */
    public void test_whatis_DIContainer() {
        // your answer? => 
        // and your confirmation code here freely
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What is class or file of DI settings that defines MemberBhv class as DI component in the following Lasta Di application? <br>
     * (以下のLasta DiアプリケーションでMemberBhvクラスをDIコンポーネントとして定義しているDI設定クラスもしくはファイルは？) <br>
     * 
     * https://github.com/lastaflute/lastaflute-example-harbor
     */
    public void test_zone_search_component_on_LastaDi() {
        // your answer? => 
    }

    /**
     * What is class or file of DI settings that defines MemberBhv class as DI component in the following Spring application? <br>
     * (以下のSpringアプリケーションでMemberBhvクラスをDIコンポーネントとして定義しているDI設定クラスもしくはファイルは？) <br>
     * 
     * https://github.com/dbflute-example/dbflute-example-on-springboot
     */
    public void test_zone_search_component_on_Spring() {
        // your answer? => 
    }
}
