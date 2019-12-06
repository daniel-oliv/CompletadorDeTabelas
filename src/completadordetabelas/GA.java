/*
 * Programa para projetos de Sistemas Fotovoltaicos Conectados a Rede el�trica
 * Autor: Daniel de Oliveira Ferreira 
 * Orientadora: Elise Saraiva
 * Universidade Federal de Uberl�ndia - Campus Patos de Minas
 * 
 */
package completadordetabelas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import sun.awt.CharsetString;

/**
 *
 * @author danol
 */
public class GA {
    
    static int BOM_UTF8[] = {0xEF, 0xBB, 0xBF};

    public static String ler(String nomeArquivo) { /////// LER COM ENCONDING DEFAULT - acho que é utf-8
        String strLida = "";
        try {
            FileReader fileReader = new FileReader(new File(nomeArquivo));
            BufferedReader reader = new BufferedReader(fileReader);
            while (reader.ready()) {
                strLida = strLida.concat(reader.readLine());
            }
            reader.close();
            fileReader.close();
            System.out.println("String lida com sucesso!(Serialização)\n");
        } catch (IOException e) {
            System.err.printf("Exceção durante leitura: " + e.toString());
        }
        return strLida;
    }
    
    public static boolean isUTF8_BOM(String nomeArquivo)
    {
        String strBOM_UTF8 = "EF BB BF";
        String bom = "";
        try {
            FileInputStream fis = new FileInputStream(nomeArquivo);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            
            
            for (int i = 0; i < 3; i++) {
                if(buffer.ready())
                {
                    bom += String.format("%02X ", fis.read()) ;
                }
            }
                                    
            fis.close();
            buffer.close();
            return bom.equals(strBOM_UTF8); 
        }
        catch (IOException e) {
            System.err.printf("Exceção durante leitura do BOM: " + e.toString());
        }
        
            return false;
    }

    public static ArrayList<String[]> lerCSV_UTF8(String nomeArquivo, String delimitador) {
        String[] colunas;
        ArrayList<String[]> linhas = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(nomeArquivo);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
             
            while (buffer.ready()) {
                colunas = buffer.readLine().split(delimitador);
                linhas.add(colunas);
            }
            fis.close();
            buffer.close();
            System.out.println("String lida com sucesso!(Serialização)\n");
        }
        catch (IOException e) {
            System.err.printf("Exceção durante leitura: " + e.toString());
        }
        return linhas;
    }
    
    public static ArrayList<String[]> lerCSV_ISO_OR_ANSI(String nomeArquivo, String delimitador) {
        String[] colunas;
        ArrayList<String[]> linhas = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), "ISO-8859-1"));
            while (buffer.ready()) {
                colunas = buffer.readLine().split(delimitador);
                linhas.add(colunas);
            }
            buffer.close();
            System.out.println("String lida com sucesso!(Serialização)\n");
        }
        catch (IOException e) {
            System.err.printf("Exceção durante leitura: " + e.toString());
        }
        return linhas;
    }
    
    public static ArrayList<String> lerLinha_ENC_DEFAULT(String nomeArquivo) {
        ArrayList<String> linhas = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(new File(nomeArquivo));
            BufferedReader reader = new BufferedReader(fileReader);
            while (reader.ready()) {
                linhas.add(reader.readLine());
            }
            reader.close();
            fileReader.close();
            System.out.println("String lida com sucesso!(Serialização)\n");
        } catch (IOException e) {
            System.err.printf("Exceção durante leitura: " + e.toString());
        }
        return linhas;
    }

    public static void escrever_ENC_DEFAULT(String nomeArquivo, String str) {
        try {
            FileWriter fWriter = new FileWriter(new File(nomeArquivo));
            fWriter.write(str);
            fWriter.close();
            System.out.println("String gravada com sucesso!(Serialização)\n");
        } catch (IOException e) {
            System.err.printf("Exceção durante escrita: " + e.toString());
        }
    }

    public static void anexarLinha_ENC_DEFAULT(String nomeArquivo, String str) {
        try {
            FileWriter fWriter = new FileWriter(new File(nomeArquivo), true);
            fWriter.append(str.concat(System.lineSeparator()));
            fWriter.close();
            System.out.println("String gravada com sucesso!(Serialização)\n");
        } catch (IOException e) {
            System.err.printf("Exceção durante escrita: " + e.toString());
        }
    }

    public static void escreverCVS_ENC_DEFAULT(String nomeArquivo, String delimitador, ArrayList<String[]> matriz) {
        try {
            String strLinha = "";
            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo) ));
            for (int i = 0; i < matriz.size(); i++) {
                
                int tamanhoLinha = matriz.get(i).length;
                
                for (int j = 0; j < tamanhoLinha; j++) {
                    strLinha += matriz.get(i)[j] + delimitador;
                }
                
                strLinha += (System.lineSeparator());
                
            }
            buffer.write(strLinha);
            buffer.close();
            System.out.println("String gravada com sucesso!(Serialização)\n");
        } catch (IOException e) {
            System.err.printf("Exceção durante escrita: " + e.toString());
        }
    }
    
    public static void escreverCVS_UTF8_BOM(String nomeArquivo, String delimitador, ArrayList<String[]> matriz) {
        try {
            String strLinha = "";
            FileOutputStream fos = new FileOutputStream(nomeArquivo) ;
            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter( fos , "UTF-8"));
            
            for (int i = 0; i < 3; i++) {
               fos.write((byte)BOM_UTF8[i]); 
            }
            
            for (int i = 0; i < matriz.size(); i++) {
                
                int tamanhoLinha = matriz.get(i).length;
                
                for (int j = 0; j < tamanhoLinha; j++) {
                    strLinha += matriz.get(i)[j] + delimitador;
                }
                
                strLinha += (System.lineSeparator());
                
            }
            buffer.write(strLinha);
            fos.close();
            buffer.close();
            System.out.println("String gravada com sucesso!(Serialização)\n");
        } catch (IOException e) {
            System.err.printf("Exceção durante escrita: " + e.toString());
        }
    }
    
    public static void escreverCVS_UTF8(String nomeArquivo, String delimitador, ArrayList<String[]> matriz) {
        try {
            String strLinha = "";
            FileOutputStream fos = new FileOutputStream(nomeArquivo) ;
            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter( fos , "UTF-8"));
            
            //for (int i = 0; i < 3; i++) {
            //   fos.write((byte)BOM_UTF8[i]); 
            //}
            
            for (int i = 0; i < matriz.size(); i++) {
                
                int tamanhoLinha = matriz.get(i).length;
                System.out.println("Writing line " + i + "");
//                for (int j = 0; j < tamanhoLinha; j++) {
//                    strLinha += matriz.get(i)[j] + delimitador;
//                }
                strLinha+= String.join(delimitador, matriz.get(i));
                
                strLinha += (System.lineSeparator());
                
            }
            buffer.write(strLinha);
            fos.close();
            buffer.close();
            System.out.println("String gravada com sucesso!(Serialização)\n");
        } catch (IOException e) {
            System.err.printf("Exceção durante escrita: " + e.toString());
        }
    }

    
    public static void escreverCVS_ISO_OR_ANSI(String nomeArquivo, String delimitador, ArrayList<String[]> matriz) {
        try {
            String strLinha = "";
            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), "ISO-8859-1"));
            for (int i = 0; i < matriz.size(); i++) {
                
                int tamanhoLinha = matriz.get(i).length;
                
                for (int j = 0; j < tamanhoLinha; j++) {
                    strLinha += matriz.get(i)[j] + delimitador;
                }
                
                strLinha += (System.lineSeparator());
                
            }
            buffer.write(strLinha);
            buffer.close();
            System.out.println("String gravada com sucesso!(Serialização)\n");
        } catch (IOException e) {
            System.err.printf("Exceção durante escrita: " + e.toString());
        }
    }
    
    public static void escreverColunasCSV_ENC_DEFAULT(String nomeArquivo, ArrayList<String[]> matriz, int colunasGravar[]) {
        try {
            String strLinha = "";
            FileWriter fWriter = new FileWriter(new File(nomeArquivo));
            for (int i = 0; i < matriz.size(); i++) {
                for (int j = 0; j < colunasGravar.length; j++) {
                    strLinha += matriz.get(i)[colunasGravar[j]] + ",";
                }
                System.err.println(""+i);
                strLinha += (System.lineSeparator());
            }
            fWriter.write(strLinha);
            fWriter.close();
            System.out.println("String gravada com sucesso!(Serialização)\n");
        } catch (IOException e) {
            System.err.printf("Exceção durante escrita: " + e.toString());
        }
    }

}
