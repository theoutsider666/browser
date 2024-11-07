package com.example.test2_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test2_springboot.model.SpeciesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author wx
 * @email wx@gmail.com
 * @date 2023-04-21 17:48:27
 */
@Mapper
@Repository
public interface SpeciesDao extends BaseMapper<SpeciesEntity> {
//    List<String> selectTissueBySpecies(@Param("speciesname") String speciesname);
//List<SpeciesEntity> selectTissueBySpecies(@Param("speciesname") String speciesname);
	
}
