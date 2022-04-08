package com.devcommunity.nabong.common.config;

import com.devcommunity.nabong.common.jwt.*;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
            TokenProvider tokenProvider,
            CorsFilter corsFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    //해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    //service에서 비밀번호를 암호화 할 수 있도록 bean으로 등록
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }


    //js, css, image 설정은 보안설정의 영향 밖에 있도록 만듬
    @Override
    public void configure(WebSecurity web) throws Exception {
        web .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()  //csrf : 자신의 의지와는 무관하게 특정 웹사이트에(수정, 삭제, 등록 등) 요청하는 행위
                                //토큰 방식이기 때문에 설정을 안함

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //우리가 만든 클래스들로 설정해 핸들링
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()  //해당 주소의 요청은 인증없이 접근을 허용하겠다는 의미
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/manager/**").authenticated()  //해당들에 대해서는 모두 인증을 받아야 한다는 의미
                .antMatchers("/admin/**").authenticated()


                /*    //.antMatchers("/api/user/**").authenticated()
                    .antMatchers("/manager/**").authenticated()
                   // .antMatchers("/api/support/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                //.antMatchers("/api/support/**").hasRole("100")
                .anyRequest().permitAll()
//               .and()
//                    .formLogin()
//                    .loginPage("/user/Login")
//                .usernameParameter("userId")
//                .loginProcessingUrl("/login") //SecurityConfig
//                    .defaultSuccessUrl("/")*/


                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
