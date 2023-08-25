package com.example.ecommerce.controller.admin;

import com.example.ecommerce.model.SEO;
import com.example.ecommerce.service.SEOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/seo")
public class SEOController {
    private final SEOService seoService;

    @Autowired
    public SEOController(SEOService seoService) {
        this.seoService = seoService;
    }

    @GetMapping
    public ResponseEntity<List<SEO>> getAllSEO() {
        List<SEO> seoList = seoService.getAllSEO();
        return new ResponseEntity<>(seoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SEO> getSEOById(@PathVariable Long id) {
        SEO seo = seoService.getSEOById(id);
        if (seo != null) {
            return new ResponseEntity<>(seo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SEO> createSEO(@RequestBody SEO seo) {
        SEO createdSEO = seoService.createSEO(seo);
        return new ResponseEntity<>(createdSEO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSEO(@PathVariable Long id) {
        seoService.deleteSEO(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SEO> updateSEO(@PathVariable Long id, @RequestBody SEO updatedSEO) {
        updatedSEO.setId(id);
        SEO updatedSEOData = seoService.updateSEO(id, updatedSEO);
        if (updatedSEOData != null) {
            return new ResponseEntity<>(updatedSEOData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}