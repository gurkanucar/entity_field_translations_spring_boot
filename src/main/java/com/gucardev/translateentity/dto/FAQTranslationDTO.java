package com.gucardev.translateentity.dto;

import com.gucardev.translateentity.model.FAQTranslation;
import lombok.Data;

@Data
public class FAQTranslationDTO {
  private Long id;
  private Long faqId;
  private Long languageId;
  private String question;
  private String questionDetail;
  private String questionAnswer;

  public FAQTranslationDTO(FAQTranslation faqTranslation) {
    this.id = faqTranslation.getId();
    this.faqId = faqTranslation.getFaq().getId();
    this.languageId = faqTranslation.getLanguage().getId();
    this.question = faqTranslation.getQuestion();
    this.questionDetail = faqTranslation.getQuestionDetail();
    this.questionAnswer = faqTranslation.getQuestionAnswer();
  }
}
