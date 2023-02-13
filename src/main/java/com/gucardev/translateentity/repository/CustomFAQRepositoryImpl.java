package com.gucardev.translateentity.repository;

import com.gucardev.translateentity.dto.FAQProjection;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.SqlResultSetMapping;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CustomFAQRepositoryImpl implements CustomFAQRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<FAQProjection> findFaqByIdAndBringWithOtherFields(Long faqId) {
    List<FAQProjection> result =
        entityManager
            .createNativeQuery(
                "  SELECT\n"
                    + "    FAQ_TRANSLATION.id as id,\n"
                    + "    FAQ_TRANSLATION.QUESTION as question,\n"
                    + "    FAQ_TRANSLATION.QUESTION_ANSWER as questionAnswer,\n"
                    + "    FAQ_TRANSLATION.QUESTION_DETAIL as questionDetail,\n"
                    + "    LANGUAGE.SLUG as languageSlug\n"
                    + "  FROM\n"
                    + "    FAQ\n"
                    + "    JOIN FAQ_TRANSLATION ON FAQ.id = FAQ_TRANSLATION.FAQ_ID\n"
                    + "    JOIN LANGUAGE ON LANGUAGE.id = FAQ_TRANSLATION.LANGUAGE_ID\n"
                    + "  where\n"
                    + "    FAQ.id= ?1",
                "FAQProjectionMapping")
            .setParameter(1, faqId)
            .getResultList();
    return result;
  }

  @SqlResultSetMapping(
      name = "FAQProjectionMapping",
      classes = {
        @ConstructorResult(
            targetClass = FAQProjection.class,
            columns = {
              @ColumnResult(name = "id", type = Long.class),
              @ColumnResult(name = "question", type = String.class),
              @ColumnResult(name = "questionAnswer", type = String.class),
              @ColumnResult(name = "questionDetail", type = String.class),
              @ColumnResult(name = "languageSlug", type = String.class)
            })
      })
  @Entity
  static class SQLMappingCfgEntity {
    @Id int id;
  } // <- workaround ~ we should put it to entity annotated class not pojo
}
