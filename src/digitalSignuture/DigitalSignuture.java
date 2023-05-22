package digitalSignuture;

import java.math.BigInteger;
import java.util.ArrayList;

public class DigitalSignuture {

    public static ArrayList<BigInteger> resumo = new ArrayList<BigInteger>();
    MD5Hash md5Hash = new MD5Hash();

    private String hashResume(String mensagem) {
        return md5Hash.hashMessage(mensagem);
    }

    public String[] encrypt(int[] privateKey, String mensagem) throws Exception {
        BigInteger asciiValue = BigInteger.ZERO; // Inicializa uma variável BigInteger com o valor zero para armazenar o valor ASCII do caractere
        BigInteger textoCifrado = BigInteger.ZERO; // Inicializa uma variável BigInteger com o valor zero para armazenar o texto cifrado
        String resumoHash = hashResume(mensagem); // Calcula o resumo hash da mensagem fornecida

        StringBuilder resumoCifrado = new StringBuilder(); // Cria um objeto StringBuilder para construir a representação cifrada do resumo
        for (int i = 0; i < resumoHash.length(); i++) {
            BigInteger character = BigInteger.valueOf(resumoHash.charAt(i)); // Obtém o valor ASCII do caractere atual
            asciiValue = character; // Atribui o valor ASCII à variável asciiValue
            textoCifrado = (asciiValue.pow(privateKey[0])).mod(BigInteger.valueOf(privateKey[1])); // Aplica a criptografia usando a chave privada
            resumoCifrado.append(textoCifrado); // Adiciona o texto cifrado ao StringBuilder
            resumoCifrado.append(", ");
            resumo.add(textoCifrado); // Adiciona o texto cifrado à lista 'resumo'
        }
        System.out.println(resumoCifrado); // Imprime a representação cifrada do resumo na saída
        return new String[]{mensagem, resumoCifrado.toString()}; // Retorna um array de Strings contendo a mensagem original e o resumo cifrado como Strings

    }

    // decrypt
    public String[] decrypt(int[] publicKey, String resumoMensagem) throws Exception {
        BigInteger asciiValue = BigInteger.ZERO; // Inicializa uma variável BigInteger com o valor zero para armazenar o valor ASCII do caractere
        BigInteger textoCifrado = BigInteger.ZERO; // Inicializa uma variável BigInteger com o valor zero para armazenar o texto cifrado
       
      
       // Split the string by commas
        String[] numberStrings = resumoMensagem.split(",\\s*");

        // Convert string array to array of BigInteger
        BigInteger[] array = new BigInteger[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            array[i] = new BigInteger(numberStrings[i]);
        }
        
        StringBuilder resumoDecifrado = new StringBuilder(); // Cria um objeto StringBuilder para construir a representação decifrada do resumo
        for (BigInteger integer : array) {
            System.out.println(integer);
            BigInteger textoClaro = (integer.pow(publicKey[0])).mod(BigInteger.valueOf(publicKey[1])); // Aplica a descriptografia usando a chave pública
            char character = (char) textoClaro.intValue(); // Converte o valor numérico do texto claro em um caractere
            resumoDecifrado.append(character); // Adiciona o caractere decifrado ao StringBuilder
        }
        
        System.out.println(resumoDecifrado); // Imprime a representação decifrada do resumo na saída
        return new String[]{resumoMensagem, resumoDecifrado.toString()}; // Retorna um array de Strings contendo o resumo original e o resumo decifrado como Strings
    }

    public Boolean validacaoHash(String[] resumoHash) {
        // Compara o resumo hash da mensagem original (resumoHash[0]) com o resumo cifrado (resumoHash[1])
        return hashResume(resumoHash[0]).equals(resumoHash[1]);
    }

}
