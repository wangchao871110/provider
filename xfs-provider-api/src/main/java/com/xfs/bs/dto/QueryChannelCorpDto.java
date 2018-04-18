package com.xfs.bs.dto;

import java.util.Date;
import java.util.List;

/**
 * 渠道企业查询类
 * Created by yangf on 2016/10/11.
 */
public class QueryChannelCorpDto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orgId;
    private String isAnswer;
    private List<String> isLegalizes;
    private String startTime;
    private String endTime;
    private String seachWord;
    private String sname;
    private String legalizeStatus;
    private String answerStatus;

    private String state;
    
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(String isAnswer) {
        this.isAnswer = isAnswer;
    }

    public List<String> getIsLegalizes() {
        return isLegalizes;
    }

    public void setIsLegalizes(List<String> isLegalizes) {
        this.isLegalizes = isLegalizes;
    }

    
    public String getStartTime() {
		return startTime;
	}

	public void setstartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSeachWord() {
        return seachWord;
    }

    public void setSeachWord(String seachWord) {
        this.seachWord = seachWord;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getLegalizeStatus() {
        return legalizeStatus;
    }

    public void setLegalizeStatus(String legalizeStatus) {
        this.legalizeStatus = legalizeStatus;
    }

    public String getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus;
    }
}
