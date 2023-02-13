package com.gucardev.translateentity.controller;

import com.gucardev.translateentity.dto.FAQTranslationDTO;
import com.gucardev.translateentity.dto.FAQProjection;
import com.gucardev.translateentity.service.FAQService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faq")
@RequiredArgsConstructor
@Slf4j
public class FaqController {

  private final FAQService faqService;

  @GetMapping
  List<FAQTranslationDTO> getFaqByLanguage() {
    log.info("locale: {}", LocaleContextHolder.getLocale());
    return faqService.getByLanguage(String.valueOf(LocaleContextHolder.getLocale()));
  }

  @GetMapping("/{faqId}")
  List<FAQProjection> getFaqById(@PathVariable Long faqId) {
    return faqService.getFaqById(faqId);
  }

  @GetMapping("/translation/{faqId}")
  FAQTranslationDTO getFaqTranslationsByLanguage(@PathVariable Long faqId) {
    log.info("locale: {}", LocaleContextHolder.getLocale());
    return faqService.getFaqTranslationByIdAndLanguage(
        faqId, String.valueOf(LocaleContextHolder.getLocale()));
  }
}
