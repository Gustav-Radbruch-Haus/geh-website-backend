// FAQCategoryRepository.java
package org.grh.website.repository;

import org.grh.website.model.FAQ;
import org.grh.website.model.FAQCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FAQCategoryRepository extends JpaRepository<FAQCategory, Long> {
    List<FAQCategory> findFAQCategoriesByLanguage(String language);
}

// FAQRepository.java


