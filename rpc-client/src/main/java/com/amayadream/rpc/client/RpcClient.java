package com.amayadream.rpc.client;

import com.amayadream.rpc.common.bean.RpcRequest;
import com.amayadream.rpc.common.bean.RpcResponse;
import com.amayadream.rpc.common.codec.RpcDecoder;
import com.amayadream.rpc.common.codec.RpcEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RPC 客户端（用于发送 RPC 请求）
 * @author :  Amayadream
 * @date :  2016.07.19 20:04
 */
public class RpcClient extends SimpleChannelInboundHandler<RpcResponse> {

    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    private final String host;
    private final int port;

    private RpcResponse response;

    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
        this.response = response;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("api caught exception", cause);
        ctx.close();
    }

    public RpcResponse send(RpcRequest request) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建并初始化Netty客户端Bootstrap对象
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new RpcEncoder(RpcRequest.class)); //编码RPC请求
                    pipeline.addLast(new RpcDecoder(RpcResponse.class));    //解码RPC请求
                    pipeline.addLast(RpcClient.this);   //处理RPC响应
                }
            });
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            //连接RPC服务器
            ChannelFuture future = bootstrap.connect(host, port).sync();
            //写入RPC请求数据并关闭连接
            Channel channel = future.channel();
            channel.writeAndFlush(request).sync();
            channel.closeFuture().sync();
            //返回RPC响应对象
            return response;
        } finally {
            group.shutdownGracefully();
        }
    }


}
