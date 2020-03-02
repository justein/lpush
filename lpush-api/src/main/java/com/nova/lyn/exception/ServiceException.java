package com.nova.lyn.exception;

/***
 * @ClassName: ServiceException
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 上午11:51
 * @version : V1.0
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
