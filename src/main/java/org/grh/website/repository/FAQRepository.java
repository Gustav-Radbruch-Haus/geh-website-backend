package org.grh.website.repository;

import org.grh.website.model.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {
    List<FAQ> findByCategoryId(Long categoryId);

    List<FAQ> findByCategoryIdAndLanguage(Long categoryId, String language);

}
