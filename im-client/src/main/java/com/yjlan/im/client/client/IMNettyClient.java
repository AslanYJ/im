package com.yjlan.im.client.client;

import com.yjlan.im.client.handler.IMClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

/**
 * @author yjlan
 * @version V1.0
 * @Description client的启动器
 * @date 2022.01.20 15:36
 */
@Component
public class IMNettyClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(IMNettyClient.class);

    @Value("${connect.gateway.ip}")
    private String ip;

    @Value("${connect.gateway.port}")
    private Integer port;

    /**
     * 和客户端连接的工作线程
     */
    private final EventLoopGroup threadGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("im-client-work"));

    /**
     * 和服务器连接的channel
     */
    private SocketChannel socketChannel;


    @PostConstruct
    public void init() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(threadGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new IMClientHandler());
                    }
                });
        // 尝试发起连接
        ChannelFuture channelFuture = bootstrap.connect(ip, port);
        // 给异步化的连接请求加入监听器
        channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
            if (channelFuture1.isSuccess()) {
                socketChannel = (SocketChannel) channelFuture1.channel();
                LOGGER.info("已经跟TCP接入系统建立连接，TCP接入系统地址为：" + socketChannel);
            } else {
                channelFuture1.channel().close();
                threadGroup.shutdownGracefully();
            }
        });
    }


    public void sendMsg(String msg) {
        ByteBufAllocator allocator = ByteBufAllocator.DEFAULT;
        ByteBuf writeBuffer = allocator.buffer(msg.getBytes().length);
        writeBuffer.writeCharSequence(msg, Charset.defaultCharset());
        socketChannel.writeAndFlush(writeBuffer);
    }

    /**
     * 关闭连接
     */
    public void close() {
        if (socketChannel != null) {
            socketChannel.close();
        }
        threadGroup.shutdownGracefully();
    }
}