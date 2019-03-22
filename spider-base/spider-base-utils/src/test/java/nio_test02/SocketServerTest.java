package nio_test02;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class SocketServerTest {

    public static void main(String[] arg)throws Exception{
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8000));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            if(selector.select(5000) == 0){
                System.out.println(".......");
                continue;
            }
            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
            while(selectionKeyIterator.hasNext()){
                SelectionKey selectionKey = selectionKeyIterator.next();
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel = ((ServerSocketChannel)selectionKey.channel()).accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if(selectionKey.isReadable()){
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    int len = socketChannel.read(byteBuffer);
                    String str=null;
                    if(len == -1){
                        socketChannel.close();
                    }else{
                        byteBuffer.flip();
                        str = new String(byteBuffer.array());
                        System.out.println("读取到消息:"+str);
                    }
                    str = "->服务端收到";
                    ByteBuffer byteBuffer01 = ByteBuffer.wrap(str.getBytes(Charset.forName("UTF8")));
                    System.out.println("发送消息:"+str);
                    socketChannel.write(byteBuffer01);
                    selectionKey.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                    Thread.sleep(20000);
                }
                selectionKeyIterator.remove();
            }
        }
    }
}
