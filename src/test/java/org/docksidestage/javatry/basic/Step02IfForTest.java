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
package org.docksidestage.javatry.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() {
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) { // sea == 7
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) { // will get into this branch first
            sea = 7;
        } else if (sea >= 903) { // Although sea >= 903 is true,
                                 // the program gets to the previous branch as it's in front
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (land && sea >= 904) { // land is false
            sea = 7;
        } else if (sea >= 903 || land) { // Here
            sea = 8;
            if (!land) { // Here
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else {
            sea = 9;
        }
        if (land) { // Another if, will get into here
            sea = 10;
        }
        log(sea); // your answer? => 10
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList(); // ["broadway", "dockside", "hangar", "magiclamp"]
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => "dockside"
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList(); // ["broadway", "dockside", "hangar", "magiclamp"]
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => "magiclamp"
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList(); // ["broadway", "dockside", "hangar", "magiclamp"]
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) { // "broadway" is skipped
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) { // "hanger" breaks the loop
                break;
            }
        }
        log(sea); // your answer? => "hangar"
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList(); // ["broadway", "dockside", "hangar", "magiclamp"]
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            // Not sure about forEach, I assume that "stage" is the name of each item
            // And -> {...} is a assigned function to execute with each item (stage in this case)?
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) { //"dockside"
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => "dockside"
    }
    // Need to check the detail of forEach method!

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stage = prepareStageList();
        List<String> answer = new ArrayList<String>();
        for (String s: stage) {
            if (s.contains("a")) {
                answer.add(s);
            }
        }
        for (String s: answer) {
            log(s);
        }
    }
    /**
    I always want to write python syntax..... (ex. answer = [s for s in stage if 'a' in s])
    Also forgot to declare type,add parenthesis etc... XD
     */

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    // Is it possible to "break" in forEach?
    // I checked this StackOverflow　https://stackoverflow.com/questions/23308193/break-or-return-from-java-8-stream-foreach
    // But seems not good to use exception handling...
    // Seems this one will work: https://www.baeldung.com/java-break-stream-foreach but it's Java 9
    // I can only use this dirty method to avoid error...
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList(); // ["broadway", "dockside", "hangar", "magiclamp"]
        String[] seas = {null, null};
        int[] index = {0};
        stageList.forEach(stage -> {
                    if (stage.startsWith("br")) {
                        return;
                    }
                    seas[index[0]] = stage;
                    if (stage.contains("ga")) {
                        index[0] = 1;
                    }
        });
        String sea = seas[0];
        log(sea); // should be same as before-fix: hangar
    }

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * Write an equivalent of python's expression:
     * answer = [s+"1" for s in stage if "ga" in s else s+"0"]
     * with forEach method.
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
        List<String> answer = new ArrayList<>();
        prepareStageList().forEach(stage -> {
            if (stage.contains("ga")) {
                answer.add(stage+"1");
            }
            else {
                answer.add(stage+"0");
            }
        });
        log(answer); // [broadway0, dockside0, hangar1, magiclamp0]
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
