import RSA.RSAKeyPairGenerator;
import digitalSignuture.DigitalSignuture;
import digitalSignuture.MD5Hash;

import java.util.Arrays;

public class Hello
{
    public static void main(String[] args) throws Exception {
        int[] publicKey = RSAKeyPairGenerator.publicKey(7, 11);
        int[] privateKey = RSAKeyPairGenerator.privateKey(7, 11);

        System.out.println(Arrays.toString(publicKey));
        System.out.println(Arrays.toString(privateKey));

        String[] msg = DigitalSignuture.encrypt(privateKey, "password");
        String[] msgs = DigitalSignuture.decrypt(publicKey, msg[0]);

        System.out.println(DigitalSignuture.validacaoHash(msgs));
    }
}
