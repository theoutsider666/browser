package com.example.test2_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.test2_springboot.model.SpeciesEntity;

import java.util.List;

/**
 * 
 *
 * @author wx
 * @email wx@gmail.com
 * @date 2023-04-21 17:48:27
 */
public interface SpeciesService extends IService<SpeciesEntity> {
    List<SpeciesEntity> selectBySpecies(String speciesName);
    List<String> selectTissueBySpecies(String speciesName);

}

