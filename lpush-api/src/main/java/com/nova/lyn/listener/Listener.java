package com.nova.lyn.listener;

/***
 * @ClassName: Listener
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 上午9:53
 * @version : V1.0
 */
public interface Listener {

    void onSuccess(Object... objects);
    void onFailure(Throwable throwable);
}
