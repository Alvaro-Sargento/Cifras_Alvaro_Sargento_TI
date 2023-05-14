/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

public class RSAKeyPairGenerator {

    public static int[] publicKey(int p, int q) throws Exception {
        if (!ePrimo(p)) {
            throw new Exception("Numero deve ser primo: " + p);
        }

        if (!ePrimo(q)) {
            throw new Exception("Numero deve ser primo: " + q);
        }

        int phi = (p - 1) * (q - 1);
        int n = p * q;
        int e = 0;

        for (int i = 1; i < phi; i++) {
            if (ePrimo(i) && saoCoprimos(i, phi)) {
                e = i;
                break;
            }
        }

        return new int[]{e, n};
    }

    public static int[] privateKey(int p, int q) throws Exception {
        if (!ePrimo(p)) {
            throw new Exception("Numero deve ser primo: " + p);
        }

        if (!ePrimo(q)) {
            throw new Exception("Numero deve ser primo: " + q);
        }

        int phi = (p - 1) * (q - 1);
        int n = p * q;


        int e = publicKey(p, q)[0];
        int d = 0;
        for (int i = 0; i < 1000000000; i++) {
           if ((i * e) % phi == 1) {
               d = i;
               break;
           }
        }

        return new int[]{d, n};
    }

    private static boolean saoCoprimos(int a, int b) throws Exception {
        return maximoDivisorComum(a, b) == 1;
    }

    private static int maximoDivisorComum(int a, int b) throws Exception {
        if (b == 0) {
            return a;
        }
        return maximoDivisorComum(b, a % b);
    }

    private static boolean ePrimo(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
