package de.ssc.jwt.jose4j;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class User {

  private String _id;
  private String username;
  private String password;
  private String firm;
  private List<String> rolesList = new ArrayList<String>();
  
  public User() {}

  public User(String _id, String username, 
								String password, List<String> rolesList) {
    super();
    this._id = _id;
    this.username = username;
    this.password = password;
    this.rolesList = rolesList;
  }

  @JsonProperty(value = "_id")
  public String get_id() {
    return _id;
  }
  
  public void set_id(String _id) {
    this._id = _id;
  }

  @JsonProperty(value = "username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonProperty(value = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @JsonProperty(value = "firm")
  public String getFirm() {
    return firm;
  }

  public void setFirm(String firm) {
    this.firm = firm;
  }

  @JsonProperty(value = "roles")
  public List<String> getRolesList() {
    return rolesList;
  }

  public void setRolesList(List<String> rolesList) {
    this.rolesList = rolesList;
  }

  @Override
  public String toString() {
    return "User [_id=" + _id + ", username=" + username 
			+ ", password=" + password + ", rolesList=" 
			+ rolesList + "]";
  }
}