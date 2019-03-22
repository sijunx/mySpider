import   java.io.*;
import   java.util.*;

public class SerialzeTest02 implements   Serializable   { //   实现序列话接口
        private   int   n;
        public   SerialzeTest02(int   n)   {   this.n   =   n;   }
        public   String   toString()   {   return   Integer.toString(n);   }
    }

