package cesar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alvaro Sargento
 */

//essa é uma biblioteca que vai permitir usar e ler dados pelo teclado via console
import java.util.Scanner;


public class Cesar {
    
    //metodo para cifrar
    public static String encriptar(int chave, String texto) {
        
        //ciramos um objecto do tipo construtor de de obj strings
        StringBuilder textoCifrado = new StringBuilder();
        //descobrimos o tamnho de texto claro
        int tamanhoTexto = texto.length();

        //percoremos o texto e encotramos a letras correspondente
        for (int c = 0; c < tamanhoTexto; c++) {
            int letraCifradaASCII = ((int) texto.charAt(c)) + (chave - 1);

            while (letraCifradaASCII > 126) {
                //guardamos a letra corresponde enquanto o texto nao termina
                letraCifradaASCII -= 94;
            }

            textoCifrado.append((char) letraCifradaASCII);
        }
        
        //devolvemos o exto cifra
        return textoCifrado.toString();
    }

    //este é um medoto inverso 
    public static String decriptar(int chave, String textoCifrado) {
        
        StringBuilder texto = new StringBuilder();
        int tamanhoTexto = textoCifrado.length();

        for (int c = 0; c < tamanhoTexto; c++) {
            int letraDecifradaASCII = ((int) textoCifrado.charAt(c)) - (chave - 1);

            while (letraDecifradaASCII < 32) {
                //guardamos formando corresponde enquanto o texto nao termina
                letraDecifradaASCII += 94;
            }

            texto.append((char) letraDecifradaASCII);
        }
        //devolvemos o exto decifra
        return texto.toString();
    }

    public static void main(String[] args) {

        try {
            ////fazemos a declaracao do obecto scanner/teclado
            Scanner entrada = new Scanner(System.in);

            System.out.println("*****************************************************");
            
            //pedido de insercao do texto simples
            System.out.print("Informe o texto a ser criptografado: ");
            //leitura de insercao do texto simples
            String texto = entrada.nextLine();
            //pedido de insercao da chava
            System.out.print("Informe a chave de deslocamento: ");
            int chave = entrada.nextInt();

            //recebemos o texto e a chave executamos os metodos
            String textoCriptografado = encriptar(chave, texto);
            String textoDescriptografado = decriptar(chave, textoCriptografado);

            System.out.println("\n\nTEXTO CRIPTOGRAFADO: " + textoCriptografado);
            System.out.println("TEXTO DESCRIPTOGRAFADO: " + textoDescriptografado);

            System.out.println("*****************************************************");

        } catch (RuntimeException e) {
            System.out.println("A chave de deslocamento foi informada incorretamente.");
            System.out.println("Execute o programa novamente e entre com uma chave valida.");
        }
    }
}
