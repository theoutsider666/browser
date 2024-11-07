package com.example.test2_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test2_springboot.model.Gene;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GeneMapper extends BaseMapper<Gene> {
    /**
     * 自定义查询
     */
//    List<String> selectTissueBySpecies(@Param("species") String species);
//
//    long selectIdByTissueNameAndSpeciesName(@Param("tissues") String tissues, @Param("species") String species);

}
