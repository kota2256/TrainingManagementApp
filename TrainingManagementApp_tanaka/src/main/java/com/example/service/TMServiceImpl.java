package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.MUser;
import com.example.repository.TMMapper;

@Service
public class TMServiceImpl implements TMService {

	@Autowired
	private TMMapper mapper;
	@Autowired
	private PasswordEncoder encoder;
	
	//ユーザー1件登録
	@Override
	public void signup(MUser user) {
		encoder.encode(user.getPassword());
		user.setRoleCode(2);
		mapper.insertOne(user);
	}
	
	//ログインユーザー情報取得
	@Override
	public MUser getLoginUser(String email) {
		return mapper.findLoginUser(email);
	}
	
}
