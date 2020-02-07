package com.scau.zifeng.mapper;

import com.scau.zifeng.entities.PayList;
import com.scau.zifeng.entities.PayListExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PayListMapper {
    long countByExample(PayListExample example);

    int deleteByExample(PayListExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PayList record);

    int insertSelective(PayList record);

    List<PayList> selectByExample(PayListExample example);

    PayList selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PayList record, @Param("example") PayListExample example);

    int updateByExample(@Param("record") PayList record, @Param("example") PayListExample example);

    int updateByPrimaryKeySelective(PayList record);

    int updateByPrimaryKey(PayList record);
}