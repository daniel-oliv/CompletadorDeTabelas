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
public class DataTime {
    public int hour;
    public int minute;
    
    
    
    public DataTime(String str)
    {
        String params []= str.split(":");
        System.out.println("completadordetabelas.DataTime.<init>() params.lenght = " + params.length);
        
        hour = Integer.parseInt(params[0]);
        minute = Integer.parseInt(params[1]);
    }
    
}
