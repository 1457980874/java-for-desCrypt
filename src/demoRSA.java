import javax.crypto.*;
import java.security.*;

public class demoRSA {
    static final int RSA_LENGH_1024 = 1024;
    static final int RSA_LENGH_2048 = 2048;
    public static void main(String[] args) {
        String data = "衣带渐宽终不悔，为伊消得人憔悴。";
        demoRSA demoRSA=new demoRSA();
        demoDES demoDES =new demoDES();
        //生成秘钥对
        KeyPair keyPair=demoRSA.creatKey(RSA_LENGH_2048);
        //对数据进行加密
        byte[] cipherTxt=demoRSA.enCrypt(data.getBytes(),keyPair.getPublic());
        //对密文进行解密
        byte[] originTxt=demoRSA.deCrypt(cipherTxt,keyPair.getPrivate());
        System.out.println(demoDES.byteToString(cipherTxt));
        System.out.println(demoDES.byteToString(originTxt));
        //私钥签名
        byte[] signTxt=demoRSA.signForRSA(demoRSA.md5Hash(data.getBytes()),keyPair.getPrivate());
        //公钥验签
        boolean result=demoRSA.verifyForRSA(signTxt,data.getBytes(),keyPair.getPublic());
        System.out.println(demoDES.byteToString(signTxt));
        System.out.println(result);
    }

    //================公钥加密，私钥解密================

    /**
     * RSA算法公钥加密
     * @param data 要加密的明文数据
     * @param pub 公钥
     * @return 加密后的数据
     */
    public byte[] enCrypt(byte[] data, PublicKey pub){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,pub);
            return cipher.doFinal(data);
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

    /**
     * 使用RSA算法进行私钥解密
     * @param cipherTXT 要解密的密文
     * @param pri 私钥
     * @return 解密后的明文
     */
    public byte[] deCrypt(byte[] cipherTXT,PrivateKey pri){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,pri);
            return cipher.doFinal(cipherTXT);
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

    //======================私钥签名，公钥验签==========

    /**
     * 使用RSA算法进行私钥签名
     * @param data hash后的数据
     * @param pri 私钥
     * @return 签名后的数据
     */
    public byte[] signForRSA(byte[] data, PrivateKey pri){
        try {
            //实例化一个签名器对象
            Signature signature=Signature.getInstance("MD5withRSA");
            //把私钥传入签名器中
            signature.initSign(pri);
            //把数据更新写入到签名器中
            signature.update(data);
            //返回数据签名
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用RSA算法进行验签
     * @param signTXT 签名数据
     * @param data 原文数据
     * @param pub 公钥
     * @return 返回值为签名是否通过验证
     */
    public boolean verifyForRSA(byte[] signTXT,byte[] data,PublicKey pub){
        try {
            //实例化一个加密器并声明使用的算法
            Signature signature = Signature.getInstance("MD5withRSA");
            //初始化加密器并传入公钥
            signature.initVerify(pub);
            //对原文进行MD5hash计算
            byte[] md5Hash = md5Hash(data);
            //把数据更新写入到签名器中
            signature.update(md5Hash);
            //返回数据签名
            return signature.verify(signTXT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 使用MD5hash算法对数据进行hash计算
     * @param data 原文
     * @return 摘要
     */
    public byte[] md5Hash(byte[] data){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return messageDigest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用java的api生成一对秘钥，返回KeyPair类型
     * @param size 秘钥的长度，RSA_LENGH_1024和RSA_LENGH_2048两种选择
     * @return 生成的秘钥对
     */
    public static KeyPair creatKey(int size){
        try {
            //秘钥生成器
            KeyPairGenerator keyPairGenerator =KeyPairGenerator.getInstance("RSA");
            //设置秘钥的长度
            keyPairGenerator.initialize(size);
            //生成秘钥对
            KeyPair keyPair= keyPairGenerator.genKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
