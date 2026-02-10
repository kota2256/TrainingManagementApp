package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration				// 設定ファイルであることを示すアノテーション
@EnableWebSecurity	//カスタムセキュリティ設定を有効（デフォルトのセキュリティ設定が無効）にする
public class SecurityConfig {		//WebSecurityConfigurerAdapterは現在非推奨
	
//	@Autowired
//	private UserDetailsService userDetailsService;
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())	//csrf.ignoringRequestMatchers("/h2-console/**")
			.headers(h -> h.frameOptions(f -> f.sameOrigin()))
			.authorizeHttpRequests(authz -> authz
				.requestMatchers("/css/**").permitAll()
				.requestMatchers("/webjars/**").permitAll()
				.requestMatchers("/js/**").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/signup").permitAll()
				//.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() 	//この設定でcssなどはログインなしでもアクセス可
				.anyRequest().authenticated()
			).formLogin(login -> login
				.loginProcessingUrl("/login")	//ログイン処理のパス
				.loginPage("/login")	//ログインページの指定
				.usernameParameter("email")
				.passwordParameter("password")
				.failureUrl("/login?error")	//ログイン失敗時のリダイレクト先
				.defaultSuccessUrl("/home", true)	//認証後にリダイレクトする場所を指定
				.permitAll());	
		return http.build();	
	}
	
	//パスワードをエンコードする方法を設定
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//インメモリ認証
	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		//ユーザーオブジェクト作成
		UserDetails user = User
			.withUsername("user@mail.com")
			.password(passwordEncoder().encode("user"))
			.roles("GENERAL")
			.build();
		UserDetails admin = User
			.withUsername("admin@mail.com")
			.password(passwordEncoder().encode("admin"))
			.roles("ADMIN")
			.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	// ユーザー認証処理
	
}
