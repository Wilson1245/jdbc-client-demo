package com.jdbc.jdbcclientdemo.controller;

import com.jdbc.jdbcclientdemo.dto.request.StoreRequest;
import com.jdbc.jdbcclientdemo.dto.response.StoreResponse;
import com.jdbc.jdbcclientdemo.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/store")
@RequiredArgsConstructor
public class StoreController {

    private final JdbcClient jdbcClient;

    @GetMapping
    public List<StoreResponse> getStores() {
        return jdbcClient.sql("select * from store")
                .query(Store.class).stream()
                .map(StoreResponse::buildResponse).toList();
    }

    @GetMapping(value = "/{id}")
    public StoreResponse getStoreById(@PathVariable Long id) {
        return jdbcClient.sql("select * from store where id = :id")
                // 命名參數
                .param("id", id).query(Store.class).optional()
                .map(StoreResponse::buildResponse).orElse(null);
    }

    @PostMapping
    public String addStore(@RequestBody StoreRequest request) {
        Store store = request.toEntity();
        // 佔位符號
//        jdbcClient.sql("insert into store (name, address, phone, email) values (?, ?, ?, ?)")
//                .param(store.getName()).param(store.getAddress())
//                .param(store.getPhone()).param(store.getEmail())
//                .update();
        int update = jdbcClient.sql("insert into store (name, address, phone, email) values (:name, :address, :phone, :email)")
                .paramSource(store)
                .update();
        return update == 1 ? "Store added successfully" + request.getName() : "Store added failed";
    }

    @PutMapping(value = "/updateA/{id}")
    public String updateA(@PathVariable Long id, @RequestBody StoreRequest request) {
        int update = jdbcClient.sql("update store set name = ?, address = ?, phone = ?, email = ? where id = ?")
                .params(List.of(request.getName(), request.getAddress(), request.getPhone(), request.getEmail(), id))
                .update();
        return update == 1 ? "Store updated successfully" : "Store updated failed";
    }

    @PutMapping(value = "/updateB/{id}")
    public String updateB(@PathVariable Long id, @RequestBody StoreRequest request) {
        Store store = jdbcClient.sql("select * from store where id = :id")
                // 命名參數
                .param("id", id).query(Store.class).optional().orElse(null);
        if (store == null) {
            return "Store not found";
        }
        Store updateStore = request.updateEntity(store);
        System.out.println(store);
        int update = jdbcClient.sql("update store set name = ?, address = ?, phone = ?, email = ? where id = ?")
                .params(List.of(updateStore.getName(), updateStore.getAddress(), updateStore.getPhone(), updateStore.getEmail(), id))
                .update();
        return update == 1 ? "Store updated successfully" : "Store updated failed";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteStore(@PathVariable Long id) {
        int update = jdbcClient.sql("delete from store where id = :id").param("id", id).update();
        return update == 1 ? "Store deleted successfully" : "Store deleted failed";
    }
}
