import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class day1123 {
    //秘钥
    static String key = "20201124";
    //数据
    static String data = "海上月是天上月，眼前人是心上人";

    public static void main(String[] args) {
        day1123 DesCipher = new day1123();
        byte[] dst = DesCipher.encrypt(key.getBytes(), data.getBytes());
        System.out.println(DesCipher.byteToString(dst));
        try {
            System.out.println(DesCipher.byteToString(desOperation(key.getBytes(), dst, Cipher.DECRYPT_MODE)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用des算法进行加密
     *
     * @param key 自己设定的秘钥
     * @param msg 要加密的明文
     * @return 将加密后的密文字节数组返回
     */
    public byte[] encrypt(byte[] key, byte[] msg) {
        try {
            //DES秘钥初始化
            DESKeySpec spec = new DESKeySpec(key);
            //工厂模式：获取不同类型的加密算法类型
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            //生成相应加密算法的秘钥
            SecretKey secretKey = factory.generateSecret(spec);
            //具体执行加密和解密的动作
            Cipher cipher = Cipher.getInstance("DES");
            //设置工作模式，加密或者解密
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //执行
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
        return null;
    }

    /**
     * 使用des算法对传入的密文进行解密
     *
     * @param key 秘钥
     * @param msg 密文
     * @return 将解密后的明文以字节数组的形式返回
     */
    public byte[] decrypt(byte[] key, byte[] msg) {
        try {
            //DES秘钥初始化
            DESKeySpec desKeySpec = new DESKeySpec(key);
            //工厂模式，根据需求返回不同的实例
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            //统一的秘钥对象
            SecretKey secretKey = factory.generateSecret(desKeySpec);
            //实例化解密对象
            Cipher cipher = Cipher.getInstance("DES");
            //设置解密模式
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            //最后解密的动作
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
        return null;
    }

    /**
     * 使用des算法进行加密或者解密动作
     *
     * @param key  传入的秘钥
     * @param data 明文或者密文
     * @param mode 加密或者解密模式 加密模式为Cipher.ENCRYPT_MODE，解密模式为Cipher.DECRYPT_MODE
     * @return 将结果以字节数组的形式返回
     * @throws Exception
     */
    static public byte[] desOperation(byte[] key, byte[] data, int mode) throws Exception {

        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = factory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(mode, secretKey);

        return cipher.doFinal(data);
    }
    //byte数组转String
    public  String byteToString(byte[] bytes){
        if (bytes==null||bytes.length==0){
            return"";
        }
        String strContent ="";
        try {
            strContent=new String(bytes,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strContent;
    }
}
