/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vigenere;

//essa é uma biblioteca que vai permitir usar e ler dados pelo teclado via console
import java.util.Scanner;

/**
 *
 * @author Alvaro Sargento
 */
public class Vigenere {
    
    public static void main(String[]args)
    {
        //fazemos a declaracao do obecto scanner/teclado
        Scanner leer = new Scanner(System.in);
        String cadena;
        
        //esse menu vai nos permitor cifrar ou decifar
        char opc;
        do{
        System.out.println("Escolha"+"\n1.-Cifrar"+"\n2.-Descifrar "+"\n0.-Sair");
        opc=leer.nextLine().charAt(0);
        
        if(opc=='1'){
                System.out.println("Introduza o texto claro");
                cadena=leer.nextLine();
                System.out.println(Cifrar(cadena));
            }
        else if(opc=='2'){
                System.out.println("Introduza o texto cifrado");
                cadena=leer.nextLine();
                System.out.println(Descifrar(cadena));
                }
        }while(opc!='0');
        
    }
    public  static String Cifrar(String MSG)
    {
        String saida="";
        String alfabeto="ABCDEFGHIJKLMNNOPQRSTUVWXYZ";
       String chave="LOUP";
       char []claveEquals=new char [MSG.length()];
       char []Msj=MSG.toUpperCase().toCharArray();
       int cont=0;
       for(int c=0;c<MSG.length();c++)
       {
           
           if(MSG.charAt(c)==' ')
           {
               c++;
           }
           claveEquals[c]=chave.charAt(cont);
           cont++;
           if(cont==chave.length())
           {
               cont=0;
           }
       }//
       int x=0,y=0,z;
       for(int c=0;c<MSG.length();c++)
       {
           if(MSG.charAt(c)==' ')
           {
               saida+=" ";
               c++;
           }
           for(int f=0;f<alfabeto.length();f++)
           {
               if(Msj[c]==alfabeto.charAt(f))
               {
                   x=f;
                   
               }
               if(claveEquals[c]==alfabeto.charAt(f))
               {
                   y=f;
                   
               }
               
               
           }
           z=(x+y)%27;
           saida+=alfabeto.charAt(z);
           
       }
       
       return saida;
    }
    
    //esse é um meto para decifrar
    public static String Descifrar(String MSG)
    {
        //declaramos as variaveis
        String saida="";
        String alfabeto="ABCDEFGHIJKLMNNOPQRSTUVWXYZ";
       String chave="LOUP";
       //decaramos o array para receber o texto cifrado
       char []chaveEquals=new char [MSG.length()];
       char []Msg=MSG.toUpperCase().toCharArray();
       int cont=0;
       
       //aqui vamos pegar o texto e retirar os espacos em braca do array
       for(int c=0;c<MSG.length();c++)
       {
           
           if(MSG.charAt(c)==' ')
           {
               c++;
           }
           chaveEquals[c]=chave.charAt(cont);
           cont++;
           if(cont==chave.length())
           {
               cont=0;
           }
       }
       cont=0;
       int x=0,y=0,z,t;
       
       for(int c=0;c<MSG.length();c++)
       {
           if(MSG.charAt(c)==' ')
           {
               saida+=" ";
               c++;
           }
           
           //aqui fazemos a decifragem 
           for(int f=0;f<alfabeto.length();f++)
           {
               if(Msg[c]==alfabeto.charAt(f))
               {
                   x=f;
                   
               }
               if(chaveEquals[c]==alfabeto.charAt(f))
               {
                   y=f;
                   
               }
               
               
           }
           z=(y-x);
           
           if(z<=0)
           {
               if(z==0)
               {
                   saida+="A";
               }
               else
               {
                   // pegamos os carracteres
               for(int j=1;j<=alfabeto.length();j++)
               {
                cont++;
                if(cont==(z*-1))
                {
                    saida+=alfabeto.charAt(j);
                    break;
                }
               }    
               }
               
           }else{
               for(int i=26;i>=0;i--)
                {
                    cont++;
                    if(cont==z)
                    {
                       saida+=alfabeto.charAt(i); 
                       break;
                    }
                }
           }
                
                cont=0;
           
       }
       
       return saida;
    }
}
