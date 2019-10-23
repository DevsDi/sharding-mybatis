/**  
 * @Title: GoodMapper.java
 * @Package com.dev
 * @Description: 
 * @author wen.dai
 * @date 2019-10-23 17:18
 * @version V1.0  
 */
package com.dev.mapper;

import java.util.List;

import com.dev.entity.Goods;

/** 
 * @ClassName: GoodMapper
 * @description: 
 * @author: wen.dai
 * @Date: 2019年10月23日 下午5:18:41
 */
public interface GoodMapper {

	/**
	 * @Title: save
	 * @Description: 
	 * @param goods
	 * @return void
	 */    
	void save(Goods goods);

	/**
	 * @Title: findAll
	 * @Description: 
	 * @return
	 * @return List<Goods>
	 */    
	List<Goods> findAll();

	/**
	 * @Title: deleteAll
	 * @Description: 
	 * @return void
	 */    
	void deleteAll();

	/**
	 * @Title: select
	 * @Description: 
	 * @param goods
	 * @return
	 * @return List<Goods>
	 */    
	List<Goods> select(Goods goods);
	
}
