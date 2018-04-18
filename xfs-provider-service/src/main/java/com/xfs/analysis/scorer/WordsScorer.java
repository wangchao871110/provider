package com.xfs.analysis.scorer;

import com.hankcs.hanlp.suggest.scorer.BaseScorer;

/**
 * 字段比值
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-05-24 18:27
 */
public class WordsScorer extends BaseScorer<WordsKey> {

    @Override
    protected WordsKey generateKey(String sentence)
    {
        WordsKey wordsKey = new WordsKey(sentence);
        return wordsKey;
    }

}
