// FAQController.java
package org.grh.website.controller;

import org.grh.website.model.FAQ;
import org.grh.website.model.FAQCategory;
import org.grh.website.service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${baseUrl}/public/faq")
public class FAQPublicController {

    private final FAQService faqService;

    @Autowired
    public FAQPublicController(FAQService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/categories/{language}")
    public ResponseEntity<List<FAQCategory>> getAllCategories(@PathVariable String language) {
        List<FAQCategory> categories = faqService.getAllCategories(language);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categories/{language}/{categoryName}/faqs")
    public ResponseEntity<List<FAQ>> getFAQsByCategory(@PathVariable String categoryName, @PathVariable String language) {
        List<FAQ> faqs = faqService.getFAQsByCategoryAndLanguage(categoryName, language);
        return ResponseEntity.ok(faqs);
    }
}