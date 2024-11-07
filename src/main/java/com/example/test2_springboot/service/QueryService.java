package com.example.test2_springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.test2_springboot.model.Query;

public interface QueryService extends IService<Query> {

    boolean saveBook(Query query);

    boolean modify(Query query);

    boolean delete(Integer datasetid);

    IPage<Query> getPage(int currentPage, int pageSize);

    IPage<Query> getPage(int currentPage, int pageSize, Query query);
}
