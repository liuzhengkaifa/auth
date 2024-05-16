package com.base.auth.service.user.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SaltedHashExample {

    public static void main(String[] args) {
        String password = "mySecretPassword"; // 用户的密码
        try {
            // 生成随机盐值
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16]; // 16字节的盐值
            random.nextBytes(salt);
            
            // 将盐值转换为Base64字符串，便于存储和显示
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            System.out.println("盐值(Base64): " + encodedSalt);
            
            // 使用SHA-256进行哈希
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // 将密码与盐值拼接
            String toHash = password + new String(salt, StandardCharsets.UTF_8);
            byte[] passwordWithSaltBytes = toHash.getBytes(StandardCharsets.UTF_8);
            
            // 计算哈希值
            byte[] hashedBytes = md.digest(passwordWithSaltBytes);
            
            // 哈希值转为Base64字符串存储
            String encodedHash = Base64.getEncoder().encodeToString(hashedBytes);
            System.out.println("哈希后的密码(Base64): " + encodedHash);
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("找不到合适的哈希算法");
            e.printStackTrace();
        }
    }
}
