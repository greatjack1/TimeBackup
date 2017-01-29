/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timebackup;

/**
 *
 * @author yaakov
 */
import java.io.IOException;
import java.security.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * The point of this class it to compute the MD5 sum of a file
 */
public class Md5 {

    private String path;
    private String md5Sum;

    /**
     * Constructor that takes a file path and and calcs the MD5 sum
     *
     * @param filePath the string that contains the full path to the file
     */
    public Md5(String filePath) {
        path = filePath;
        calcMd5(path);
    }

    private void calcMd5(String filePath) {
        //create a messagedigest object to compute an MD5 sum
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //create a input stream to get the bytes of the file
            Path path = Paths.get(filePath);
            md.update(Files.readAllBytes(path));
            byte[] hash = md.digest();

            md5Sum = toHexString(hash);
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

    }

    private String toHexString(byte[] bytes) {
        char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v / 16];
            hexChars[j * 2 + 1] = hexArray[v % 16];
        }
        return new String(hexChars);
    }

    /**
     * Returns the MD5 sum as a String
     *
     * @return the string that contains the MD5 sum
     */
    public String getMd5Sum() {
        return md5Sum;
    }
}
