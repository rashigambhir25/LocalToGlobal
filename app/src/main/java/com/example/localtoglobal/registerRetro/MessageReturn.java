package com.example.localtoglobal.registerRetro;

import java.io.Serializable;

public class MessageReturn implements Serializable {
    private Long status;

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

}
