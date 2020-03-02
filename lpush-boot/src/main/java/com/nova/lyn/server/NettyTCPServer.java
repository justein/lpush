package com.nova.lyn.server;

import com.nova.lyn.event.ServerEventSubscrib;
import com.nova.lyn.exception.ServiceException;
import com.nova.lyn.listener.Listener;
import com.nova.lyn.service.BaseService;
import com.nova.lyn.utils.EventBusCenter;
import io.netty.channel.EventLoopGroup;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/***
 * @ClassName: NettyTCPServer
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/3/1 下午5:44
 * @version : V1.0
 */
public class NettyTCPServer extends BaseService implements Server {

    protected int PORT;

    public enum State{ CREATED,INITIALIZED,STARTED,STOPED }

    private final AtomicReference<State> serverState = new AtomicReference<State>(State.CREATED);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public NettyTCPServer(int PORT) {
        this.PORT = PORT;
    }


    @Override
    public void init() {

        /**服务初始化时将事件处理器注册到总线*/
        EventBusCenter.newInstance().register(new ServerEventSubscrib());

        if (!serverState.compareAndSet(State.CREATED, State.INITIALIZED)) {
            throw new ServiceException("Server has already been initialized");
        }
        System.out.println("TCPServer当前运行状态："+serverState.get());
    }

    @Override
    public void start(Listener listener) {
        System.out.println("创建NettyTCPServer，正在进行");
        listener.onSuccess(9800);
    }

    @Override
    public void stop(Listener listener) {
        System.out.println("NettyTCPServer停止");
    }

    @Override
    public CompletableFuture<Boolean> start() {
        return super.start();
    }

    @Override
    public CompletableFuture<Boolean> stop() {
        return super.stop();
    }

    @Override
    public boolean isRunning() {
        return serverState.get() == State.STARTED;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
