package com.gucardev.translateentity;

import com.gucardev.translateentity.model.FAQ;
import com.gucardev.translateentity.model.FAQTranslation;
import com.gucardev.translateentity.model.Language;
import com.gucardev.translateentity.service.FAQService;
import com.gucardev.translateentity.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Startup implements CommandLineRunner {

  private final FAQService faqService;

  private final LanguageService languageService;

  @Override
  public void run(String... args) throws Exception {

    Language tr = languageService.create(Language.builder().slug("tr").detail("Turkish").build());
    Language en =
        languageService.create(Language.builder().slug("en_US").detail("English").build());

    FAQ faq = faqService.createFaq(new FAQ());
    FAQ faq2 = faqService.createFaq(new FAQ());

    FAQTranslation englishTranslation = new FAQTranslation();
    englishTranslation.setFaq(faq);
    englishTranslation.setLanguage(en);
    englishTranslation.setQuestion("Question in English");
    englishTranslation.setQuestionDetail("Question detail in English");
    englishTranslation.setQuestionAnswer("Question answer in English");

    FAQTranslation englishTranslation2 = new FAQTranslation();
    englishTranslation2.setFaq(faq2);
    englishTranslation2.setLanguage(en);
    englishTranslation2.setQuestion("Question2 in English");
    englishTranslation2.setQuestionDetail("Question2 detail in English");
    englishTranslation2.setQuestionAnswer("Question2 answer in English");

    FAQTranslation turkishTranslation = new FAQTranslation();
    turkishTranslation.setFaq(faq);
    turkishTranslation.setLanguage(tr);
    turkishTranslation.setQuestion("Question in Turkish");
    turkishTranslation.setQuestionDetail("Question detail in Turkish");
    turkishTranslation.setQuestionAnswer("Question answer in Turkish");

    FAQTranslation turkishTranslation2 = new FAQTranslation();
    turkishTranslation2.setFaq(faq2);
    turkishTranslation2.setLanguage(tr);
    turkishTranslation2.setQuestion("Question2 in Turkish");
    turkishTranslation2.setQuestionDetail("Question2 detail in Turkish");
    turkishTranslation2.setQuestionAnswer("Question2 answer in Turkish");

    faqService.addToTranslation(faq, englishTranslation);
    faqService.addToTranslation(faq, turkishTranslation);
    faqService.addToTranslation(faq2, englishTranslation2);
    faqService.addToTranslation(faq2, turkishTranslation2);

    log.info("list: {}", faqService.getByLanguage(tr).toString());

    log.info(
        "faq by id: {}", faqService.getFaqTranslationByIdAndLanguage(faq.getId(), en).toString());
  }
}
