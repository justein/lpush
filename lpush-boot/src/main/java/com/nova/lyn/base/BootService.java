package com.nova.lyn.base;

/***
 * @ClassName: BootService
 * @Description: 服务抽象类
 * @Author: Lyn
 * @Date: 2020/2/29 下午6:18
 * @version : V1.0
 */
public abstract class BootService {

    /**由于需要构造成链，所以每个节点需要定义一个成员变量*/
    private BootService next;

    /**服务启动方法*/
    public abstract void start();
    /**服务停止方法*/
    public abstract void stop();

    /**启动下一个*/
    public void startNext() {
        if (next!=null) {
            next.start();

        }
    }

    public void stopNext() {
        if (next!=null) {
            next.stop();

        }
    }

    public BootService setNext(BootService next) {
        this.next = next;
        /**这里不是return this，应该return next。这样下次setnext的时候，就是给当前这个组件添加next了
         * 对应bootchain的52行，当前last.setNext()了以后，将最新setnext的组件赋给last，成链
         * */
        return next;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }




}
