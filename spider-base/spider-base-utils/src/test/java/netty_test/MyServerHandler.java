package netty_test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务端消息处理
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    /** 消息处理 */
    public void channelRead0(ChannelHandlerContext handlerContext, String msg) throws Exception{
        System.out.println("channelRead0-----------");
        System.out.println("服务端收到"+handlerContext.channel().remoteAddress()+"发来的消息："+msg);
        handlerContext.channel().writeAndFlush("hi 服务已经收到你IP="+handlerContext.channel().remoteAddress()+"发的消息");
        handlerContext.writeAndFlush("hi 服务已经收到你发的消息");
    }

    /**
     * 当有新的客户端连接到服务端
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive.......新的客户端连接到服务端");
    }

    /**
     * 当客户端断开与服务端的连接
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive.......客户端断开与服务端的连接");
    }

    /**
     * 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
