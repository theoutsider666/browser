package com.example.test2_springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test2_springboot.mapper.GeneMapper;
import com.example.test2_springboot.model.Gene;
import com.example.test2_springboot.service.IGeneService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GeneServiceImpl extends ServiceImpl<GeneMapper, Gene> implements IGeneService {
    private static final Logger logger = LoggerFactory.getLogger(GeneServiceImpl.class);
    @Autowired(required = false)
    private GeneMapper bookDao;
    @Override
    public boolean saveBook(Gene gene) {
        return bookDao.insert(gene)>0;
    }

    @Override
    public boolean modify(Gene gene) {
        return bookDao.updateById(gene)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return bookDao.deleteById(id)>0;
    }

    @Override
    public IPage<Gene> getPage(int currentPage, int pageSize) {
        IPage page=new Page(currentPage,pageSize);
        bookDao.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Gene> getPage(int currentPage, int pageSize, Gene gene) {
        LambdaQueryWrapper<Gene> lqw = new LambdaQueryWrapper<Gene>();
        lqw.eq(Strings.isNotEmpty(gene.getSpecies()), Gene::getSpecies, gene.getSpecies());
        lqw.eq(Strings.isNotEmpty(gene.getTissuetype()), Gene::getTissuetype, gene.getTissuetype());

        if (gene.getCdr().equals("true"))
        {
            lqw.ne( Gene::getCdr, "NA");
        }
        else if (gene.getCdr().equals("false")) {
            lqw.eq( Gene::getCdr, "NA");
        }
        if (gene.getBraak().equals("true"))
        {
            lqw.ne( Gene::getBraak, "NA");
        }
        else if (gene.getBraak().equals("false")) {
            lqw.eq( Gene::getBraak, "NA");
        }
        if (gene.getCerad().equals("true"))
        {
            lqw.ne( Gene::getCerad, "NA");
        }
        else if (gene.getCerad().equals("false")) {
            lqw.eq( Gene::getCerad, "NA");
        }
        if (gene.getApoestatus().equals("true"))
        {
            lqw.ne( Gene::getApoestatus, "NA");
        }
        else if (gene.getApoestatus().equals("false")) {
            lqw.eq( Gene::getApoestatus, "NA");
        }



        IPage page = new Page(currentPage,pageSize);
        bookDao.selectPage(page,lqw);
        return page;
    }
}

