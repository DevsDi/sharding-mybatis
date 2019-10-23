package com.dev.entity;

import javax.validation.constraints.NotNull;

import lombok.Data;
 

/** 
 * @ClassName: Goods
 * @description: 
 * @author: wen.dai
 * @Date: 2019年10月23日 下午2:52:22
 */ 

@Data
public class Goods {
	@NotNull
    private Long goods_id;
 
    private String goods_name;
 
    private Long goods_type;
}
