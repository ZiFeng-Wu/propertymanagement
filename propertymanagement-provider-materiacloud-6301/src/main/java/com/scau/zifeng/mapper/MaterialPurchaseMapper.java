package com.scau.zifeng.mapper;

import com.scau.zifeng.entities.MaterialPurchase;
import com.scau.zifeng.entities.MaterialPurchaseExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MaterialPurchaseMapper {
    long countByExample(MaterialPurchaseExample example);

    int deleteByExample(MaterialPurchaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MaterialPurchase record);

    int insertSelective(MaterialPurchase record);

    List<MaterialPurchase> selectByExample(MaterialPurchaseExample example);

    MaterialPurchase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MaterialPurchase record, @Param("example") MaterialPurchaseExample example);

    int updateByExample(@Param("record") MaterialPurchase record, @Param("example") MaterialPurchaseExample example);

    int updateByPrimaryKeySelective(MaterialPurchase record);

    int updateByPrimaryKey(MaterialPurchase record);
}