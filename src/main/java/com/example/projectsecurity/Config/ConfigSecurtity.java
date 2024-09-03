package com.example.projectsecurity.Config;
import com.example.projectsecurity.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurtity {
    private final MyUserDetailsService myUserDetailsService;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //focus on username
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        //focus on password
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("api/v1/auth/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/delete/{id}").hasAuthority("ADMIN")

                .requestMatchers("/api/v1/customer/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/customer/register").permitAll()
                .requestMatchers("/api/v1/customer/update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/delete/{auth_id}").hasAnyAuthority("CUSTOMER")

                .requestMatchers("/api/v1/employee/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/employee/register").permitAll()
                .requestMatchers("/api/v1/employee/update").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/employee/delete").hasAnyAuthority("EMPLOYEE")


                .requestMatchers("/api/v1/account/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/account/get-my").hasAnyAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/add").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account//setActivationAccount/{account_id}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/delete/{auth_id}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/delete").hasAnyAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/account-details/{account_id}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/blockAccount/{account_id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/account/deposit-money/{account_id}/{amount}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/withdraw-money/{account_id}/{amount}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/transfer-Funds/{Account1_id}/{Account2_id}/{amount}").hasAuthority("CUSTOMER")





                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();}

}
