package ds.util;

import java.util.*;

 
public class Util {
	
	public static void println(String s){
        if (Symbols.DEBUG_FLAG) {
            System.out.println(s);
            System.out.flush();
        }
    }
	
	public static int max(int a, int b) {
        if (a > b) return a;
        return b;
    }
	
	public static void mySleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
	
	public static String writeArray(int A[]){
        StringBuffer s = new StringBuffer();
        for (int j = 0; j < A.length; j++)
            s.append(String.valueOf(A[j]) + " ");
        return new String(s.toString());
    }
	
	public static void readArray(String s, int A[]) {
        StringTokenizer st = new StringTokenizer(s);
        for (int j = 0; j < A.length; j++)
            A[j] = Integer.parseInt(st.nextToken());
    }
}
