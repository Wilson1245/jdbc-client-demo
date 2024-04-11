package com.jdbc.jdbcclientdemo.dto.request;

import com.jdbc.jdbcclientdemo.entity.Store;
import lombok.Data;

@Data
public class StoreRequest {

    private String name;

    private String address;

    private String phone;

    private String email;

    public Store toEntity() {
        Store store = new Store();
        store.setName(name);
        store.setAddress(address);
        store.setPhone(phone);
        store.setEmail(email);
        return store;
    }

    public Store updateEntity(Store store) {
        if (this.getName() != null && !this.getName().isEmpty()) {
            store.setName(this.getName());
        }
        if (this.getAddress() != null && !this.getAddress().isEmpty()) {
            store.setAddress(this.getAddress());
        }
        if (this.getPhone() != null && !this.getPhone().isEmpty()) {
            store.setPhone(this.getPhone());
        }
        if (this.getEmail() != null && !this.getEmail().isEmpty()) {
            store.setEmail(this.getEmail());
        }
        return store;
    }
}
