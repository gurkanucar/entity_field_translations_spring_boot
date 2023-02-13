package com.gucardev.translateentity.repository;

import com.gucardev.translateentity.dto.FAQProjection;
import java.util.List;

public interface CustomFAQRepository {

  List<FAQProjection> findFaqByIdAndBringWithOtherFields(Long faqId);
}
