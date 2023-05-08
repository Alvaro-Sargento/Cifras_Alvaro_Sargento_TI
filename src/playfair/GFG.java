/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playfair;

/**
 *
 * @author Alvaro Sargento
 */
public class GFG {
    public static void main(String[] args)
    {
        System.out.println("Example-1\n");
        
        String key1 = "Problem";
        String plainText1 = "Playfair";
        
        System.out.println("Key: " + key1);
        System.out.println("PlainText: " + plainText1);
        
        Playfair pfc1 = new Playfair(key1, plainText1);
        pfc1.cleanPlayFairKey();
        pfc1.generateCipherKey();
        
        String encText1 = pfc1.encryptMessage();
        System.out.println("Cipher Text is: " + encText1);
  
        System.out.println("\nExample-2\n");
        
        String key2 = "Problem";
        String plainText2 = "Hello";
        
        System.out.println("Key: " + key2);
        System.out.println("PlainText: " + plainText2);
        
        Playfair pfc2 = new Playfair(key2, plainText2);
        pfc2.cleanPlayFairKey();
        pfc2.generateCipherKey();
        
        String encText2 = pfc2.encryptMessage();
        System.out.println("Cipher Text is: " + encText2);
    }
}
