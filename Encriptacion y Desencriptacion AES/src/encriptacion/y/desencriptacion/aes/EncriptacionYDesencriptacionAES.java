package encriptacion.y.desencriptacion.aes;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Andres Cordova <github.com/acordova200>
 */
public class EncriptacionYDesencriptacionAES {

    static Cipher cipher;

    public static String encriptar(String texto, SecretKey llaveSecreta)
            throws Exception {
        byte[] textoByte = texto.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, llaveSecreta);
        byte[] encriptadoByte = cipher.doFinal(textoByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String textoEncriptado = encoder.encodeToString(encriptadoByte);
        return textoEncriptado;
    }

    public static String desencriptar(String textoEncriptado, SecretKey llaveSecreta)
            throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encriptadoByte = decoder.decode(textoEncriptado);
        cipher.init(Cipher.DECRYPT_MODE, llaveSecreta);
        byte[] desencriptadoByte = cipher.doFinal(encriptadoByte);
        String textoDesencriptado = new String(desencriptadoByte);
        return textoDesencriptado;
    }

    public static void main(String[] args) {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();

            cipher = Cipher.getInstance("AES");

            String textoEncriptar = "Andres Cordova";
            System.out.println("Texto a Encriptar: " + textoEncriptar);

            String textoEncriptado = encriptar(textoEncriptar, secretKey);
            System.out.println("Texto Encriptado: " + textoEncriptado);

            String textoDesencriptado = desencriptar(textoEncriptado, secretKey);
            System.out.println("Texto Desencriptado: " + textoDesencriptado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
