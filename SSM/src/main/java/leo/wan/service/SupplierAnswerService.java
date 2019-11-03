package leo.wan.service;

import leo.wan.dao.SupplierAnswerMapper;
import leo.wan.model.SupplierAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierAnswerService {
    @Autowired
    private SupplierAnswerMapper supplierAnswerMapper;
    public  void add(){
        SupplierAnswer supplierAnswer = new SupplierAnswer();
        supplierAnswer.setQuestionId("1");
        supplierAnswer.setSupplierAnswer("1");
        supplierAnswer.setSupplierName("ces");
        supplierAnswerMapper.insert(supplierAnswer);
        SupplierAnswer supplierAnswer2 = new SupplierAnswer();
        supplierAnswer2.setQuestionId("1");
        supplierAnswer2.setSupplierAnswer("1");
        supplierAnswer2.setSupplierName("ces");
        supplierAnswerMapper.insert(supplierAnswer);
    }
}
