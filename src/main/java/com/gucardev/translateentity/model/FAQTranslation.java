package com.gucardev.translateentity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "faq_translation")
@Builder
@NamedQuery(
    name = "FAQTranslation.findByLanguage",
    query = "SELECT ft FROM FAQTranslation ft WHERE ft.language = :language")
public class FAQTranslation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "faq_id")
  private FAQ faq;

  @ManyToOne
  @JoinColumn(name = "language_id")
  private Language language;

  private String question;
  private String questionDetail;
  private String questionAnswer;
}
