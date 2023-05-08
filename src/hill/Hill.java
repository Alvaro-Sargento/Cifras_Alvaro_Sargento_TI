/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hill;

/**
 *
 * @author Alvaro Sargento
 */

//essa é uma biblioteca que vai permitir usar e ler dados pelo teclado via console
import java.util.Scanner;


public class Hill {
    
    public static void main (String[] args) {
        
        //aqui fazemos com que o user insira a texto claro
        System.out.print("Digite sua mensagem > ");
        
        //fazemos a declaracao do obecto scanner/teclado
        Scanner input = new Scanner(System.in);
        
        //lemos a msg
        String msg = input.nextLine();
        //convertemos a msg para um array, ja que contamos os caracteres
        char[] caracteres = msg.toCharArray();

        //aqui fazemos com que o user insira a chava
        System.out.print("Digite o valor da cifra > ");
        //lemos a chave
        int cifra = input.nextInt();
        
        //percorre o array (cada caractere) para deslocar e subistituir de acordo com a chave acima
        for (int i = 0; i < caracteres.length; i++) {
                int ascii = (int) caracteres[i] + cifra;
                //imprimimos o texto cifrado
                System.out.print((char) ascii);
        }

        System.out.println();
        
//        descodificacao
        
 
        //percorre o array (cada caractere) para deslocar e subistituir de acordo com a chave acima mmas no sentido inverso
        for (int i = 0; i < caracteres.length; i++) {
                int ascii = (int) caracteres[i] - cifra;
                //imprimimos o texto claro
                System.out.print((char) ascii);
        }

        System.out.println();
    }
}
