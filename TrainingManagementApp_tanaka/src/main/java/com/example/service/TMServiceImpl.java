package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.MUser;
import com.example.repository.TMMapper;

@Service
public class TMServiceImpl implements TMService {

	@Autowired
	private TMMapper mapper;
	
	//ユーザー1件登録
	@Override
	public void signup(MUser user) {
		user.setRoleCode(2);
		mapper.insertOne(user);
	}
	
}
