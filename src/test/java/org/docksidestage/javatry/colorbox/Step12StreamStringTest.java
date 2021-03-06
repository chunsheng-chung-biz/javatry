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
package org.docksidestage.javatry.colorbox;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, using Stream API you can. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author chunsheng.chung
 */
public class Step12StreamStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * What is color name length of first color-box? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .findFirst()
                .map(colorBox -> colorBox.getColor().getColorName())
                .map(colorName -> colorName.length() + " (" + colorName + ")")
                .orElse("*not found");
        log(answer); // 5
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        /*
         done chung こちらの右辺にカーソルを合わせて option + enter を実行しましょう by subaru (2020/05/20)
         Replace ... が表示されるのでこちらを実行すると良いです。
         done chung まだ最適化してないとみえますが、わからないなら聞いてください。午前中の解説でもComparatorの便利メソッドあると話しましたので。 by winkichanwi 20200521
        */

        /*
         done chung ここで get メソッドを呼ぶと文字列がなかったときに落ちてしまいます。 by subaru (2020/05/20)
         orElse を使う、または Optional で変数化して、answer が存在するかどうかでログ出力を変えるのが良いです。
        */
        Optional<String> answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass() == String.class)
                .map(object -> (String) object)
                .max(Comparator.comparingInt(String::length));

        log(answer);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        // done こちらも get メソッドを見直しましょう。 by subaru (2020/05/20)
        Optional<Integer> max = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass() == String.class)
                .map(object -> ((String) object).length())
                .max(Comparator.comparing(Integer::valueOf));
        Optional<Integer> min = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass() == String.class)
                .map(object -> ((String) object).length())
                .min(Comparator.comparing(Integer::valueOf));
        log(max.orElse(0) - min.orElse(0));
    }

    // has small #adjustmemts from ClassicStringTest
    //  o sort allowed in Stream
    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (sort allowed in Stream)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (Streamでのソートありで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        Optional<String> answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass()==String.class)
                .map(object -> (String)object)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .skip(1)
                .findFirst();
        log(answer);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass()==String.class)
                .map(content -> (String)content)
                // done chung [comment] mapToIntでsumができる by winkichanwi 20200521
                .mapToInt(String::length)
                .sum();
        log(answer);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        // done chung comporatorの最適化 by winkichanwi 20200521
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        Optional<String> answer = colorBoxList.stream()
                .map(colorBox -> colorBox.getColor().getColorName())
                .max(Comparator.comparingInt(String::length));
        log(answer);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List answer = colorBoxList.stream()
                .filter(
                        colorBox -> colorBox.getSpaceList().stream()
                                .map(boxSpace -> boxSpace.getContent())
                                .filter(content -> content != null)
                                .filter(content -> content.getClass() == String.class)
                                // [comment] うまいですね by winkichanwi 2020521
                                .anyMatch(content -> ((String) content).startsWith("Water"))
                )
                .map(colorBox -> colorBox.getColor().getColorName())
                .collect(Collectors.toList());
        log(answer);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List answer = colorBoxList.stream()
                .filter(
                        colorBox -> colorBox.getSpaceList().stream()
                                .map(boxSpace -> boxSpace.getContent())
                                .filter(content -> content != null)
                                .filter(content -> content.getClass() == String.class)
                                .anyMatch(content -> ((String) content).endsWith("front"))
                )
                .map(colorBox -> colorBox.getColor().getColorName())
                .collect(Collectors.toList());
        log(answer);
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass() == String.class)
                .map(content -> (String)content)
                .filter(content -> content.endsWith("front"))
                .map(str -> str.indexOf("front"))
                .collect(Collectors.toList());
        log(answer);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass() == String.class)
                .map(content -> (String)content)
                .filter(
                        // done chung splitを使うのやり方もやってみてください by winkichanwi 20200521
                        content -> content.split("ど", -1).length > 2
                )
                .map(str -> str.lastIndexOf('ど'))
                .collect(Collectors.toList());
        log(answer);
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass() == String.class)
                .map(content -> (String)content)
                .filter(content -> content.endsWith("front"))
                .map(str -> str.charAt(0))
                .collect(Collectors.toList());
        log(answer);
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass() == String.class)
                .map(content -> (String)content)
                .filter(content -> content.startsWith("Water"))
                .map(str -> str.charAt(str.length()-1))
                .collect(Collectors.toList());

        log(answer);
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass() == String.class)
                .map(content -> (String)content)
                .filter(content -> content.contains("o"))
                .map(content -> content.replace("o", ""))
                // done chung [comment] ここmapToIntするなら sumができる by winkichanwi (2020521)
                .mapToInt(String::length)
                .sum();

        // done chung -1が出力しちゃう場合はどいう意味？ by winkichanwi 20200521
        // It's just a default value if there's problem while getting result from Optional (e.g., null).
        // Not needed anymore after changing to mapToInt()
        log(answer);
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass() == File.class)
                .map(content -> ((File) content).getPath())
                .map(content -> content.replace("/", "\\"))
                .collect(Collectors.toList());

        log(answer);
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        Integer answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content != null)
                .filter(content -> content.getClass() == YourPrivateRoom.DevilBox.class)
                .map(content -> (YourPrivateRoom.DevilBox)content)
                // It is also possible to put the following 3 lines in safeGetDevilString
                .peek(YourPrivateRoom.DevilBox::wakeUp)
                .peek(YourPrivateRoom.DevilBox::allowMe)
                .peek(YourPrivateRoom.DevilBox::open)
                // done chung [comment] ここmapToIntするなら sumができる by winkichanwi (2020521)
                .mapToInt(box -> safeGetDevilString(box).length())
                .sum();

        log(answer);
    }

    public String safeGetDevilString(YourPrivateRoom.DevilBox devilBox) {
        String str;
        try {
            str = devilBox.getText();
        } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
            str = "";
        }
        return str;
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // has small #adjustmemts from ClassicStringTest
    //  o comment out because of too difficult to be stream?
    ///**
    // * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_flat() {
    //}
    //
    ///**
    // * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_nested() {
    //}
}
