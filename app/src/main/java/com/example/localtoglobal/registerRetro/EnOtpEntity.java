package com.example.localtoglobal.registerRetro;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EnOtpEntity implements Serializable {
    @SerializedName("enOtp")
    private String enOtp;

    public String getEnOtp() {
        return enOtp;
    }

    public void setEnOtp(String enOtp) {
        this.enOtp = enOtp;
    }
}
