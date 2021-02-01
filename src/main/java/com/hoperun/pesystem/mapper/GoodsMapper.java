package com.hoperun.pesystem.mapper;

import com.hoperun.pesystem.model.Goods;
import com.hoperun.pesystem.model.GoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    long countByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int deleteByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int deleteByPrimaryKey(Integer goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int insert(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int insertSelective(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    List<Goods> selectByExampleWithBLOBsWithRowbounds(GoodsExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    List<Goods> selectByExampleWithRowbounds(GoodsExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    List<Goods> selectByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    Goods selectByPrimaryKey(Integer goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByPrimaryKeySelective(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByPrimaryKeyWithBLOBs(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByPrimaryKey(Goods record);
}