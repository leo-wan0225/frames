package leo.wan.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import leo.wan.dao.QuestionItemMapperExt;
import leo.wan.model.QuestionItem;
import leo.wan.model.QuestionItemExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class QuestionItemService {
    @Autowired
    private QuestionItemMapperExt questionItemMapperExt;
    public  void  getQuestionItemsByPage(){
        Map params = new HashMap();
        leo.wan.common.Page page = new leo.wan.common.Page(1, 3);
        params.put("page", page);
        List<QuestionItemExt> questionItemExts = questionItemMapperExt.getQuestionItemsByPage(params);
        Page page1 = PageHelper.startPage(1,3);
       // questionItemMapperExt.getQuestionItemsByPage();
    }
}
