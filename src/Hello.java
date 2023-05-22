
import RSA.RSAKeyPairGenerator;
import digitalSignuture.DigitalSignuture;

import java.util.Arrays;

public class Hello
{
    public static void main(String[] args) throws Exception {
        RSAKeyPairGenerator rGenerator = new RSAKeyPairGenerator();
        DigitalSignuture digitalSignuture =  new DigitalSignuture();

        int[] publicKey = rGenerator.publicKey(11, 17);
        int[] privateKey = rGenerator.privateKey(11, 17);

        System.out.println(Arrays.toString(publicKey));
        System.out.println(Arrays.toString(privateKey));


        String[] msg = digitalSignuture.encrypt(privateKey, "este e um exemplo");
        String[] msgs = digitalSignuture.decrypt(publicKey, msg[0]);

        System.out.println(msgs[1]);

        System.out.println(digitalSignuture.validacaoHash(msgs));
    }
}
