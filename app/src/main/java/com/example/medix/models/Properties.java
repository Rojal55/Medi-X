package com.example.medix.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Properties {

private String id;
private String amenity;
private String name; // pharmacy ko nam
private List<Medicine> medicine = null;
private String addrPostcode;

@SerializedName("addr:street")
private String addrStreet;
private String email;
//private String openingHours;
private String operator;
private String phone;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getAmenity() {
return amenity;
}


public String getName() {
return name;
}


public List<Medicine> getMedicine() {
return medicine;
}

public void setMedicine(List<Medicine> medicine) {
this.medicine = medicine;
}

public String getAddrPostcode() {
return addrPostcode;
}

public void setAddrPostcode(String addrPostcode) {
this.addrPostcode = addrPostcode;
}

public String getAddrStreet() {
return addrStreet;
}

public void setAddrStreet(String addrStreet) {
this.addrStreet = addrStreet;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

//public String getOpeningHours() {
//return openingHours;
//}
//
//public void setOpeningHours(String openingHours) {
//this.openingHours = openingHours;
//}

public String getOperator() {
return operator;
}

public void setOperator(String operator) {
this.operator = operator;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

}