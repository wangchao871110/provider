package com.xfs.analysis;

import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.suggest.scorer.BaseScorer;
import com.hankcs.hanlp.suggest.scorer.editdistance.EditDistanceScorer;
import com.hankcs.hanlp.suggest.scorer.lexeme.IdVectorScorer;
import com.hankcs.hanlp.suggest.scorer.pinyin.PinyinScorer;
import com.xfs.analysis.dto.SysAnalysisBustype;
import com.xfs.analysis.model.AnalysisConfig;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.analysis.scorer.WordsScorer;
import com.xfs.analysis.service.SysAnalysisBustypeService;
import com.xfs.analysis.service.SysAnalysisLibraryService;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-07-06.
 */
@Service
public class ExcelAnalysisConfigService implements IRedisKeys{

    @Autowired
    SysAnalysisBustypeService sysAnalysisBustypeService;

    @Autowired
    SysAnalysisLibraryService sysAnalysisLibraryService;

    private static Suggester suggester ;

    static {
        List<BaseScorer> scorerList = new ArrayList<BaseScorer>();
        scorerList.add(new WordsScorer());
        scorerList.add(new IdVectorScorer());
        scorerList.add(new EditDistanceScorer());
        scorerList.add(new PinyinScorer());
        suggester = new Suggester(scorerList);
    }

    /**
     *  获取解析文件所需要的配置参数 -- 解析器（近义词等）
     *  @param
     *	@return 			: java.lang.String
     *  @createDate  	: 2017-03-21 14:58
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 14:58
     *  @updateAuthor  :
     */
    public AnalysisConfig analysisConfig(String analysisType){
        AnalysisConfig config = new AnalysisConfig();

        //获取分析业务对应的操作类型
        Object ob = RedisUtil.get(ANALYSIS_CONFIG_BUSTYPE_+analysisType);
        if(null != ob){
            config.setSysAnalysisBustype((List<SysAnalysisBustype>)ob);
        }else{
            SysAnalysisBustype bustype = new SysAnalysisBustype();
            bustype.setAnalysisType(analysisType);
            config.setSysAnalysisBustype(sysAnalysisBustypeService.findAll(bustype));
            RedisUtil.set(ANALYSIS_CONFIG_BUSTYPE_+analysisType,config.getSysAnalysisBustype(),3600);
        }

        //获取分析业务对应的操作下的词库列表
        Object obtitles = RedisUtil.get(ANALYSIS_CONFIG_TITLES_+analysisType);
        if(null != obtitles){
            config.setTitles((Map<String,List<AnalysisTitle>>)obtitles);
        }else {
            Map<String,List<AnalysisTitle>> titleMap = sysAnalysisLibraryService.findAllBusTypeLibrary(analysisType);
            config.setTitles(titleMap);
            RedisUtil.set(ANALYSIS_CONFIG_SUGGESTER_+analysisType,config.getTitles(),0);
            for(Map.Entry<String,List<AnalysisTitle>> titles : config.getTitles().entrySet()){
                Iterator<AnalysisTitle> iterator = titles.getValue().iterator();
                while (iterator.hasNext()){
                    if(iterator.next().getShow() < 1)
                        iterator.remove();
                }
            }
            RedisUtil.set(ANALYSIS_CONFIG_TITLES_+analysisType,config.getTitles(),3600);
        }

        //获取近义词
        Object similarObj = RedisUtil.get(ANALYSIS_CONFIG_SIMILAR_+analysisType);
        if(null != similarObj){
            config.setSplitWordMap((Map<String,String>)similarObj);
        }else{
            Map<String,List<AnalysisTitle>> titleMap = (Map<String,List<AnalysisTitle>>)RedisUtil.get(ANALYSIS_CONFIG_SUGGESTER_+analysisType);
            for(Map.Entry<String,List<AnalysisTitle>> titles : titleMap.entrySet()){
                for(AnalysisTitle analysisTitle : titles.getValue()){
                    for(String word : analysisTitle.getSimilar().split(",")){
                        config.getSplitWordMap().put(word,analysisTitle.getName());
                    }
                }
            }
            RedisUtil.set(ANALYSIS_CONFIG_SIMILAR_+analysisType,config.getSplitWordMap(),3600);
        }

        //添加近义词
        config.setSuggester(suggester);
        suggester.removeAllSentences();
        for(Map.Entry<String,String> e : config.getSplitWordMap().entrySet()){
            config.getSuggester().addSentence(e.getKey());
        }
        return config;
    }

    /**
     *  获取解析文件所需要的配置参数 -- 解析器（字相等）
     *  @param
     *	@return 			: java.lang.String
     *  @createDate  	: 2017-03-21 14:58
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 14:58
     *  @updateAuthor  :
     */
    public AnalysisConfig analysisConfigSuggester(String analysisType){
        AnalysisConfig config = analysisConfig(analysisType);
        //添加近义词
        List<BaseScorer> scorerList = new ArrayList<BaseScorer>();
        scorerList.add(new WordsScorer());
        suggester = new Suggester(scorerList);
        config.setSuggester(suggester);
        suggester.removeAllSentences();
        for(Map.Entry<String,String> e : config.getSplitWordMap().entrySet()){
            config.getSuggester().addSentence(e.getKey());
        }
        return config;
    }
}
