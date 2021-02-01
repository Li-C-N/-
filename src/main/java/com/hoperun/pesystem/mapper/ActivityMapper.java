package com.hoperun.pesystem.mapper;

import com.hoperun.pesystem.model.Activity;
import com.hoperun.pesystem.model.ActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    long countByExample(ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int deleteByExample(ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int deleteByPrimaryKey(Integer aId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int insert(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int insertSelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    List<Activity> selectByExampleWithBLOBsWithRowbounds(ActivityExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    List<Activity> selectByExampleWithBLOBs(ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    List<Activity> selectByExampleWithRowbounds(ActivityExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    List<Activity> selectByExample(ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    Activity selectByPrimaryKey(Integer aId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByExampleWithBLOBs(@Param("record") Activity record, @Param("example") ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByPrimaryKeyWithBLOBs(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity
     *
     * @mbg.generated Mon Feb 01 12:56:39 CST 2021
     */
    int updateByPrimaryKey(Activity record);
}