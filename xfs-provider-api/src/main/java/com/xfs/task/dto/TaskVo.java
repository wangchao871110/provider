package com.xfs.task.dto;

import java.util.List;

/**
 * 社保公积金看板数据传输实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:49:18
 * @version 	: V1.0
 */
public class TaskVo {
	private Integer peopleCountNumber;// 总人数
	private Integer addNumber;// 增员人数
	private Integer subNumber;// 减员人数
	private Integer supNumber;// 补缴人数
	private Integer dxxshNumber;// 待信息审核
	private Integer dbsddNumber;// 待报送当地
	private Integer dsjclNumber;// 待收集材料
	private Integer dtjzfNumber;// 待提交政府
	private Integer blcgNumber;// 办理成功
	private Integer blycNumber;// 办理异常
	private Integer dws;//等待网申
	private Integer wsz;//网申中
	private List<TaskMapVo> taskMapVo;// 每个城市参保人数
	private Integer ddsbNumber;// 等待申报人数
	private Integer wszNumber;// 申报中人数
	
	public Integer getPeopleCountNumber() {
		return peopleCountNumber;
	}
	public void setPeopleCountNumber(Integer peopleCountNumber) {
		this.peopleCountNumber = peopleCountNumber;
	}
	public Integer getAddNumber() {
		return addNumber;
	}
	public void setAddNumber(Integer addNumber) {
		this.addNumber = addNumber;
	}
	public Integer getSubNumber() {
		return subNumber;
	}
	public void setSubNumber(Integer subNumber) {
		this.subNumber = subNumber;
	}
	public Integer getSupNumber() {
		return supNumber;
	}
	public void setSupNumber(Integer supNumber) {
		this.supNumber = supNumber;
	}
	public Integer getDxxshNumber() {
		return dxxshNumber;
	}
	public void setDxxshNumber(Integer dxxshNumber) {
		this.dxxshNumber = dxxshNumber;
	}
	public Integer getDbsddNumber() {
		return dbsddNumber;
	}
	public void setDbsddNumber(Integer dbsddNumber) {
		this.dbsddNumber = dbsddNumber;
	}
	public Integer getDsjclNumber() {
		return dsjclNumber;
	}
	public void setDsjclNumber(Integer dsjclNumber) {
		this.dsjclNumber = dsjclNumber;
	}
	public Integer getDtjzfNumber() {
		return dtjzfNumber;
	}
	public void setDtjzfNumber(Integer dtjzfNumber) {
		this.dtjzfNumber = dtjzfNumber;
	}
	public Integer getBlcgNumber() {
		return blcgNumber;
	}
	public void setBlcgNumber(Integer blcgNumber) {
		this.blcgNumber = blcgNumber;
	}
	public Integer getBlycNumber() {
		return blycNumber;
	}
	public void setBlycNumber(Integer blycNumber) {
		this.blycNumber = blycNumber;
	}
	public List<TaskMapVo> getTaskMapVo() {
		return taskMapVo;
	}
	public void setTaskMapVo(List<TaskMapVo> taskMapVo) {
		this.taskMapVo = taskMapVo;
	}
	public Integer getDdsbNumber() {
		return ddsbNumber;
	}
	public void setDdsbNumber(Integer ddsbNumber) {
		this.ddsbNumber = ddsbNumber;
	}
	public Integer getWszNumber() {
		return wszNumber;
	}
	public void setWszNumber(Integer wszNumber) {
		this.wszNumber = wszNumber;
	}

	public Integer getDws() {
		return dws;
	}

	public void setDws(Integer dws) {
		this.dws = dws;
	}

	public Integer getWsz() {
		return wsz;
	}

	public void setWsz(Integer wsz) {
		this.wsz = wsz;
	}
}
