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
        BigInteger asciiValue = BigInteger.ZERO;
        BigInteger textoCifrado = BigInteger.ZERO;
        String resumoHash = hashResume(mensagem);

        StringBuilder resumoCifrado = new StringBuilder();
        for (int i = 0; i < resumoHash.length(); i++) {
            BigInteger character = BigInteger.valueOf(resumoHash.charAt(i));
            asciiValue = character;
            textoCifrado = (asciiValue.pow(privateKey[0])).mod(BigInteger.valueOf(privateKey[1]));
            resumoCifrado.append(textoCifrado);
            resumo.add(textoCifrado);
        }
        System.out.println(resumoCifrado);
        return new String[]{mensagem, resumoCifrado.toString()};
    }

    // decrypt
    public String[] decrypt(int[] publicKey, String resumoMensagem) throws Exception {
        BigInteger asciiValue = BigInteger.ZERO;
        BigInteger textoCifrado = BigInteger.ZERO;

        StringBuilder resumoDecifrado = new StringBuilder();
        for (BigInteger integer : resumo) {
            BigInteger textoClaro = (integer.pow(publicKey[0])).mod(BigInteger.valueOf(publicKey[1]));
            char character = (char)textoClaro.intValue();
            resumoDecifrado.append(character);
        }
        System.out.println(resumoDecifrado);
        return new String[]{resumoMensagem, resumoDecifrado.toString()};
    }

    public Boolean validacaoHash(String[] resumoHash) {
        return hashResume(resumoHash[0]).equals(resumoHash[1]);
    }
}
