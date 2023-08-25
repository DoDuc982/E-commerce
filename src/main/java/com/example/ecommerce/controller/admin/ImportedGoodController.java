package com.example.ecommerce.controller.admin;

import com.example.ecommerce.model.ImportedGood;
import com.example.ecommerce.service.ImportedGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/imported_good")
public class ImportedGoodController {
    private final ImportedGoodService ImportedGoodService;

    @Autowired
    public ImportedGoodController(ImportedGoodService ImportedGoodService) {
        this.ImportedGoodService = ImportedGoodService;
    }

    @GetMapping
    public ResponseEntity<List<ImportedGood>> getAllImportedGood() {
        List<ImportedGood> ImportedGoodList = ImportedGoodService.getAllImportedGood();
        return new ResponseEntity<>(ImportedGoodList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImportedGood> getImportedGoodById(@PathVariable Long id) {
        ImportedGood ImportedGood = ImportedGoodService.getImportedGoodById(id);
        if (ImportedGood != null) {
            return new ResponseEntity<>(ImportedGood, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ImportedGood> createImportedGood(@RequestBody ImportedGood ImportedGood) {
        ImportedGood createdImportedGood = ImportedGoodService.createImportedGood(ImportedGood);
        return new ResponseEntity<>(createdImportedGood, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImportedGood> updateImportedGood(@PathVariable Long id, @RequestBody ImportedGood updatedImportedGood) {
        updatedImportedGood.setId(id);
        ImportedGood updatedImportedGoodData = ImportedGoodService.updateImportedGood(id,updatedImportedGood);
        if (updatedImportedGoodData != null) {
            return new ResponseEntity<>(updatedImportedGoodData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}