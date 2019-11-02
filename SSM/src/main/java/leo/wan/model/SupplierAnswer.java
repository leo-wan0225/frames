package leo.wan.model;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table supplier_answer
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class SupplierAnswer {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column supplier_answer.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column supplier_answer.supplier_answer
     *
     * @mbggenerated
     */
    private String supplierAnswer;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column supplier_answer.supplier_id
     *
     * @mbggenerated
     */
    private String supplierId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column supplier_answer.supplier_name
     *
     * @mbggenerated
     */
    private String supplierName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column supplier_answer.question_id
     *
     * @mbggenerated
     */
    private String questionId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column supplier_answer.id
     *
     * @return the value of supplier_answer.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column supplier_answer.id
     *
     * @param id the value for supplier_answer.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column supplier_answer.supplier_answer
     *
     * @return the value of supplier_answer.supplier_answer
     *
     * @mbggenerated
     */
    public String getSupplierAnswer() {
        return supplierAnswer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column supplier_answer.supplier_answer
     *
     * @param supplierAnswer the value for supplier_answer.supplier_answer
     *
     * @mbggenerated
     */
    public void setSupplierAnswer(String supplierAnswer) {
        this.supplierAnswer = supplierAnswer == null ? null : supplierAnswer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column supplier_answer.supplier_id
     *
     * @return the value of supplier_answer.supplier_id
     *
     * @mbggenerated
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column supplier_answer.supplier_id
     *
     * @param supplierId the value for supplier_answer.supplier_id
     *
     * @mbggenerated
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column supplier_answer.supplier_name
     *
     * @return the value of supplier_answer.supplier_name
     *
     * @mbggenerated
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column supplier_answer.supplier_name
     *
     * @param supplierName the value for supplier_answer.supplier_name
     *
     * @mbggenerated
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column supplier_answer.question_id
     *
     * @return the value of supplier_answer.question_id
     *
     * @mbggenerated
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column supplier_answer.question_id
     *
     * @param questionId the value for supplier_answer.question_id
     *
     * @mbggenerated
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }
}