package leo.wan.dao;

import leo.wan.model.QuestionItem;
import leo.wan.model.QuestionItemExample;
import leo.wan.model.QuestionItemExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionItemMapperExt extends QuestionItemMapper {
    List<QuestionItemExt> getQuestionItemsByPage(Map params);

    List<QuestionItemExt> getQuestionItems();

    List<QuestionItemExt> getQuestionItemsWithSelect();

    void insertBatch(@Param("questionItems") List<QuestionItem> questionItems);
}