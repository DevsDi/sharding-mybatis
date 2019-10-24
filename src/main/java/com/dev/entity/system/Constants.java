package com.dev.entity.system;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: Constants
 * @Description: 提示消息
 * @author: wen.dai
 * @date: 2018年5月22日 下午6:40:21
 */
@Configuration
public class Constants {

    public static final String CURRENT_USER_ID = "current_user_id";

    @AllArgsConstructor
    public enum ResponseEnum {

        /**
         	* 通用
         */
        OK(0 , "success"),
        FAIL(1 , "error"),
        SYSTEM_ERROR(1 , "系统异常"),
        NETWORKEXCEPTION(1 , "网络异常，请稍后重试");


        @Setter
        @Getter
        private int code;

        @Setter
        @Getter
        private String message;

    }


}
