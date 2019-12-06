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
public class EscalaParser {
    
    public static ArrayList<int[]> findWordsPositions(ArrayList<String[]> table, String word)
    {
        //System.out.print("findWordsPositions - word: " + word + "   ");
        ArrayList<int[]> positions = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            String line [] = table.get(i);
            //if(line.length > 0) System.out.print("linha " + i + "   ");            
            for (int j = 0; j < line.length; j++) {
                //System.out.print(line[j] + "  ");
                if(line[j].contains(word))
                {
                    
                    int lineAndCol [] = {i, j};
                    positions.add(lineAndCol);
                }
            }
            //if(line.length > 0) System.out.println();       
         }
        return positions;
    }
    
    public static void showPositions(ArrayList<int[]> mat)
    {
        for (int i = 0; i < mat.size(); i++) {
            System.out.print("linha " + i + "   ");
            for (int j = 0; j < mat.get(i).length; j++) {
                System.out.print(mat.get(i)[j] + "  ");
            }
            System.out.println();
        }
    }
    
    public static void mostrarMatCVS(ArrayList<String[]> mat)
    {
        for (int i = 0; i < mat.size(); i++) {
            System.out.print("linha " + i + "   ");
            for (int j = 0; j < mat.get(i).length; j++) {
                System.out.print(mat.get(i)[j] + "  ");
            }
            System.out.println();
        }
    }
    
    public static void getEscalas(ArrayList<String[]> table, ArrayList<int[]> positions){
        ArrayList<Escala> escalas = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            int lineNCol [] = positions.get(i);
            int iLine = lineNCol[0];
            int iCol = lineNCol[1];
            ArrayList<String[]> escalaTable = new ArrayList<>();
            String line []= new String[2];
            boolean isOver = false;
            
            
            while(!isOver)
            {
                int nLinesEmp = 0;
                //System.out.println("completadordetabelas.EscalaParser.getEscalas() iLine " + iLine);
                //EscalaParser.mostrarMatCVS(table);
                line  = table.get(iLine);
                //System.out.println("completadordetabelas.EscalaParser.getEscalas() iCol " + iCol);
                //System.out.println("completadordetabelas.EscalaParser.getEscalas() line[iCol]) " + line[iCol]);
                //System.out.println("completadordetabelas.EscalaParser.getEscalas() line[iCol+2] " + line[iCol+2]);
                
                String lineEsc [] = {line[iCol], line[iCol+2]};
                if(line[iCol].isEmpty())
                {
                    nLinesEmp++;
                }
                if(nLinesEmp >= 2)
                {
                    isOver = true;
                }
                iLine++;
                if(iLine >= table.size())
                {
                    isOver = true;
                }                
                System.out.println("lineEsc  " + lineEsc[0] + "           " + lineEsc[1]);
                escalaTable.add(lineEsc);
            }
            System.out.println("nova Escala  =------------------------------");
            Escala esc = new Escala(escalaTable);
            EscalaParser.mostrarMatCVS(escalaTable);
            escalas.add(esc);
            
        }
    }
    
//    public static void getStops(ArrayList<int[]> mat, int i, int j, String type, String[] stopsStrings)
//    {
//        for (int i = 0; i < mat.size(); i++) {
//            System.out.print("linha " + i + "   ");
//            for (int j = 0; j < mat.get(i).length; j++) {
//                System.out.print(mat.get(i)[j] + "  ");
//            }
//            System.out.println();
//        }
//    }
    
    
    
}
