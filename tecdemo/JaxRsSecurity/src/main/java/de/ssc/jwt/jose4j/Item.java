package de.ssc.jwt.jose4j;

import org.codehaus.jackson.annotate.JsonProperty;

public class Item {

  private String _id;
  private String itemId;
  private String itemName;
  private double itemPrice;
  private int itemQuantity;
  
  public Item() {}
  
  public Item(String _id, String itemId, String itemName, 
      double itemPrice, int itemQuantity) {
    
    super();
    this._id = _id;
    this.itemId = itemId;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemQuantity = itemQuantity;
  }

  public String get_id() {
    return _id;
  }
  
  public void set_id(String _id) {
    this._id = _id;
  }
  
  @JsonProperty(value = "item-id")
  public String getItemId() {
    return itemId;
  }
  
  public void setItemId(String itemId) {
    this.itemId = itemId;
  }
  
  @JsonProperty(value = "item-name")
  public String getItemName() {
    return itemName;
  }
  
  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
  
  @JsonProperty(value = "price")
  public double getItemPrice() {
    return itemPrice;
  }
  
  public void setItemPrice(double itemPrice) {
    this.itemPrice = itemPrice;
  }
  
  @JsonProperty(value = "quantity")
  public int getItemQuantity() {
    return itemQuantity;
  }
  
  public void setItemQuantity(int itemQuantity) {
    this.itemQuantity = itemQuantity;
  }

  @Override
  public String toString() {
    return "Item [_id=" + _id + ", itemId=" + itemId + ", itemName="
        + itemName + ", itemPrice=" + itemPrice + ", itemQuantity="
        + itemQuantity + "]";
  }
}