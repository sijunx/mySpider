import java.io.*;
import java.util.Scanner;

public class TestCloseAble implements AutoCloseable{

    private static OutputStream outputStream;

    public static void main(String[] arg)throws Exception {

//        bufferedReader();

//        testReadInMemory();

//        testScanner();

        test998();
    }

    public static String test998(){
        try{
            int icount = 100/100;
        }catch (Exception e){}
        finally {
            System.out.println("sfsadf");
        }
        System.out.println("111");
        return "11";
    }


    public static void testScanner()throws Exception{
        File file = new File("D://temp/a.txt");
        InputStream inputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        OutputStream outputStream = new FileOutputStream(new File("D://temp/b.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

        while(scanner.hasNext()){
            String line = scanner.next();
            System.out.println("line:"+line);
            bufferedWriter.write(line);
        }
    }

    public static void testReadInMemory()throws Exception{
        File file = new File("D://temp/a.txt");

        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        ;

        int icount = 1;
        while(true){
            int lineNum = inputStreamReader.read();
            System.out.println("lineNum:"+lineNum);
        }


    }

    public static void bufferedReader()throws Exception{
        File fileFrom = new File("D://temp/a.txt");
        File fileTo = new File("D://temp/b.txt");
        String path = "D://temp/a.txt";

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileFrom), getCode(path));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileTo), "UTF-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        String line = null;
        while((line=bufferedReader.readLine())!=null){
            System.out.println("line:"+line);
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();

    }

    // 获取编码格式 gb2312,UTF-16,UTF-8,Unicode,UTF-8
    public static String getCode(String path) throws Exception {
        InputStream inputStream = new FileInputStream(path);
        byte[] head = new byte[3];
        inputStream.read(head);
        String code = "GBK"; // 或GBK
        if (head[0] == -1 && head[1] == -2)
            code = "UTF-16";
        else if (head[0] == -2 && head[1] == -1)
            code = "Unicode";
        else if (head[0] == -17 && head[1] == -69 && head[2] == -65)
            code = "UTF-8";
        inputStream.close();
        System.out.println("head0:"+head[0]+"   文件编码格式:"+code);
        return code;
    }

    public static void test02()throws IOException{
        File fileFrom = new File("D://temp/a.txt");
        File fileTo = new File("D://temp/b.txt");
        char[] buff = new char[1024];

        FileReader fileReader = new FileReader(fileFrom);
        int charNums = fileReader.read(buff);
        System.out.println("charNums:"+charNums + "     " +new String(buff));

        FileWriter fileWriter = new FileWriter(fileTo);
        fileWriter.write(buff);
        fileWriter.flush();

    }

    public static void test01()throws IOException{
        File fileFrom = new File("C://temp/a.txt");
        File fileTo = new File("C://temp/b.txt");

        byte[] myByte  = new byte[1024];
        try (InputStream inputStream = new FileInputStream(fileFrom)){
            inputStream.read(myByte);
        }

        OutputStream outputStream = new FileOutputStream(fileTo);
        outputStream.write(myByte);

        System.out.println("xxtttt");
    }



    @Override
    public void close(){
        try{
            System.out.println("xxx");
        }catch (Exception e){
           e.printStackTrace();
        }
    }
}
