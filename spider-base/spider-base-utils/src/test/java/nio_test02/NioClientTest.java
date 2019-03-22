package nio_test02;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import netty_test.MyClientHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NioClientTest {

    public static void main(String[] arg)throws Exception{
        Bootstrap bootstrap = new Bootstrap();


        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(eventLoopGroup);

        bootstrap.channel(NioSocketChannel.class);

        bootstrap.handler(new ChannelInitializer() {
            @Override
            public void initChannel(Channel channel){
                channel.pipeline().addLast(new StringEncoder());
                channel.pipeline().addLast(new StringDecoder());
                channel.pipeline().addLast(new MyClientHandler());
            }
        });
        ChannelFuture channelFuture  = bootstrap.connect("127.0.0.1", 10201);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("请输入:");
            String str = bufferedReader.readLine();
            channelFuture.channel().writeAndFlush(str);
        }

    }
}
