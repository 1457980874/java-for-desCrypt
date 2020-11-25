import com.sun.crypto.provider.AESKeyGenerator;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class demoAES {
    public static void main(String[] args) {
        String data ="海上月是天上月，眼前人是心上人";
        String key ="123456781234567812345678";
        demoAES demoAES=new demoAES();
        byte[] dst =demoAES.AESOperation(key.getBytes(),data.getBytes(),Cipher.ENCRYPT_MODE);
        demoDES demoDES = new demoDES();
        byte[] origin = demoAES.AESOperation(key.getBytes(),dst,Cipher.DECRYPT_MODE);
        System.out.println(demoDES.byteToString(dst));
        System.out.println(demoDES.byteToString(origin));
    }

    public static byte[] AESOperation(byte[] key,byte[] msg,int mode){
        try {
            //秘钥生成器，获取要生成的秘钥
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //秘钥的初始化，SecureRandom是生产安全随机数序列
            keyGenerator.init(128,new SecureRandom(key));
            //根据用户设置的key生成一个秘钥
            SecretKey secretKey = keyGenerator.generateKey();
            //返回基本编码格式的秘钥
            byte[] enCodeFormat = secretKey.getEncoded();
            //转换成AES秘钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat,"AES");
            //Cipher 负责加密或解密的动作
            Cipher cipher = Cipher.getInstance("AES");
            //设置加密模式，传入秘钥
            cipher.init(mode,secretKeySpec);
            //返回加密后结果
            return cipher.doFinal(msg);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }


}
