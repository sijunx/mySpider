package netty_test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端消息处理
 * @author xiajie
 *
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    public void channelRead0(ChannelHandlerContext handlerContext, String msg) throws Exception{
        System.out.println("客户端收到消息:"+msg);
    }
}