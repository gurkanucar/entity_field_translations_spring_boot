package com.gucardev.translateentity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FAQProjection {

  Long id;
  String question;
  String questionAnswer;
  String questionDetail;
  String languageSlug;
}
