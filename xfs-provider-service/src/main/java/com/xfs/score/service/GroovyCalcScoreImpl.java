package com.xfs.score.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Service;

import com.xfs.bs.util.GroovyCommonUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.score.model.BdScoreDimension;
import com.xfs.score.model.BdScoreDimensionDetail;

import groovy.lang.Binding;

/**
 * Created by konglc on 2016-09-25.
 */
@Service
public class GroovyCalcScoreImpl extends BaseCalcScore {

    @Override
    public boolean checkCalcScore(BdScoreDimension dimension) {
        if(null == dimension.getCalType())
            return false;
        return dimension.getCalType().equals("GROOVY_EL") || dimension.getCalType().equals("GROOVY_FILE") ? true : false;
    }

    /**
     * 处理核算公式为SQL模式
     * @param dimension
     * @return 当前最新版本号
     */
    @Override
    public void calcScore(ContextInfo cti, BdScoreDimension dimension) {
        String sql = dimension.getCalParam();
        String formula = dimension.getCalFormula();
        if(StringUtils.isBlank(sql))
            return;
        sql = sql.replace("\\r\\n"," ");
        List<Map<String,Object>> calcPareams = bdScoreDimensionService.executeDynamicSql(sql);
        if(null != calcPareams && !calcPareams.isEmpty()){
            for (Map<String,Object> calcParam : calcPareams){
                /**
                 * 根据表达式计算对应分值
                 */
                Object result = null;
                if(dimension.getCalType().equals("GROOVY_FILE")){//以脚本文件方式执行
                    try{
                        result = GroovyCommonUtil.invokeMethod("calcScore.groovy",formula,calcPareams,calcParam);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Binding binding = new Binding();
                    binding.setVariable("calcPareams",calcPareams);
                    binding.setVariable("calcParam",calcParam);
                    result = GroovyCommonUtil.invokeScriptStr(formula,binding);
                }

                /**
                 * 封装对应的评分维度明细
                 */
                BdScoreDimensionDetail detail = new BdScoreDimensionDetail();
                detail.setCalFormula(dimension.getCalFormula());
                detail.setCalType(dimension.getCalType());
                detail.setCalVersion(dimension.getCalcVersion());
                BigDecimal score = new BigDecimal(null == result ? 0 : Double.parseDouble(String.valueOf(result)));
                score.setScale(2,BigDecimal.ROUND_UP);
                //判断是否存在维度默认分值
                if(null != dimension.getDefaultScore()){
                    if(dimension.getDefaultScore().compareTo(score) > 0)
                        score = dimension.getDefaultScore();
                }
                detail.setScore(score);
                detail.setItemId(dimension.getItemId());
                detail.setWeight(dimension.getWeight());
                detail.setDimensionName(dimension.getDimensionName());
                detail.setDimId(dimension.getDimId());
                if(null == calcParam.get("score_target_id"))
                    continue;
                detail.setScoreTargetId(Integer.parseInt(String.valueOf(calcParam.get("score_target_id"))));
                detail.setScoreTargetType(dimension.getItemType());
                bdScoreDimensionDetailService.insert(cti,detail);
            }
        }

    }


    public static void main(String[] args) {
        Binding binding = new Binding();
        binding.setVariable("amount","1000");
        System.out.println(GroovyCommonUtil.invokeScriptStr("def total = amount.toBigDecimal(); return total.div(0.994).minus(total).setScale(2, BigDecimal.ROUND_HALF_UP);",binding));
    }

}
