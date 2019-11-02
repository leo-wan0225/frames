package leo.wan.model;

import java.util.List;

public class QuestionItemExt extends QuestionItem{
    private List<SupplierAnswer> supplierAnswers;

    public List<SupplierAnswer> getSupplierAnswers() {
        return supplierAnswers;
    }

    public void setSupplierAnswers(List<SupplierAnswer> supplierAnswers) {
        this.supplierAnswers = supplierAnswers;
    }
}
