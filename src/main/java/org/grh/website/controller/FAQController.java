package org.grh.website.controller;


import org.grh.website.model.FAQ;
import org.grh.website.model.FAQCategory;
import org.grh.website.repository.FAQCategoryRepository;
import org.grh.website.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${baseUrl}/faq")
public class FAQController {

    private final FAQRepository faqRepository;

    private final FAQCategoryRepository faqCategoryRepository;

    @Autowired
    public FAQController(FAQRepository faqRepository, FAQCategoryRepository faqCategoryRepository) {
        this.faqRepository = faqRepository;
        this.faqCategoryRepository = faqCategoryRepository;
    }

    @PostMapping("/question/create")
    public FAQ createFAQ(@RequestBody FAQ faq) {
        FAQCategory category = faqCategoryRepository.findById(faq.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        faq.setCategory(category);
        return faqRepository.save(faq);
    }

    @PostMapping("/category/create")
    public FAQCategory createFAQCategory(@RequestBody FAQCategory faqCategory) {
        return faqCategoryRepository.save(faqCategory);
    }
}