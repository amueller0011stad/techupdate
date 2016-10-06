package com.soprasteria.de.b1.pb.techupdate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains utility methods to create hash and salt values out of passwords.
 */
public class LoginUtil
{
    private static final Logger log = Logger.getLogger("corebanking-login-service");

    public static String createRandomString128()
    {
        try(Formatter fmt = new Formatter())
        {
            for(int i=0;i<128;i++) fmt.format("%x",(int)(Math.random() * 16));
            return fmt.toString();
        }
    }
    
    public static String sha256h(String s)
    throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        try(Formatter fmt = new Formatter())
        {
            for(int i=0;i<hash.length;i++) fmt.format("%02x",hash[i]);
            return fmt.toString();
        }
    }
    
    public static boolean checkPw(String pw,String salt,String passwordHash)
    {
        try
        {
            String sha256h = sha256h(pw+salt);
            return sha256h.equals(passwordHash);
        }
        catch(Exception e)
        {
            log.log(Level.SEVERE,"Cannot check password",e);
            return false;
        }
    }
    
    public static String[] createNewPasswordHashAndSalt(String pw)
    throws Exception
    {
        String salt = createRandomString128();
        String sha256h = sha256h(pw+salt);
        return new String[]{sha256h,salt};
    }
    
    /**Helper to create new passwords on the command line */
    public static void main(String[] args)
    throws Exception
    {
        if(args.length==2 && "create".equals(args[0]))
        {
            String[] pwAndSalt = createNewPasswordHashAndSalt(args[1]);
            System.out.println(Arrays.toString(pwAndSalt));
        }
        else if(args.length==4 && "check".equals(args[0]))
        {
            String pw = args[1];
            String salt = args[2];
            String passwordHash = args[3];
            int exitCode = checkPw(pw,salt,passwordHash)? 0 : 1;
            System.out.println(exitCode);
            System.exit(exitCode);
        }
    } 
}
