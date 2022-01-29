package com.example.localtoglobal.loginRetro;

import java.io.Serializable;

public class UserEntity implements Serializable {
    private Long id;
    private Long status;

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

//    public UserEntity(){}
//    public UserEntity(Long id) {
//        this.id = id;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
