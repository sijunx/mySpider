package netty_test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MyServer {

    public static void main(String[] arg)throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        //  创建服务
        ServerBootstrap bootstrap = bootstrap = new ServerBootstrap();
        //  设置boss、worker线程池
        bootstrap.group(boss, worker);
        //  设置socket工厂
        bootstrap.channel(NioServerSocketChannel.class);
        //  设置管道工厂
        bootstrap.childHandler(new ChannelInitializer<Channel>() {
            //  管道最终要设置到处理器中
            @Override
            protected void initChannel(Channel ch) throws Exception {
                //  设置解码处理器
                ch.pipeline().addLast(new StringDecoder());
                //  设置编码处理器
                ch.pipeline().addLast(new StringEncoder());
                ch.pipeline().addLast(new MyServerHandler());
            }
        });
        //绑定端口
        ChannelFuture future = bootstrap.bind(10201);
        System.out.println("start");
        //等待服务端关闭
        future.channel().closeFuture().sync();
        //  add by zard 增加监听addListener回调（高效）
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                // Perform post-closure operation
                // ...
            }
        });
    }
}
