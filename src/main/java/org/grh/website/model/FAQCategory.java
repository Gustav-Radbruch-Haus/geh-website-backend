// FAQCategory.java
package org.grh.website.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class FAQCategory {
    @Id
    private Long id;

    @Column(nullable = false, length = 2)
    private String language;

    @Column(nullable = false)
    private String title;

    private String image;

    private String link;

    private String description;

}


