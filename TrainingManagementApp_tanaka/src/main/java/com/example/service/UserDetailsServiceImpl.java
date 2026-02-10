package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.model.MUser;

//@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	TMService tmService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		//ユーザー情報取得
		MUser loginUser = tmService.getLoginUser(email);
		
		//ユーザーが存在しない場合の処理
		if (loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		//権限リストの作成
		String role = "GENERAL";
		if (loginUser.getRoleCode()==1) {
			role = "ADMIN";
		}
		GrantedAuthority authority = new SimpleGrantedAuthority(role);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		
		//UserDetails生成
		UserDetails userDetails = (UserDetails) new User(loginUser.getEmail(), loginUser.getPassword(), authorities);
		
		return userDetails;
	}

}
