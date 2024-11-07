package com.example.test2_springboot.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("browse")
//import lombok.Data;

@Data
public class Gene {
    @TableId
    private String datasetid;
    private String project;
    private String species;
    private int samples;
    private String sex;
    private String agemean;
    private String ad;
    private String tissuetype;
    private String braak;
    private String cdr;
    private String cerad;
    private String apoestatus;
    private String accession;
    private String http;

}
/*

CREATE TABLE IF NOT EXISTS browse (
    DatasetID VARCHAR(50) PRIMARY KEY,
    Species VARCHAR(50) NOT NULL,
    Samples INT NOT NULL,
		Sex VARCHAR(50) NOT NULL,
		Age_Mean VARCHAR(50) NOT NULL,
    AD VARCHAR(50) NOT NULL,
		Brain_Region VARCHAR(50) NOT NULL,
		Braak VARCHAR(50) NOT NULL,
		CDR VARCHAR(50) NOT NULL,
		CERAD VARCHAR(50) NOT NULL,
		APOE_Status VARCHAR(50) NOT NULL,
		Accession VARCHAR(50) NOT NULL,
		Http VARCHAR(100) NOT NULL
);
* */