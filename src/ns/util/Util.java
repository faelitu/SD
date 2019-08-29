/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ns.util;

/**
 *
 * @author Fellipe
 */
public class Util {
	
	public static void println(String s){
        if (Symbols.DEBUG_FLAG) {
            System.out.println(s);
            System.out.flush();
        }
    } 
        
}
