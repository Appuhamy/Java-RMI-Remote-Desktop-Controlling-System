/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ilemo.viewer.view.other;

/**
 *
 * @author Chamindu_Appuhamy
 */
public class Encryption {

    public Encryption() {
    }
    public static String encrypting(String line){
        byte[] bytes = line.getBytes();
        String tempLine="";
        for (int i = 0; i < bytes.length; i++) {
            tempLine=tempLine+bytes[i]+"`";
        }
        String stringBytes=tempLine;
        stringBytes = stringBytes.replace("1", "!");
        stringBytes = stringBytes.replace("2", "@");
        stringBytes = stringBytes.replace("3", "#");
        stringBytes = stringBytes.replace("4", "$");
        stringBytes = stringBytes.replace("5", "%");
        stringBytes = stringBytes.replace("6", "^");
        stringBytes = stringBytes.replace("7", "&");
        stringBytes = stringBytes.replace("8", "*");
        stringBytes = stringBytes.replace("9", "(");
        stringBytes = stringBytes.replace("0", ")");        
        
        return stringBytes; 
    }
    public static String decrypting(String stringBytes){
        stringBytes = stringBytes.replace("!", "1");
        stringBytes = stringBytes.replace("@", "2");
        stringBytes = stringBytes.replace("#", "3");
        stringBytes = stringBytes.replace("$", "4");
        stringBytes = stringBytes.replace("%", "5");
        stringBytes = stringBytes.replace("^", "6");
        stringBytes = stringBytes.replace("&", "7");
        stringBytes = stringBytes.replace("*", "8");
        stringBytes = stringBytes.replace("(", "9");
        stringBytes = stringBytes.replace(")", "0");
        
        String[] split = stringBytes.split("`");
        String byteLine="";
        for (int i = 0; i <split.length; i++) {
            int tempInt=Integer.parseInt(split[i]);
            byteLine=byteLine+(char)tempInt;
        }   
        return byteLine;
    }
}
