package com.jdbc.jdbcclientdemo.entity;

import lombok.Data;

@Data
public class Store {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
