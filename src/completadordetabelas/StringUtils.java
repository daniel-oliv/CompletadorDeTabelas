/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package completadordetabelas;

/**
 *
 * @author Daniel
 */
public class StringUtils {
    public static boolean startsWithNumber(String str)
    {
        return  str.substring(0,1).matches("\\d");
    }
    
    public static String removeLast(String str)
    {
        if(str.length()>0)
            return str.substring(0, str.length() - 1);
        else
            return str;
    }
    
    
    
}
