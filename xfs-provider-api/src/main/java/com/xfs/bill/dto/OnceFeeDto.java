package com.xfs.bill.dto;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.xfs.bill.model.SpsFeeCorponce;
import com.xfs.bill.model.SpsFeeEmponce;

/**
 * Created by konglc on 2016-08-25.
 */
public class OnceFeeDto  implements Serializable {
    private static final long serialVersionUID = 1L;

    @JSONField(name = "spsFeeCorponces")
    private List<SpsFeeCorponce> spsFeeCorponces;

    @JSONField(name = "spsFeeEmponces")
    private List<SpsFeeEmponce> spsFeeEmponces;

    public List<SpsFeeEmponce> getSpsFeeEmponces() {
        return spsFeeEmponces;
    }

    public void setSpsFeeEmponces(List<SpsFeeEmponce> spsFeeEmponces) {
        this.spsFeeEmponces = spsFeeEmponces;
    }

    public List<SpsFeeCorponce> getSpsFeeCorponces() {
        return spsFeeCorponces;
    }

    public void setSpsFeeCorponces(List<SpsFeeCorponce> spsFeeCorponces) {
        this.spsFeeCorponces = spsFeeCorponces;
    }

    public static void main(String[] args) {
        String json = "{\n" +
                "  \"spsFeeCorponces\": [\n" +
                "    {\n" +
                "      \"item\": \"受企官\",\n" +
                "      \"cpId\": \"\",\n" +
                "      \"schemeId\": \"\",\n" +
                "      \"period\": \"2016-08\",\n" +
                "      \"source\": \"2\",\n" +
                "      \"sourceid\": \"272\",\n" +
                "      \"feeType\": \"\",\n" +
                "      \"insuredMonth\": \"\",\n" +
                "      \"officialFee\": \"1.00\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"item\": \"受企服\",\n" +
                "      \"cpId\": \"\",\n" +
                "      \"schemeId\": \"\",\n" +
                "      \"period\": \"2016-08\",\n" +
                "      \"source\": \"2\",\n" +
                "      \"sourceid\": \"273\",\n" +
                "      \"feeType\": \"\",\n" +
                "      \"insuredMonth\": \"\",\n" +
                "      \"serviceFee\": \"2.00\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"spsFeeEmponces\": [\n" +
                "    {\n" +
                "      \"reason\": \"受个官\",\n" +
                "      \"empId\": \"38160\",\n" +
                "      \"cpId\": \"1111\",\n" +
                "      \"officialFee\": \"3.00\",\n" +
                "      \"source\": \"2\",\n" +
                "      \"sourceid\": \"\",\n" +
                "      \"payType\": \"\",\n" +
                "      \"insuredMonth\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"reason\": \"受个服\",\n" +
                "      \"empId\": \"38160\",\n" +
                "      \"cpId\": \"1111\",\n" +
                "      \"serviceFee\": \"4.00\",\n" +
                "      \"source\": \"2\",\n" +
                "      \"sourceid\": \"\",\n" +
                "      \"payType\": \"\",\n" +
                "      \"insuredMonth\": \"\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        OnceFeeDto a =  JSON.parseObject(json,OnceFeeDto.class);
        System.out.println(a);
    }
}
