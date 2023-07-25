//package io.dustin.apps.common.config;
//
////import io.dustin.apps.user.jwt.JwtAccessDeniedHandler;
////import io.dustin.apps.user.jwt.JwtAuthenticationEntryPoint;
////import io.dustin.apps.user.jwt.TokenProvider;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
////@RequiredArgsConstructor
//@Configuration
//public class SecurityConfig {
//
////    private final TokenProvider tokenProvider;
////    private final JwtAuthenticationEntryPoint jwtAtuthenticationEntryPoint;
////    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public WebSecurityCustomizer webSecurityCustomizer(){
////        return (web) -> web.ignoring()
////                .requestMatchers("/favicon.ico");
////    }
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        return http
////                .csrf(csrf-> csrf.disable())
////
////                /**401, 403 Exception 핸들링 */
////                .exceptionHandling(exceptionHandling -> exceptionHandling
////                        .authenticationEntryPoint(jwtAtuthenticationEntryPoint)
////                        .accessDeniedHandler(jwtAccessDeniedHandler)
////                )
////
////                /**세션 사용하지 않음*/
//////                .and()
//////                .sessionManagement()
//////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .sessionManagement((session) -> session
////                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                );
////
////                /** HttpServletRequest를 사용하는 요청들에 대한 접근 제한 설정*/
////                .and()
////                .authorizeRequests()
////                .antMatchers("/authenticate").permitAll()
////
////                /**JwtSecurityConfig 적용 */
////                .addFilter()
////                .apply(new JwtSecurityConfig(tokenProvider))
////
////                .and.build();
////    }
//}