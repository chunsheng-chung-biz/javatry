package org.docksidestage.bizfw.debug.sorter;

import java.util.List;

import org.docksidestage.bizfw.debug.Word;

/**
 * @author zaya
 * @author chunsheng.chung
 */
public class SelectionSorter implements Sorter<Word> {
    private List<Word> words;

    public SelectionSorter() {
    }

    public SelectionSorter(List<Word> words) {
        this.words = words;
    }

    @Override
    public List<Word> sort() {
        return sort(words);
    }

    @Override
    public List<Word> sort(List<Word> wordList) {
        int n = wordList.size();
        for (int i = 0; i < n - 1; i++) {
            int m = i;
            for (int j = i + 1; j < n; j++) {
                if (wordList.get(j).getWord().compareTo(wordList.get(m).getWord()) < 0) { // Not comparing to i but m
                    m = j;
                }
            }
            Word t = wordList.get(m);
            wordList.set(m, wordList.get(i));
            wordList.set(i, t);
        }
        return wordList;
    }
}
