package com.example.ecommerce.service;

import com.example.ecommerce.model.SEO;
import com.example.ecommerce.repository.SEORepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SEOService {
    private final SEORepository seoRepository;
    @Autowired
    public SEOService(SEORepository seoRepository) {
        this.seoRepository = seoRepository;
    }
    public List<SEO> getAllSEO() {
        return seoRepository.findAll();
    }
    public SEO getSEOById(Long id) {
        return seoRepository.findById(id).orElse(null);
    }
    public SEO createSEO(SEO seo) {
        return seoRepository.save(seo);
    }
    public void deleteSEO(Long id) {
        seoRepository.deleteById(id);
    }
    public SEO updateSEO(Long id, SEO updatedSEO) {
        SEO existingSEO = seoRepository.findById(id).orElse(null);
        if (existingSEO != null) {
            existingSEO.setKeyword(updatedSEO.getKeyword());
            existingSEO.setSearchVolume(updatedSEO.getSearchVolume());
            return seoRepository.save(existingSEO);
        }
        return null;
    }
}
