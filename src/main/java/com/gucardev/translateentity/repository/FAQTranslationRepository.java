package com.gucardev.translateentity.repository;

import com.gucardev.translateentity.model.FAQTranslation;
import com.gucardev.translateentity.model.Language;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQTranslationRepository extends JpaRepository<FAQTranslation, Long> {

  List<FAQTranslation> findByLanguage(Language language);

  Optional<FAQTranslation> findByFaqIdAndLanguage(Long id, Language language);
}
