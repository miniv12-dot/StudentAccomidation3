package com.example.studentaccomidation;

public class resItem {
    String name;
    String address;
    String email;
    String telephone;
    String picture;
    public static String documentId;

    public resItem(){}
    public resItem(String resName, String resAddress, String resEmail, String resPhone, String picture, String documentId) {
        this.name = resName;
        this.address = resAddress;
        this.email = resEmail;
        this.telephone = resPhone;
        this.picture = picture;
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPicture() {
        return picture;
    }

    public String getDocumentId() {
        return documentId;
    }
}
