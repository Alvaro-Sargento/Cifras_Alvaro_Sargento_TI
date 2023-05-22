package digitalSignuture;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {

    public String hashMessage(String mensagem) {
        String md5Hash = ""; // define uma variavel que ira receber o valor do Hash

        try {
            // Inicializa uma instância da classe MessageDigest para usar o algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Obtém a representação em bytes da mensagem fornecida
            byte[] hashBytes = md.digest(mensagem.getBytes());

            // Cria um objeto StringBuilder para construir uma string
            StringBuilder sb = new StringBuilder();

            // Percorre cada byte no array de bytes do hash
            for (byte b : hashBytes) {
                // Converte cada byte em uma representação hexadecimal de dois dígitos
                sb.append(String.format("%02x", b));
            }

            // Converte o objeto StringBuilder em uma string contendo o hash MD5
            md5Hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Retorna o hash MD5 resultante
        return md5Hash;
    }
}
