package com.example.service;

import com.example.model.MUser;

public interface TMService {

	//ユーザー1件登録
	public void signup(MUser user);
	
	//ログインユーザー情報取得
	public MUser getLoginUser(String email);
}

