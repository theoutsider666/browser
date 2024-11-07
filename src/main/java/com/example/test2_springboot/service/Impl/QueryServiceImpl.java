package com.example.test2_springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test2_springboot.mapper.QueryMapper;
import com.example.test2_springboot.model.Query;
import com.example.test2_springboot.service.QueryService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl extends ServiceImpl<QueryMapper, Query> implements QueryService {
    private static final Logger logger = LoggerFactory.getLogger(QueryServiceImpl.class);
    @Autowired(required = false)
    private QueryMapper bookDao;

    @Override
    public boolean saveBook(Query query) {
        return bookDao.insert(query) > 0;
    }

    @Override
    public boolean modify(Query query) {
        return bookDao.updateById(query) > 0;
    }

    @Override
    public boolean delete(Integer datasetid) {
        return bookDao.deleteById(datasetid) > 0;
    }

    @Override
    public IPage<Query> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        bookDao.selectPage(page, null);
        return page;
    }


    public IPage<Query> getPage(int currentPage, int pageSize, Query query) {
        LambdaQueryWrapper<Query> lqw = new LambdaQueryWrapper<Query>();
        lqw.eq(Strings.isNotEmpty(query.getSpecies()), Query::getSpecies, query.getSpecies());
        lqw.eq(Strings.isNotEmpty(query.getGenename()), Query::getGenename, query.getGenename());


        IPage page = new Page(currentPage, pageSize);
        bookDao.selectPage(page, lqw);
        return page;
    }
}
