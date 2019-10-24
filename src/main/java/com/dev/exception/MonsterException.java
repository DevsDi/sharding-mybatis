package com.dev.exception;


/**
 * @ClassName: HeroException
 * @Description: HeroException
 * @author: wen.dai
 * @date: 2018年5月22日 下午6:43:39
 */
public class MonsterException extends Exception {

    public MonsterException() {
        super();
    }

    public MonsterException(Throwable cause) {
        super(cause);
    }

    public MonsterException(String message) {
        super(message);
    }

    public MonsterException(String message , Throwable throwable) {
        super(message , throwable);
    }
}
