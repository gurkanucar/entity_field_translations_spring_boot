package com.gucardev.translateentity.service;

import com.gucardev.translateentity.model.Language;
import com.gucardev.translateentity.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LanguageService {

  private final LanguageRepository languageRepository;

  public Language findBySlug(String slug) {
    return languageRepository
        .findBySlug(slug)
        .orElseThrow(() -> new RuntimeException("language not found!"));
  }

  public Language create(Language language) {
    return languageRepository.save(language);
  }
}
