package com.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.dev.entity.Goods;
import com.dev.entity.system.BaseRequest;
import com.dev.entity.system.Constants;
import com.dev.entity.system.LoginUser;
import com.dev.entity.system.ResultTo;
import com.dev.exception.MonsterException;
import com.dev.interceptor.CurrentUser;
import com.dev.interceptor.NeedAuth;
import com.dev.service.impl.GoodServiceImpl;
import com.dev.util.StringUtil;

import lombok.extern.slf4j.Slf4j;
 
/** 
 * @ClassName: GoodsController
 * @description: 
 * @author: wen.dai
 * @Date: 2019年10月23日 下午2:52:03
 */ 
@RestController
@Slf4j
@NeedAuth
public class GoodsController {
 
    @Autowired
    private GoodServiceImpl goodService;
    
    @Autowired
    private KeyGenerator keyGenerator;
 
 
    @PostMapping("s/{start}")
    public ResultTo<String> save(@PathVariable(value="start") int start){
        for(int i= start ; i < start+10 ; i ++){
            Goods goods = new Goods();
            goods.setGoods_id((long) i);
            goods.setGoods_name( "product_" + i);
            goods.setGoods_type((long) (new Random().nextInt(1000)));
            goodService.save(goods);
            
        }
        return new ResultTo<String>("success");
    }
 
    @PostMapping("query")
    @NeedAuth
    public ResultTo<Object> query(@CurrentUser LoginUser loginUser,@RequestBody BaseRequest.BuilderPage<Map<String, Object>> request) {
    	
    	log.info("loginUser:"+loginUser);
    	ResultTo<Object> baseResponse = new ResultTo<>();
    	List<Goods> list=goodService.findAll(request);
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("list", list);
    	map.put("totalNum", request.getTotalNum());
    	map.put("pageNo", request.getPageNo());
    	map.put("pageSize", request.getPageSize());
    	baseResponse.setResult(map);
    	return baseResponse;
    }
 
    @PostMapping("d")
    public ResultTo<String> delete(){
    	goodService.deleteAll();
        return new ResultTo<String>("success");
    }
    
    @PostMapping("select")
    public ResultTo<Object> select(@RequestBody BaseRequest.Builder<Map<String, Object>> request) throws MonsterException{
    	
    	ResultTo<Object> baseResponse = new ResultTo<>();
    	 Map<String, Object> paramMap = request.getRequestData();
         if (paramMap == null) {
             baseResponse.commonException(Constants.ResponseEnum.SYSTEM_ERROR.getMessage());
             return baseResponse;
         }
         
    	Goods goods=new Goods();
    	
		try {
			goods.setGoods_id(Long.parseLong(StringUtil.getNotNullStr(paramMap.get("id"))));
			List<Goods> list = goodService.select(goods);
			log.info("Goods List:"+list);
		    return new ResultTo<Object>(list);
		} catch (NumberFormatException e) {
			log.error("exception:"+e);
			throw new MonsterException(e.toString());
//            return baseResponse;
		}
		
    }
    
    
}