package com.example.ecommerce.service;

import com.example.ecommerce.repository.ImportedGoodRepository;

import com.example.ecommerce.model.ImportedGood;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportedGoodService{
    private final ImportedGoodRepository importedGoodRepository;

    public ImportedGoodService(ImportedGoodRepository importedGoodRepository) {
        this.importedGoodRepository = importedGoodRepository;
    }
    public List<ImportedGood> getAllImportedGood() {
        return importedGoodRepository.findAll();
    }
    public ImportedGood getImportedGoodById(Long id) {
        return importedGoodRepository.findById(id).orElse(null);
    }
    public ImportedGood createImportedGood(ImportedGood seo) {
        return importedGoodRepository.save(seo);
    }
    public ImportedGood updateImportedGood(Long id, ImportedGood updatedImportedGood) {
        ImportedGood existingImportedGood = importedGoodRepository.findById(id).orElse(null);
        if (existingImportedGood != null) {
            existingImportedGood.setNote(updatedImportedGood.getNote());
            existingImportedGood.setPrice(updatedImportedGood.getPrice());
            return importedGoodRepository.save(existingImportedGood);
        }
        return null;
    }
}
