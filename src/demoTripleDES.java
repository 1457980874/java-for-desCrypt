import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class demoTripleDES {
    //秘钥
    static String key = "123456781234567812345678";
    //数据
    static String data = "海上月是天上月，眼前人是心上人";


    public static void main(String[] args) {
        demoTripleDES tripleDES = new demoTripleDES();
        demoDES bytetoStringdemo = new demoDES();
        byte[] dst = tripleDES.TripleDESencrypt(key.getBytes(), data.getBytes());
        byte[] origin = tripleDES.TripleDESOperation(key.getBytes(),dst,Cipher.DECRYPT_MODE);
        //byte类型与string的互转
        System.out.println(bytetoStringdemo.byteToString(dst));
        System.out.println(bytetoStringdemo.byteToString(origin));
    }


    public byte[] TripleDESencrypt(byte[] key, byte[] msg) {

        try {
            //KeyGenerator提供对称加密秘钥生成器的功能，支持各种算法
            //KeyGenerator keyGenerator=KeyGenerator.getInstance("DESede");
            DESedeKeySpec keySpec = new DESedeKeySpec(key);
            //工厂模式获取不同的加密算法
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            //生成相应加密算法的秘钥
            SecretKey secretKey = factory.generateSecret(keySpec);
            //Cipher负责加密工作
            Cipher cipher = Cipher.getInstance("DESede");
            //设置工作模式，加密动作或者解密动作
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //最后执行动作
            return cipher.doFinal(msg);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        //Security.addProvider(new com.sun.crypto.provider.SunJCE());
        return null;
    }
    public byte[] TripleDESOperation(byte[] key,byte[] data,int mode){
        try {
            //初始化一个3DES秘钥
            DESedeKeySpec deSedeKeySpec=new DESedeKeySpec(key);
            //工厂模式：获取需要的加密算法
            SecretKeyFactory factory=SecretKeyFactory.getInstance("DESede");
            //生成相应加密算法的秘钥
            SecretKey secretKey =factory.generateSecret(deSedeKeySpec);
            //Cipher负责加密工作
            Cipher cipher =Cipher.getInstance("DESede");
            //获取秘钥和加密模式
            cipher.init(mode,secretKey);
            //返回加密结果
            return cipher.doFinal(data);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
