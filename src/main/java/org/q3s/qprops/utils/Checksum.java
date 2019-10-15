package org.q3s.qprops.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class Checksum {

    public static String generate(String urlIMG) {
    	try {
    		byte[] b = downloadUrl(new URL(urlIMG));
    		
    		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    		messageDigest.update(b);
    		byte[] hash = messageDigest.digest();
    		
    		return DatatypeConverter.printHexBinary(hash).toUpperCase();			
		} catch (Exception e) {
			return "";
		}
    }
    
    private static byte[] downloadUrl(URL toDownload) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            byte[] chunk = new byte[4096];
            int bytesRead;
            InputStream stream = toDownload.openStream();

            while ((bytesRead = stream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return outputStream.toByteArray();
    }

}