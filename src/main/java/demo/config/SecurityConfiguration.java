package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		/* ユーザーの認証方式 */
			/*インメモリ使用
			 * 、データベースを使用
			 * 、エルダップ（ライトウェイトディレクトリアクセスプロトコル）
			 */
		 public void configure(AuthenticationManagerBuilder auth) throws Exception{

			// 「user,password」
			auth
				/*インメモリ使用
				.inMemoryAuthentication()
				.withUser("").password("").roles("");*/

				.inMemoryAuthentication()
				.withUser("user")
//				.password("password")
				.password(passwordEncoder().encode("password"))
				.roles("USER");

		}

		@Override
		/* ウェブアプリケーションが管理しているリソースへのアクセス制御 */
		 protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
				/* すべてのリクエストに *//* 認証済みであること */
				.anyRequest().authenticated()
				.and()
				/*  */
				/* ログインページ方式 */
				.formLogin();
				/* 上からのポップアップ方式 */
				//.httpBasic();
		 }


		@Bean //（依存性注入）プロジェクト内ならどこでも呼び出せる
		/* ハッシュ化（パスワード） */
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}


}
