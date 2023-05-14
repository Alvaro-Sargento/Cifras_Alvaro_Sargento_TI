package digitalSignuture;

import java.util.ArrayList;

public class DigitalSignuture {

    private static ArrayList<Integer> resumo = new ArrayList<Integer>();


    private static String hashResume(String mensagem) {
        return MD5Hash.hashMessage(mensagem);
    }

    public static String[] encrypt(int[] privateKey, String mensagem) throws Exception {
        int asciiValue = 0;
        double textoCifrado = 0;
        String resumoHash = hashResume(mensagem);

        StringBuilder resumoCifrado = new StringBuilder();
        for (int i = 0; i < resumoHash.length(); i++) {
            char character = resumoHash.charAt(i);
            asciiValue = (int) character;
            textoCifrado = Math.pow(asciiValue, privateKey[0]) % privateKey[1];
            resumoCifrado.append((int)textoCifrado);
            resumo.add((int)textoCifrado);
        }

        return new String[]{mensagem, resumoCifrado.toString()};
    }

    // decrypt
    public static String[] decrypt(int[] publicKey, String resumoMensagem) throws Exception {
        int asciiValue = 0;
        int textoClaro = 0;

        System.out.println(resumo);
        StringBuilder resumoDecifrado = new StringBuilder();
        for (Integer integer : resumo) {
            textoClaro = (int) ((Math.pow(integer, publicKey[0])) % publicKey[1]);
            char character = (char) (textoClaro + 'A');

            resumoDecifrado.append(character);
        }
        System.out.println(resumoDecifrado);
        return new String[]{resumoMensagem, resumoDecifrado.toString()};
    }

    public static Boolean validacaoHash(String[] resumoHash) {
        String hashMsg = hashResume(resumoHash[0]);

        return hashMsg.equals(resumoHash[1]);
    }
}
