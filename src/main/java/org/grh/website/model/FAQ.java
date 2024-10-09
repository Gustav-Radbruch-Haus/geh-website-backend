package org.grh.website.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FAQ {

    @Id
    private Long id;

    @Column(nullable = false, length = 2)
    private String language;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private FAQCategory category;
}
