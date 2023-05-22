/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;

public class RSAKeyPairGenerator {

    public int[] publicKey(int p, int q) throws Exception {
        // lançar uma exceção se o número p não for primo
        if (!ePrimo(p)) {
            throw new Exception("Numero deve ser primo: " + p);
        }

        // lançar uma exceção se o número q não for primo
        if (!ePrimo(q)) {
            throw new Exception("Numero deve ser primo: " + q);
        }

        // Calcula o valor de phi usando as variáveis p e q
        int phi = (p - 1) * (q - 1);

        // Calcula o valor de n multiplicando p e q
        int n = p * q;

        // Inicializa a variável e com 0
        int e = 0;

        // Percorre os valores de 1 a phi
        for (int i = 1; i < phi; i++) {
            // Verifica se i é um número primo e se é coprimo com phi
            if (ePrimo(i) && saoCoprimos(i, phi)) {
                // Atribui o valor de i à variável e
                e = i;
                // Sai do loop
                break;
            }
        }

        // Retorna um array contendo os valores de e e n
        return new int[]{e, n};

    }

    public int[] privateKey(int p, int q) throws Exception {
        // lançar uma exceção se o número p não for primo
        if (!ePrimo(p)) {
            throw new Exception("Numero deve ser primo: " + p);
        }

        // lançar uma exceção se o número q não for primo
        if (!ePrimo(q)) {
            throw new Exception("Numero deve ser primo: " + q);
        }

        // Calcula o valor de phi usando as variáveis p e q
        int phi = (p - 1) * (q - 1);

        // Calcula o valor de n multiplicando p e q
        int n = p * q;

        // Obtém o valor de e chamando a função publicKey com os parâmetros p e q e pegando o primeiro elemento do array retornado
        int e = publicKey(p, q)[0];

        // Inicializa a variável d com 0
        int d = 0;

        // Percorre os valores de 0 a 999999999 (1000000000-1)
        for (int i = 0; i < 1000000000; i++) {
            // Verifica se o resultado da expressão (i * e) % phi é igual a 1, onde phi é a variável calculada anteriormente
            if ((i * e) % phi == 1) {
                // Atribui o valor de i à variável d
                d = i;
                // Sai do loop
                break;
            }
        }

        // Retorna um array contendo os valores de d e n
        return new int[]{d, n};
    }

    private boolean saoCoprimos(int a, int b) throws Exception {
        return maximoDivisorComum(a, b) == 1;
    }

    private int maximoDivisorComum(int a, int b) throws Exception {
        if (b == 0) {
            return a;
        }

        return maximoDivisorComum(b, a % b);
    }

    private boolean ePrimo(int number) {
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
