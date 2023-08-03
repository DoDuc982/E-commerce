package com.example.ecommerce.service;

import com.example.ecommerce.model.ShopInfo;
import com.example.ecommerce.repository.ShopInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopInfoService {
    private final ShopInfoRepository ShopInfoRepository;
    @Autowired
    public ShopInfoService(ShopInfoRepository ShopInfoRepository) {
        this.ShopInfoRepository = ShopInfoRepository;
    }
    public List<ShopInfo> getAllShopInfo() {
        return ShopInfoRepository.findAll();
    }
    public ShopInfo getShopInfoById(Long id) {
        return ShopInfoRepository.findById(id).orElse(null);
    }
    public ShopInfo createShopInfo(ShopInfo ShopInfo) {
        return ShopInfoRepository.save(ShopInfo);
    }
    public void deleteShopInfo(Long id) {
        ShopInfoRepository.deleteById(id);
    }
    public ShopInfo updateShopInfo(ShopInfo updatedShopInfo) {
        ShopInfo existingShopInfo = ShopInfoRepository.findById(updatedShopInfo.getId()).orElse(null);
        if (existingShopInfo != null) {
            existingShopInfo.setUrl(updatedShopInfo.getUrl());
            existingShopInfo.setSocial(updatedShopInfo.getSocial());
            return ShopInfoRepository.save(existingShopInfo);
        }
        return null;
    }
}
