package com.example.demo.config;

import com.example.demo.details.CustomUserDetailsService;
import com.example.demo.evaluator.CustomPermissionEvaluator;
import com.example.demo.handler.CustomAuthenticationFailureHandler;
import com.example.demo.handler.CustomAuthenticationSuccessHandler;
import com.example.demo.logout.CustomLogoutSuccessHandler;
import com.example.demo.qq.QQLoginConfigure;
import com.example.demo.session.CustomExpiredSessionStrategy;
import com.example.demo.sms.SmsCodeAuthenticationSecurityConfig;
import com.example.demo.verify.authentic.CustomAuthenticationProvider;
import com.example.demo.verify.filter.VerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * @ClassName WebSecurityConfig
 * @Description security配置文件
 * @PackageName com.example.demo.config.WebSecurityConfig
 * @Author fate
 * @Date 2020/10/18 13:27
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private QQLoginConfigure qqLoginConfigure;

    /**
     * 注入自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        };
    }

    // 角色继承
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
//    @Bean
//    public RoleHierarchy roleHierarchy() {
//        String separator = System.lineSeparator();
//
//        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//        String hierarchy = "ROLE_ADMIN > ROLE_USER " + separator + " ROLE_USER > ROLE_TOURISTS";
//        roleHierarchy.setHierarchy(hierarchy);
//        return roleHierarchy;
//    }


//    基本
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return s.equals(charSequence.toString());
//            }
//        });
//    }

//    密码加密
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }

    //手动校验
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.apply(smsCodeAuthenticationSecurityConfig).and()
                .apply(qqLoginConfigure).and()
                .authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/getVerifyCode").permitAll()
                .antMatchers("/login/invalid").permitAll()
                .antMatchers("/sms/**").permitAll()

                // 认证
                .anyRequest().authenticated()
                .and()

                // 设置登陆页
                .formLogin().loginPage("/login")

                // 设置登陆成功页
//                .defaultSuccessUrl("/")
                .successHandler(customAuthenticationSuccessHandler)
                // 登录失败Url
//                .failureUrl("/login/error")
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll()

                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")

                // 指定authenticationDetailsSource
                .authenticationDetailsSource(authenticationDetailsSource).and()
                // 图形验证码过滤器
//                .and().addFilterBefore(new VerifyFilter(), UsernamePasswordAuthenticationFilter.class)

                // 退出登录
//                .logout().permitAll()
//                .and()

                // 自动登录
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    // 有效时间：单位s
                    .tokenValiditySeconds(60)
                    .userDetailsService(userDetailsService);

        // session管理
        http.sessionManagement()
            // 以下二选一
            //.invalidSessionStrategy()
            .invalidSessionUrl("/login/invalid")
            .maximumSessions(1)
            // 当达到最大值时，是否保留已经登录的用户
            .maxSessionsPreventsLogin(false)
            // 当达到最大值时，旧用户被踢出后的操作
//            .expiredSessionStrategy(new CustomExpiredSessionStrategy());
            // 主动踢出用户
            .sessionRegistry(sessionRegistry());

        // 退出登录
        http.logout()
                // 退出 Url
//                .logoutUrl("/signout")
                // 清除浏览器的 Cookie
                .deleteCookies("JSESSIONID")
                // 置退出后处理的逻辑
                .logoutSuccessHandler(logoutSuccessHandler);

        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }
}

