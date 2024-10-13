// FAQService.java
package org.grh.website.service;


import org.grh.website.model.FAQ;
import org.grh.website.model.FAQCategory;
import org.grh.website.repository.FAQCategoryRepository;
import org.grh.website.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FAQService {

    private final FAQCategoryRepository faqCategoryRepository;

    private final FAQRepository faqRepository;

    @Autowired
    public FAQService(FAQCategoryRepository faqCategoryRepository, FAQRepository faqRepository) {
        this.faqCategoryRepository = faqCategoryRepository;
        this.faqRepository = faqRepository;
    }

    public List<FAQCategory> getAllCategories() {
        return faqCategoryRepository.findAll();
    }

    public List<FAQCategory> getAllCategories(String language) { return faqCategoryRepository.findFAQCategoriesByLanguage(language); }

    public List<FAQ> getFAQsByCategoryAndLanguage(String categoryCommonName, String language) {
        return faqRepository.findByCategoryIdentifierAndLanguage(categoryCommonName, language);
    }
}