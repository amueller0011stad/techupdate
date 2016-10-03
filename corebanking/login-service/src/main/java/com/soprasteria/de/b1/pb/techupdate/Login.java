package com.soprasteria.de.b1.pb.techupdate;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOGIN")
public class Login
{

    @Id
    private String username;

    @Column(name = "SALT")
    private String salt;

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

}
