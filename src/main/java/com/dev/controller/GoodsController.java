package com.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.Goods;
import com.dev.entity.ResultTo;
import com.dev.mapper.GoodMapper;
import com.dev.service.impl.GoodServiceImpl;

import lombok.extern.slf4j.Slf4j;
 
/** 
 * @ClassName: GoodsController
 * @description: 
 * @author: wen.dai
 * @Date: 2019年10月23日 下午2:52:03
 */ 
@RestController
@Slf4j
public class GoodsController {
 
    @Autowired
    private GoodServiceImpl goodService;
 
 
    @GetMapping("save")
    public ResultTo<String> save(){
        for(int i= 1 ; i <= 40 ; i ++){
            Goods goods = new Goods();
            goods.setGoods_id((long) i);
            goods.setGoods_name( "shangpin" + i);
            goods.setGoods_type((long) (i+1));
            goodService.save(goods);
            
        }
        return new ResultTo<String>("success");
    }
 
    @GetMapping("query")
    public ResultTo<Object> query(){
    	
    	List<Goods> list=goodService.findAll();
    	
        return new ResultTo<Object>(list);
    }
 
    @GetMapping("delete")
    public ResultTo<String> delete(){
    	goodService.deleteAll();
        return new ResultTo<String>("success");
    }
    
    @GetMapping("select/{id}")
    public ResultTo<Object> select(@PathVariable("id") int id){
    	Goods goods=new Goods();
    	goods.setGoods_id(Long.valueOf(id));
    	List<Goods> list=goodService.select(goods);
    	log.info("Goods List:"+list);
        return new ResultTo<Object>(list);
    }
    
    
}