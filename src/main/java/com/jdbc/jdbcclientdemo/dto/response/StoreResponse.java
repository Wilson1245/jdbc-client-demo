package com.jdbc.jdbcclientdemo.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jdbc.jdbcclientdemo.entity.Store;
import lombok.Data;

@Data
public class StoreResponse {

    private Long id;

    private String name;

    private String address;

    @JsonIgnore
    private String phone;

    private String email;

    public static StoreResponse buildResponse(Store store) {
        StoreResponse storeResponse = new StoreResponse();
        storeResponse.setId(store.getId());
        storeResponse.setAddress(store.getAddress());
        storeResponse.setEmail(store.getEmail());
        storeResponse.setPhone(store.getPhone());
        storeResponse.setName(store.getName());
        return storeResponse;
    }
}
