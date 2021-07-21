package com.epam.training.model.user;

import lombok.Data;

@Data
public class UserImpl implements User {

    private long id;
    private String name;
    private String email;

    public UserImpl(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserImpl(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
