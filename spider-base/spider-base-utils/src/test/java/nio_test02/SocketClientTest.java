package nio_test02;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class SocketClientTest {
    public static void main(String[] arg)throws Exception{
        Selector selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while(true){
            if(selector.select(5000) == 0){
                System.out.println("...");
                continue;
            }
            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
            while(selectionKeyIterator.hasNext()){
                SelectionKey selectionKey = selectionKeyIterator.next();
                if(selectionKey.isConnectable()){
                    SocketChannel socketChannel01 = (SocketChannel) selectionKey.channel();
                    if(socketChannel01.isConnectionPending()){
                        socketChannel01.finishConnect();
                    }
                    socketChannel01.configureBlocking(false);
                    socketChannel01.write(ByteBuffer.wrap("我来自客户端".getBytes(Charset.forName("UTF8"))));
                    socketChannel01.register(selector, SelectionKey.OP_READ);
                }
                if(selectionKey.isReadable()){
                    SocketChannel socketChannel02 = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    socketChannel02.read(byteBuffer);
                    System.out.println("收到信息:"+new String(byteBuffer.array(), "UTF8"));
                    ByteBuffer byteBuffer02 = ByteBuffer.wrap("客户端来信了".getBytes(Charset.forName("UTF8")));
                    socketChannel02.write(byteBuffer02);
                }
            }
        }
    }
}
