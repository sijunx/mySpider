package nio_test02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GenericFutureListener;
import netty_test.MyServerHandler;

public class NioServerTest {

    public static void main(String[] arg)throws Exception{

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        serverBootstrap.group(bossGroup, workerGroup);

        serverBootstrap.channel(NioServerSocketChannel.class);

        serverBootstrap.childHandler(new ChannelInitializer<Channel>() {
            @Override
            public void initChannel(Channel channel){
                channel.pipeline().addLast(new StringDecoder());
                channel.pipeline().addLast(new StringEncoder());
                channel.pipeline().addLast(new MyServerHandler());
            }
        });
        ChannelFuture channelFuture = serverBootstrap.bind(10201);
        System.out.println("服务端启动了......");
        channelFuture.addListener(new ChannelFutureListener(){
            @Override
            public void operationComplete(ChannelFuture channel){
                System.out.println("监听到结束了.......");
            }
        });
        //等待服务端关闭
        channelFuture.channel().closeFuture().sync();
    }
}
