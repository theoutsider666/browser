package com.example.test2_springboot.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.test2_springboot.model.Gene;

public interface IGeneService extends IService<Gene> {
    boolean saveBook(Gene gene);
    boolean modify(Gene gene);
    boolean delete(Integer id);
    IPage<Gene> getPage(int currentPage, int pageSize);
    IPage<Gene> getPage(int currentPage, int pageSize, Gene gene);
}
