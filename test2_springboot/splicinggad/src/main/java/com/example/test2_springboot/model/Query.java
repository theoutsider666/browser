package com.example.test2_springboot.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("test") //到時候在更改


@Data
public class Query {
    @TableId
    private Integer datasetid;
    private String genename;
    private String species;
    private String feature;
    private String foldchange;
    private String pvalue;
    private String figure;
    private String http;

}
