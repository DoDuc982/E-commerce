package com.example.ecommerce.controller;
import com.example.ecommerce.model.ShopInfo;
import com.example.ecommerce.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop_info")
public class ShopInfoController {
    private final ShopInfoService ShopInfoService;

    @Autowired
    public ShopInfoController(ShopInfoService ShopInfoService) {
        this.ShopInfoService = ShopInfoService;
    }

    @GetMapping
    public ResponseEntity<List<ShopInfo>> getAllShopInfo() {
        List<ShopInfo> ShopInfoList = ShopInfoService.getAllShopInfo();
        return new ResponseEntity<>(ShopInfoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopInfo> getShopInfoById(@PathVariable Long id) {
        ShopInfo ShopInfo = ShopInfoService.getShopInfoById(id);
        if (ShopInfo != null) {
            return new ResponseEntity<>(ShopInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ShopInfo> createShopInfo(@RequestBody ShopInfo ShopInfo) {
        ShopInfo createdShopInfo = ShopInfoService.createShopInfo(ShopInfo);
        return new ResponseEntity<>(createdShopInfo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShopInfo(@PathVariable Long id) {
        ShopInfoService.deleteShopInfo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopInfo> updateShopInfo(@PathVariable Long id, @RequestBody ShopInfo updatedShopInfo) {
        updatedShopInfo.setId(id);
        ShopInfo updatedShopInfoData = ShopInfoService.updateShopInfo(updatedShopInfo);
        if (updatedShopInfoData != null) {
            return new ResponseEntity<>(updatedShopInfoData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}