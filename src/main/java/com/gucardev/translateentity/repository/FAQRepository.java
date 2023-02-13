package com.gucardev.translateentity.repository;

import com.gucardev.translateentity.model.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long>, CustomFAQRepository {}
