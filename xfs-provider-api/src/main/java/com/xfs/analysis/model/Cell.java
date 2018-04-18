package com.xfs.analysis.model;

import com.alibaba.fastjson.JSONObject;

/**
 * @Project: xfs-recommender
 * <p></p>
 * @Author: Run Main
 * @Date: 2017-03-03
 * @Copyright: 2017 www.xinfushe.com Inc. All rights reserved.
 */
public class Cell {

    private String text;//原文本

    private int colunm; //所在列

    private String targetText;//对应文本key

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColunm() {
        return colunm;
    }

    public void setColunm(int colunm) {
        this.colunm = colunm;
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public static Cell creatCell(int colunm, String text) {
        Cell cell = new Cell();
        cell.setColunm(colunm);
        cell.setText(text);
        return cell;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
