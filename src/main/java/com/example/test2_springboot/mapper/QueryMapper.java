package com.example.test2_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test2_springboot.model.Query;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QueryMapper extends BaseMapper<Query> {

}
