package com.soprasteria.de.b1.pb.techupdate;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
            name="Login.byUsername",
            query="select l from Login l where username=:username")
})
@Table(name = "LOGIN")
public class Login
{

    @Id
    private String username;

    @Column(name = "SALT")
    private String salt;
    
    @Column(name = "HASH")
    private String hash;

    @Column(name = "FAILED_LOGIN_ATTEMPTS")
    private int failedLoginAttempts;

    @Column(name = "VALID_UNTIL")
    private Calendar validUntil;

    @Column(name = "LOCKED")
    private boolean locked;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getHash()
    {
        return hash;
    }

    public void setHash(String hash)
    {
        this.hash = hash;
    }

    public int getFailedLoginAttempts()
    {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts)
    {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public Calendar getValidUntil()
    {
        return validUntil;
    }

    public void setValidUntil(Calendar validUntil)
    {
        this.validUntil = validUntil;
    }

    public boolean isLocked()
    {
        return locked;
    }

    public void setLocked(boolean locked)
    {
        this.locked = locked;
    }

    public boolean checkPassword(String pw)
    {
        return LoginUtil.checkPw(pw,getSalt(),getHash());
    }
    
    public void setNewPassword(String newPassword)
    {
        String[] pwAndSalt = LoginUtil.createNewPasswordHashAndSalt(
                newPassword);
        setHash(pwAndSalt[0]);
        setSalt(pwAndSalt[1]);
    }
}
