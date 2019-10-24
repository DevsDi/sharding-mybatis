package com.dev.entity.system;

import lombok.Data;
import lombok.ToString;

/** 
 * @ClassName: LoginUser
 * @description: 获取登录信息
 * @author: wen.dai
 * @Date: 2019年7月16日 上午11:25:35
 */ 
@ToString 
@Data
public class LoginUser {

    private Integer id;
    private String code;
    private String name;
}
