package com.example.test2_springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test2_springboot.mapper.SpeciesDao;
import com.example.test2_springboot.model.SpeciesEntity;
import com.example.test2_springboot.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("speciesService")
public class SpeciesServiceImpl extends ServiceImpl<SpeciesDao, SpeciesEntity> implements SpeciesService {
    @Autowired
    private SpeciesDao speciesDao;

    @Override
    public List<SpeciesEntity> selectBySpecies(String speciesName) {
//        this.list()
//        this.getOne()
        return this.list(
                new QueryWrapper<SpeciesEntity>()
                        .lambda().eq(SpeciesEntity::getSpeciesname, speciesName)

        );

    }

    @Override
    public List<String> selectTissueBySpecies(String speciesName) {
        return this.list(
                new QueryWrapper<SpeciesEntity>()
                        .lambda().eq(SpeciesEntity::getSpeciesname, speciesName)
                        .select(SpeciesEntity::getTissuename)
        ).stream().map(i->i.getTissuename()).collect(Collectors.toList());
    }


}
