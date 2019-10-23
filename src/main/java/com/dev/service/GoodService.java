/**  
 * @Title: GoodService.java
 * @Package com.dev.service
 * @Description: 
 * @author wen.dai
 * @date 2019-10-23 18:14
 * @version V1.0  
 */
package com.dev.service;

import java.util.List;

import com.dev.entity.Goods;

/** 
 * @ClassName: GoodService
 * @description: 
 * @author: wen.dai
 * @Date: 2019年10月23日 下午6:14:21
 */ 
public interface GoodService {
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
