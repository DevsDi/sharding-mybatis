/**  
 * @Title: GoodServiceImpl.java
 * @Package com.dev.service.impl
 * @Description: 
 * @author wen.dai
 * @date 2019-10-23 18:14
 * @version V1.0  
 */
package com.dev.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entity.Goods;
import com.dev.entity.system.BaseRequest.BuilderPage;
import com.dev.mapper.GoodMapper;
import com.dev.service.GoodService;

/** 
 * @ClassName: GoodServiceImpl
 * @description: 
 * @author: wen.dai
 * @Date: 2019年10月23日 下午6:14:53
 */
@Service("goodService")
public class GoodServiceImpl implements GoodService {
	
	@Autowired
	private GoodMapper goodMapper;

	/**
	 * @Title: save
	 * @Description: 
	 * @param goods
	 * @return void
	 */  
	@Override
	public void save(Goods goods) {
		goodMapper.save(goods);
		
	}

	/**
	 * @param request 
	 * @Title: findAll
	 * @Description: 
	 * @return
	 * @return List<Goods>
	 */  
	@Override
	public List<Goods> findAll(BuilderPage<Map<String, Object>> request) {
		return goodMapper.findAll(request.getRequestData());
	}

	/**
	 * @Title: deleteAll
	 * @Description: 
	 * @return void
	 */ 
	@Override
	public void deleteAll() {
		goodMapper.deleteAll();
	}

	/**
	 * @Title: select
	 * @Description: 
	 * @param goods
	 * @return
	 * @return List<Goods>
	 */    
	public List<Goods> select(Goods goods) {
		return goodMapper.select(goods);
	}

}
