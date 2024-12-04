package code34_aboutHash;

import java.security.MessageDigest;
import java.security.Security;

public class Code01_Hash {

    private MessageDigest hash;

    public Code01_Hash(String algrithm) {
        try {
            hash = MessageDigest.getInstance(algrithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String hashcode(String input){
        return DatatypeConverter.printHexBinary(hash.digest(input.getBytes()));
    }

    public static class DatatypeConverter {
        public static String printHexBinary(byte[] data) {
            StringBuilder hex = new StringBuilder();
            for (byte b : data) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        }
    }


    public static void main(String[] args) {
        System.out.println("支持的算法");
        for (String messageDigest : Security.getAlgorithms("MessageDigest")) {
            System.out.println(messageDigest);
        }

        System.out.println("========");

        String alg = "SHA";
        Code01_Hash code01Hash = new Code01_Hash(alg);
        String input1="zhangjunhaozhangjunhao1";
        String input2="zhangjunhaozhangjunhao2";
        String input3="zhangjunhaozhangjunhao3";
        String input4="zhangjunhaozhangjunhao4";
        String input5="zhangjunhaozhangjunhao5";
        System.out.println(code01Hash.hashcode(input1));
        System.out.println(code01Hash.hashcode(input2));
        System.out.println(code01Hash.hashcode(input3));
        System.out.println(code01Hash.hashcode(input4));
        System.out.println(code01Hash.hashcode(input5));
        //支持的算法
        //SHA3-512
        //SHA-1
        //SHA-384
        //SHA3-384
        //SHA-224
        //SHA-512/256
        //SHA-256
        //MD2
        //SHA-512/224
        //SHA3-256
        //SHA-512
        //SHA3-224
        //MD5
        //========
        //2ea581f355a1e7ab43071028664cd326b3b63189
        //b8cdb82d0ce6afe90dd4dea3413731d9b0f3df5d
        //022d749fb282dec1d54aea13fa7e285e5de32ac1
        //387c724ce1b07b8e91f250b1d6d279ce6796148a
        //912bfcf1e0ef55a88d057410d081211b1cd9f05d
    }


}
