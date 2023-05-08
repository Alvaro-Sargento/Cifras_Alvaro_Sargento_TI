/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transpLinhaColuna;

import transpColunar.CifraColunar;

/**
 *
 * @author Alvaro Sargento
 */
public class transpLinhaColuna {
    
    // variaveis chaves
    public static String chave;
    public static char[] chaveEscolhidaA;
    public static int[] chaveEscolhidaB;
  
    // indicamos a chave padrao
    public transpLinhaColuna()
    {
        chave = "leo";
        chaveEscolhidaB = new int[chave.length()];
        chaveEscolhidaA = chave.toCharArray();
    }
  
    //metodo para transpor na segundo a chave
    public  void transpoeChave()
    {
        // encontra aposicao de cada caracter e organiza em ordem alfabetica
        int menor, i, j;
        char[] chaveOrginal = chave.toCharArray();
        char temp;
  
        // ordena o array de caracteres
        for (i = 0; i < chave.length(); i++) {
            menor = i;
            for (j = i; j < chave.length(); j++) {
                if (chaveEscolhidaA[menor] > chaveEscolhidaA[j]) {
                    menor = j;
                }
            }
  
            if (menor != i) {
                temp = chaveEscolhidaA[i];
                chaveEscolhidaA[i] = chaveEscolhidaA[menor];
                chaveEscolhidaA[menor] = temp;
            }
        }
  
        // preenche o array gerando a palavra ou texto simples 
        for (i = 0; i < chave.length(); i++) {
            for (j = 0; j < chave.length(); j++) {
                if (chaveOrginal[i] == chaveEscolhidaA[j])
                    chaveEscolhidaB[i] = j;
            }
        }
    }
  
    // metodo que realiza a cifragem
    public String cifrar(String textoClaro)
    {
        int min, i, j;
        char[] chaveOrginal = chave.toCharArray();
        char temp;
        transpoeChave();
  
        // gera o texto cifrado fazendo a transposicao
        int row = textoClaro.length() / chave.length();
        int extrabit
            = textoClaro.length() % chave.length();
        int exrow = (extrabit == 0) ? 0 : 1;
        int rowtemp = -1, coltemp = -1;
        int totallen = (row + exrow) * chave.length();
        char pmat[][] = new char[(row + exrow)]
                                [(chave.length())];
        char encry[] = new char[totallen];
  
        int tempcnt = -1;
        row = 0;
  
        for (i = 0; i < totallen; i++) {
            coltemp++;
            if (i < textoClaro.length()) {
                if (coltemp == (chave.length())) {
                    row++;
                    coltemp = 0;
                }
                pmat[row][coltemp] = textoClaro.charAt(i);
            }
  
            else {
                pmat[row][coltemp] = '-';
            }
        }
  
        int len = -1, k;
  
        for (i = 0; i < chave.length(); i++) {
            for (k = 0; k < chave.length(); k++) {
                if (i == chaveEscolhidaB[k]) {
                    break;
                }
            }
            for (j = 0; j <= row; j++) {
                len++;
                encry[len] = pmat[j][k];
            }
        }
  
        String p1 = new String(encry);
        return (new String(p1));
    }
      
  
    // metodo main main()
    public static void main(String[] args)
    {
        
        CifraColunar tc = new CifraColunar();
  
        System.out.println("texto simples1 : "+ tc.cifrar("leo Alvaaro"));
        String a = tc.cifrar("leo Alvaaro");
        transpLinhaColuna tlc = new transpLinhaColuna();
        System.out.println("texto simples2 : "+tlc.cifrar(a));
        
    }
}
