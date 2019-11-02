package leo.wan.dao;

import java.util.List;
import leo.wan.model.SupplierAnswer;
import leo.wan.model.SupplierAnswerExample;
import org.apache.ibatis.annotations.Param;

public interface SupplierAnswerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    int countByExample(SupplierAnswerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    int deleteByExample(SupplierAnswerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    int insert(SupplierAnswer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    int insertSelective(SupplierAnswer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    List<SupplierAnswer> selectByExample(SupplierAnswerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    SupplierAnswer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SupplierAnswer record, @Param("example") SupplierAnswerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SupplierAnswer record, @Param("example") SupplierAnswerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SupplierAnswer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table supplier_answer
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SupplierAnswer record);
}