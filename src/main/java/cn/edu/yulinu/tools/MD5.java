package cn.edu.yulinu.tools;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
    public static final String KEY_MD5 = "MD5";

    public static  String  getResult(String username, String password){
        String res = password;
        System.out.println("=======加密前的数据:"+res);
        BigInteger bigInteger=null;

        try {
            MessageDigest md = MessageDigest.getInstance(KEY_MD5);
            byte[] inputData = res.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest());
        } catch (Exception e) {e.printStackTrace();}
        System.out.println("MD5加密后:" + bigInteger.toString(16));
        return bigInteger.toString(16);
    }
}
