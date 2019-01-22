import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.*;

public class TestWriteBigFile implements Runnable{

    private static final ExecutorService executorService =  new ThreadPoolExecutor(1,1, 10000, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(), new ThreadFactoryBuilder().setNameFormat("SpiderExcutor").build(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] arg){
        TestWriteBigFile testWriteBigFile = new TestWriteBigFile();
        Thread thread = new Thread(testWriteBigFile);
        thread.start();
    }

    @Override
    public void run(){
        try {
            int icount = 900000;
            File file = new File("D://temp/a.txt");

            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            while(true) {

                outputStreamWriter.write("中国asdfasdfasdfasdfsdf111212122222222222222222222222222222222222222222222222222222222222asdfasdf");

                outputStreamWriter.flush();
                System.out.println("写入...");
            }

        }catch (Exception e){

        }



    }
}
