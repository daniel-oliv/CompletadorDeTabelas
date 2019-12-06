/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package completadordetabelas;

import java.text.Collator;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 *
 * @author danol
 */
public class CompletadorDeTabelas {

    
    //static final ArrayList<String[]>  matIn = GA.lerCSV_UTF8("coordenadas.csv", ",");
    //static final ArrayList<String[]>  matData = GA.lerCSV_UTF8("inSmall.csv", ",");
    static final ArrayList<String[]>  matOut = null;
    
    static final int idInData = 2;
    static final int idInCoord = 0;
    static final int clon = 3;
    static final int clat = 2;
    
    static final int C_CIDADE_IN = 1;
    static final int C_UF_IN = 2;
    static final int C_TEMP_ANUAL_IN = 3;
    static final int C_TEMP_FIM_IN = 15;
    
    static final String NOME_ARQ_OUT = "ComCoordenadas.csv";
    //static ArrayList<String[]>  matOut = GA.lerCSV_UTF8(NOME_ARQ_OUT, ",");
    static final int C_CIDADE_OUT = 0;
    static final int C_UF_OUT = 1;
    static final int C_TEMP_ANUAL_OUT = 4;
    
    static final String NOME_ARQ_OUT_COMP = "ComCoordenadas.csv";
    
    static final String NOME_ARQ_OUT_COMP_UTF = "TempearaturaSaidaVírgulasCompUTF.csv";
    static final String NOME_ARQ_OUT_COMP_ANSI = "TempearaturaSaidaVírgulasCompANSI.csv";
    static final String NOME_ARQ_IN_ESCALAS = "01-12_2019_11.csv";
    
    
    static ArrayList<String[]>  matOutComp;
    
//    public static int numLinhasDadoNaColuna(int numColuna)
//    {        
//        return numLinhasSemNenhumDado(numColuna, numColuna);
//    }
    
//    public static int numLinhasSemNenhumDado(int colunaIni, int colunaFim)
//    {
//        int numLinhas = matIn.size();
//        int counter = 0;
//        
//        for (int i = 1; i < numLinhas; i++) {
//            
//            boolean linhaInteiraNaoTem = true;
//            
//            for (int j = colunaIni; j <= colunaFim; j++) {
//                String dado = matIn.get(i)[j];                
//                if( isDouble( dado ) )     linhaInteiraNaoTem = false;
//            }
//            
//            if(linhaInteiraNaoTem)  counter++;
//            
//        }
//        return counter;
//    }
//    
//    public static int numLinhasSemAlgumDado(int colunaIni, int colunaFim)
//    {
//        int numLinhas = matIn.size();
//        int counter = 0;
//        
//        for (int i = 1; i < numLinhas; i++) {
//            
//            boolean colunaNaoTem = false;
//            
//            for (int j = colunaIni; j <= colunaFim; j++) {
//                String dado = matIn.get(i)[j];                
//                if( !isDouble( dado ) )     colunaNaoTem = true;
//            }
//            
//            if(colunaNaoTem)  counter++;
//            
//        }
//        return counter;
//    }
//    
    
    public static boolean isDouble(String str) {
        try
        {
            Double.parseDouble(str.replace(",", "."));
            return true;
        }
        catch(NumberFormatException ne){
            return false;
        }
    }
    
    public static boolean saoIguaisSemAcento(String str1, String str2) {
        Collator collator = Collator.getInstance (new Locale ("pt", "BR"));
	collator.setStrength(Collator.PRIMARY);
        return collator.equals(str1, str2);
        
    }
    
    public static String getSemAcento(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
        
    }
    
    public static int iLinhaCidadeEmOut(String cidade, String uf)
    {
        int numLinhasOut = matOut.size();
        
        
        for (int i = 1; i < numLinhasOut; i++) {
            
            String linha[] = matOut.get(i);
            if(saoIguaisSemAcento(uf, linha[C_UF_OUT]) && saoIguaisSemAcento(cidade, linha[C_CIDADE_OUT]))  return i;
        }
        return -1;
    }
    
//    public static int preencherOutComp()
//    {
//        int numLinhasIn = matIn.size();
//        int linhasPreenchidas = 0;
//        
//        for (int i = 1; i < numLinhasIn; i++) {
//            String linhaIn[] = matIn.get(i);
//            int iLinhaOut = iLinhaCidadeEmOut(linhaIn[C_CIDADE_IN], linhaIn[C_UF_IN]);
//            if(iLinhaOut > -1)  
//            {
//                String linhaConst[] = new String[C_TEMP_ANUAL_OUT + 13];
//                System.arraycopy(matOut.get(iLinhaOut), 0, linhaConst, 0, C_TEMP_ANUAL_OUT);
//                System.arraycopy(linhaIn, C_TEMP_ANUAL_IN, linhaConst, C_TEMP_ANUAL_OUT, 13);
//                matOutComp.set(iLinhaOut, linhaConst);
//                linhasPreenchidas++;
//            }
//        }
//        return linhasPreenchidas;
//    }
    
//    public static int numDadosSemCorrespEmOut()
//    {
//        int numLinhasIn = matIn.size();
//        int counter = 0;
//        
//        for (int i = 1; i < 31; i++) {
//            String linha[] = matIn.get(i);
//            if(iLinhaCidadeEmOut(linha[C_CIDADE_IN], linha[C_UF_IN]) == -1)  
//            {
//                counter++;
//                System.out.println("dado sem correspondente: iLinha " + i + "    " + linha[C_CIDADE_IN] + "     " + linha[C_UF_IN]);
//            }
//        }
//        return counter;
//    }
    
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
    
    private static void inicializarMatOutComp(int numColunasOutComp) {
        matOutComp = new ArrayList<>();
        
//        for (int i = 0; i < matOut.size(); i++) {
//            matOutComp.add(new String[numColunasOutComp]);
//            for (int j = 0; j < C_TEMP_ANUAL_OUT; j++) {
//                
//            }
//        }
        
        
        System.out.println("completadordetabelas.CompletadorDeTabelas.inicializarMatOutComp() numLinhas " + matOutComp.size() + "   numColunas " + matOutComp.get(0).length);
    }
    
//    private static ArrayList<String[]> addCoordinates(ArrayList<String[]> matCoord, ArrayList<String[]> matToCoplete)
//    {
//        int count = 0;
//        for (int i = 0; i < matCoord.size(); i++) {
//            for (int j = 0; j < matToCoplete.size(); j++) {
//                String idData = matToCoplete.get(j)[idInData];
//                if(idData.equals(matCoord.get(i)[idInCoord].substring(0, idData.length())))
//                {
//                    String oldLine [] = matToCoplete.get(j);
//                    String newLine[] = new String[oldLine.length + 2];
//                    for (int k = 0; k < oldLine.length; k++) {
//                        newLine[k] = oldLine[k];
//                    }
//                    newLine[oldLine.length] = matCoord.get(i)[clat];
//                    newLine[oldLine.length + 1] = matCoord.get(i)[clon];
//                    matToCoplete.set(j, newLine);
//                    count++;
//                }
//            }
//        }
//        System.out.println("completadordetabelas.CompletadorDeTabelas.addCoordinates() : " + count);
//        return matData;
//    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArrayList<String[]> escalasCSV = GA.lerCSV_UTF8(NOME_ARQ_IN_ESCALAS, ",");
       // mostrarMatCVS(escalasCSV);
        ArrayList<int[]> escalasPos = EscalaParser.findWordsPositions(escalasCSV, "ESCALA");
        System.out.println("escalasPos.size " + escalasPos.size());
        EscalaParser.showPositions(escalasPos);
        EscalaParser.getEscalas(escalasCSV, escalasPos);
        
//        System.out.println("numLinhasComNenhumDado " + numLinhasSemNenhumDado(C_TEMP_ANUAL_IN, C_TEMP_FIM_IN) );
//        
//        System.out.println("numLinhasSemDadoAnual " + numLinhasDadoNaColuna(C_TEMP_ANUAL_IN) );
//        
//        System.out.println("numLinhasSemAlgumDado " + numLinhasSemAlgumDado(C_TEMP_ANUAL_IN, C_TEMP_FIM_IN) );
        
//        System.out.println("indice linha cidade " + iLinhaCidadeEmOut("PATOS DE MINAS", "MG"));

//        System.out.println("dados sem correspondência " + numDadosSemCorrespEmOut());        
//        System.out.println("Número de linhas de entrada " + matIn.size());
//        System.out.println("Número de linhas de saída " + matOut.size());

        
//          inicializarMatOutComp(C_TEMP_ANUAL_OUT + 13);
        //matOutComp =  GA.lerCSV_UTF8(NOME_ARQ_OUT, ",");
//            matOutComp = new ArrayList<>();
            //mostrarMatCVS(matIn);
            //mostrarMatCVS(matData);
            //matOutComp = addCoordinates(matIn, matData);
            //mostrarMatCVS(matData);
        //int linhasPreench = preencherOutComp();
//        
       //GA.escreverCVS_ISO_OR_ANSI(NOME_ARQ_OUT_COMP, ",", matData);
//        
//        System.out.println("linhasPreench " + linhasPreench);
        

        //ArrayList<String[]>  matComp = GA.lerCSV(NOME_ARQ_OUT_COMP_UTF, ";");
        //mostrarMatCVS(matOutComp);
    }

    
    
}
