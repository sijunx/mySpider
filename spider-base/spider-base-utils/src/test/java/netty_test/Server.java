package netty_test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 服务端
 */
public class Server {

    public static void main(String[] args) {
        //  服务启动类
        ServerBootstrap bootstrap = new ServerBootstrap();
        //  boss和worker 这两个可以理解为线程池，EventLoopGroup内部是对线程池的封装
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            //  设置线程池
            bootstrap.group(boss, worker);
            //  设置socket工厂、
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
            //  设置参数，TCP参数
            //  serverSocketchannel的设置，链接缓冲池的大小
            bootstrap.option(ChannelOption.SO_BACKLOG, 2048);
            //  socketchannel的设置,维持链接的活跃，清除死链接
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            //  socketchannel的设置,关闭延迟发送
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            //绑定端口
            ChannelFuture future = bootstrap.bind(10201);
            System.out.println("start");
            //等待服务端关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        } finally{
            //释放资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
