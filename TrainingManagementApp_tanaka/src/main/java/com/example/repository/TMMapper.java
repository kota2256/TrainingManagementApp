package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.MUser;

@Mapper
public interface TMMapper {

	// 1件登録
	public int insertOne(MUser user);
	
}
