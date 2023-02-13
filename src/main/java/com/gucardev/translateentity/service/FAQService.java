package com.gucardev.translateentity.service;

import com.gucardev.translateentity.dto.FAQProjection;
import com.gucardev.translateentity.dto.FAQTranslationDTO;
import com.gucardev.translateentity.model.FAQ;
import com.gucardev.translateentity.model.FAQTranslation;
import com.gucardev.translateentity.model.Language;
import com.gucardev.translateentity.repository.FAQRepository;
import com.gucardev.translateentity.repository.FAQTranslationRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FAQService {

  private final FAQRepository faqRepository;
  private final FAQTranslationRepository faqTranslationRepository;
  private final LanguageService languageService;

  public List<FAQProjection> getFaqById(Long id) {
    return faqRepository.findFaqByIdAndBringWithOtherFields(id);
  }

  public List<FAQTranslationDTO> getByLanguage(String languageSlug) {
    return getByLanguage(languageService.findBySlug(languageSlug));
  }

  public List<FAQTranslationDTO> getByLanguage(Language language) {
    List<FAQTranslation> translations = faqTranslationRepository.findByLanguage(language);
    return translations.stream().map(FAQTranslationDTO::new).collect(Collectors.toList());
  }

  public FAQTranslationDTO getFaqTranslationByIdAndLanguage(Long id, String languageSlug) {
    return getFaqTranslationByIdAndLanguage(id, languageService.findBySlug(languageSlug));
  }

  public FAQTranslationDTO getFaqTranslationByIdAndLanguage(Long id, Language language) {
    var translation =
        faqTranslationRepository
            .findByFaqIdAndLanguage(id, language)
            .orElseThrow(() -> new RuntimeException("FAQ Translation not found!"));
    return new FAQTranslationDTO(translation);
  }

  public FAQ createFaq(FAQ faq) {
    return faqRepository.save(faq);
  }

  public FAQTranslation createFaqTranslation(FAQTranslation faqTranslation) {
    return faqTranslationRepository.save(faqTranslation);
  }

  @Transactional
  public void addToTranslation(FAQ faq, FAQTranslation faqTranslation) {
    var existing = faqRepository.findById(faq.getId());
    if (existing.isEmpty()) {
      throw new RuntimeException("faq not found!");
    }

    FAQTranslation saved = createFaqTranslation(faqTranslation);
    FAQ managed = existing.get();
    managed.getTranslations().add(saved);
    faqRepository.save(managed);
  }
}
