package com.soprasteria.de.b1.pb.techupdate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(
            name="JwkParam.byKeyId",
            query="select p from JwkParam p where keyId=:keyId")
})
public class JwkParam
implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="KEY_ID")
    private String keyId;

    @Id
    @Column(name="KEY")
    private String key;

    @Column(name="VALUE",length=1000)
    private String value;

    public String getKeyId()
    {
        return keyId;
    }

    public void setKeyId(String keyId)
    {
        this.keyId = keyId;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

}
