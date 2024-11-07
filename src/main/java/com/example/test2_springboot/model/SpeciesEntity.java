package com.example.test2_springboot.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author wx
 * @email wx@gmail.com
 * @date 2023-04-23 15:46:48
 */
@Data
@TableName("species")
public class SpeciesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private String speciesname;
	/**
	 *
	 */
	private String tissuename;
	/**
	 *
	 */
	private String cellvisualpath;
	/**
	 *
	 */
	private String rowpath;
	/**
	 *
	 */
	private String metapath;
	/**
	 *
	 */
	private String rawSize;
	/**
	 *
	 */
	private String metaSize;

}
