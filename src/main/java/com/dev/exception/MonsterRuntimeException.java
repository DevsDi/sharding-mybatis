package com.dev.exception;

/**
 * @ClassName: HeroRuntimeException
 * @Description: HeroRuntimeException
 * @author: wen.dai
 * @date: 2018年5月22日 下午6:44:59
 */
public class MonsterRuntimeException extends RuntimeException {

    public MonsterRuntimeException() {

    }

    public MonsterRuntimeException(Throwable cause) {
        super(cause);
    }

    public MonsterRuntimeException(String message) {
        super(message);
    }
}
