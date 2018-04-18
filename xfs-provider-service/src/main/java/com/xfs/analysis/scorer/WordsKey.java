package com.xfs.analysis.scorer;

import com.hankcs.hanlp.suggest.scorer.ISentenceKey;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-05-24 18:28
 */
public class WordsKey  implements Comparable<WordsKey>, ISentenceKey<WordsKey> {

    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordsKey(String sentence)
    {
        word = sentence;
    }

    @Override
    public Double similarity(WordsKey other) {
        if(other.getWord().equals(word))
            return 100.0;
        else
            return 0.0;
    }

    @Override
    public int compareTo(WordsKey o) {
        if(o.getWord().equals(word))
            return 0;
        else
            return -1;
    }
}
