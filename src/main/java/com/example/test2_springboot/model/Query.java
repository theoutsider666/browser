package com.example.test2_springboot.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("test") //到時候在更改


@Data
public class Query {
    @TableId
    private Integer datasetid;
    private String gene;
    private String species;
    private String f1;
    private String f2;
    private String f3;
    private String f4;
    private String pvalue1;
    private String pvalue2;
    private String pvalue3;
    private String pvalue4;
    private String fold1;
    private String fold2;
    private String fold3;
    private String fold4;
    private String figure1;
    private String figure2;
    private String figure3;
    private String figure4;
    private String project;

}
