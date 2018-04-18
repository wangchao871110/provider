package com.xfs.score.service;

import javax.annotation.PostConstruct;

import com.xfs.common.ContextInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfs.business.service.CalcScore;
import com.xfs.score.model.BdScoreDimension;

/**
 * 基础类，目前 添加 校验功能
 * konglc
 */
public abstract class BaseCalcScore implements CalcScore {

	@Autowired
	protected BdScoreItemService bdScoreItemService;

	@Autowired
	protected BdScoreDimensionService bdScoreDimensionService;

	@Autowired
	protected BdScoreDimensionDetailService bdScoreDimensionDetailService;

	/**
	 * 注册事件
	 */
	@PostConstruct
	public void registEvent(){
		bdScoreItemService.addEventObject(this);
	}



	public abstract boolean checkCalcScore(BdScoreDimension dimension);

	/**
	 * 处理核算公式为SQL模式
	 * @param dimension
	 * @return 当前最新版本号
     */
	public abstract void calcScore(ContextInfo cti, BdScoreDimension dimension);

}
