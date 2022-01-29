package com.example.localtoglobal.registerRetro;

import java.io.Serializable;

public class RegisterEntity implements Serializable {
    private Long otp;
    private String enOtp;
    private String name;
    private String email;
    private Long phoneNo;
    private String address;
    private String password;
    private String confirmPassword;

    public RegisterEntity(Long otp, String enOtp, String name, String email, Long phoneNo, String address, String password, String confirmPassword) {
        this.otp = otp;
        this.enOtp = enOtp;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Long getOtp() {
        return otp;
    }

    public void setOtp(Long otp) {
        this.otp = otp;
    }

    public String getEnOtp() {
        return enOtp;
    }

    public void setEnOtp(String enOtp) {
        this.enOtp = enOtp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
