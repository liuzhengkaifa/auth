package com.base.auth.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class PasswordSecurityUtils {

    private static final int SALT_LENGTH = 16; // 盐值长度

    /**
     * 生成带盐值的哈希密码
     *
     * @param password 明文密码。需要被哈希保护的字符串。
     * @return 带盐值的哈希密码（Base64编码）。返回的字符串由盐值和哈希密码通过冒号分隔。
     * @throws NoSuchAlgorithmException 如果指定的哈希算法不存在，则抛出此异常。
     */
    public static String hashPasswordWithSalt(String password){
        try {
            // 生成随机盐值
            SecureRandom random = new SecureRandom();
            //16字节的盐值
            byte[] salt = new byte[SALT_LENGTH];
            // 使用SecureRandom为盐值生成随机字节
            random.nextBytes(salt);

            // 获取SHA-256消息摘要实例
            MessageDigest md = getMessageDigestInstance("SHA-256");
            // 将密码和盐值合并为一个字符串
            String toHash = password + new String(salt, StandardCharsets.UTF_8);
            // 对合并后的字符串进行SHA-256哈希
            byte[] hashedBytes = md.digest(toHash.getBytes(StandardCharsets.UTF_8));

            // 将盐值和哈希结果使用Base64编码，并通过冒号分隔符连接，形成最终返回的字符串
            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Failed to generate salt", e);
        }
    }


    /**
     * 验证密码
     *
     * 该方法用于验证用户输入的密码是否与数据库中存储的带盐值哈希密码匹配。
     * 首先，将存储的密码字符串按冒号分隔，以获取盐值和哈希密码的二进制表示。
     * 然后，使用输入的密码和获取的盐值重新计算哈希值，并将其与存储的哈希密码进行比较。
     * 如果两者匹配，则验证成功。
     *
     * @param storedPassword 储存在数据库中的带盐值哈希密码（Base64编码）。格式为“盐值:哈希密码”。
     * @param inputPassword 登录时输入的密码，需要验证的密码。
     * @return 如果输入密码正确，返回true；否则返回false。
     */
    public static boolean validatePassword(String storedPassword, String inputPassword) {
        try {
            // 分解存储的密码字符串，获取盐值和哈希值
            String[] parts = storedPassword.split(":");

            // 校验分解结果是否符合预期格式
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid stored password format");
            }

            // 解码盐值和存储的哈希值
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] storedHash = Base64.getDecoder().decode(parts[1]);

            // 使用SHA-256算法计算输入密码的哈希值，附加盐值
            MessageDigest md = getMessageDigestInstance("SHA-256");
            byte[] inputHash = md.digest((inputPassword + new String(salt, StandardCharsets.UTF_8)).getBytes(StandardCharsets.UTF_8));

            // 比较计算得到的哈希值与存储的哈希值
            return Arrays.equals(inputHash, storedHash);
        } catch (NoSuchAlgorithmException e) {
            // 处理算法不可用的异常，抛出运行时异常
            throw new RuntimeException("Failed to validate password", e);
        }
    }


    private static MessageDigest getMessageDigestInstance(String algorithm) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithm);
    }

    public static void main(String[] args) {
        String password = "123456";
        String hashedPassword = hashPasswordWithSalt(password);
        System.out.println("Hashed Password: " + hashedPassword);

        boolean isValid = validatePassword(hashedPassword, password);
        System.out.println("Is Valid: " + isValid);
    }
}
