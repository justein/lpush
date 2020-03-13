package com.nova.lyn.zk;

/***
 * @ClassName: ZKException
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/10 下午2:47
 * @version : V1.0
 */
public class ZKException extends RuntimeException {

    public ZKException() {
    }

    public ZKException(String message) {
        super(message);
    }

    public ZKException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZKException(Throwable cause) {
        super(cause);
    }
}
