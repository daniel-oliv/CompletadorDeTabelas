/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package completadordetabelas;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Escala {
//    ArrayList<String[]> strUteis, strSab, strDom;
    String strUteis, strSab, strDom, strDefault;
    ArrayList<String[]> table;
    static String columnSeparator = "!";
    static String lineSeparator = "^";
    static String csvSeparator = ";";
    
    private void parse()
    {
//        strUteis = new ArrayList<>();
//        strSab = new ArrayList<>();
//        strDom = new ArrayList<>();

        strUteis = "";
        strSab = "";
        strDom = "";
        strDefault = ""; 
        
        String dayType = "none";

        for (int i = 0; i < table.size(); i++) 
        {
            String line [] = table.get(i); 
            String c1 = line[0];
            String c2 = line[1];
            
            //! day time will keep the last day type read
            switch(c1) 
            {
                case "UTEIS":
                  dayType = "UTEIS";
                  break;
                case "SABADO":
                  dayType = "SABADO";
                  break;
                case "DOMINGO":
                  dayType = "DOMINGO";
                  break;
                default:
            }
            
            switch(dayType) 
            {
                case "UTEIS":
                  strUteis += String.join(columnSeparator, line) + lineSeparator;
                  break;
                case "SABADO":
                  strSab += String.join(columnSeparator, line) + lineSeparator;
                  break;
                case "DOMINGO":
                  strDom += String.join(columnSeparator, line) + lineSeparator;
                  break;
                default:
                  strDefault += String.join(columnSeparator, line) + lineSeparator;
            }
        }
        strDefault = StringUtils.removeLast(strDefault);
        strUteis = StringUtils.removeLast(strUteis);
        strSab = StringUtils.removeLast(strSab);
        strDom = StringUtils.removeLast(strDom);

//        System.out.println("strDefault : " + strDefault);
//        System.out.println("strUteis : " + strUteis);
//        System.out.println("strSab : " + strSab);
//        System.out.println("strDom : " + strDom);
        
    }
    public static String [] ESCALAS_KEYS = {"DEFAULT", "UTEIS", "SABADO", "DOMINGO"};
    public String[] toCSV()
    {
        String data [] = {strDefault, strUteis, strSab, strDom }; 
        //return String.join(csvSeparator, data);
        return data;
    }
    
    
    Escala(ArrayList<String[]> table)
    {
        this.table = (ArrayList<String[]>)table.clone();
        System.out.println("Escala : " + table.get(0)[0]);
        this.parse();
    }
}
