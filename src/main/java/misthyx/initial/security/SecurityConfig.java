package misthyx.initial.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig{

   @Bean //возвращаем кастомный MyUserDetailsService, который напишем далее
   public UserDetailsService userDetailsService(){
      return new MyUserDetailsService();
   }

   @Bean
   public AuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setUserDetailsService(userDetailsService());
      provider.setPasswordEncoder(passwordEncoder());
      return provider;
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return  http
              .csrf(AbstractHttpConfigurer::disable)
              .authorizeHttpRequests(auth -> auth.requestMatchers("welcome").permitAll() //вход без авторизации
                      .requestMatchers("**").authenticated()) //с авторизацией и аутентификацией
              .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
              .build();
   }

   @Bean //Ставим степень кодировки, с которой кодировали пароль в базе
   public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder(5);
   }

//   @Bean
//   protected UserDetailsService uds() {
//      User lord = (User) User.builder()
//              .username("asd")
//              .password(pswEncoder().encode("asd"))
//              .authorities("LORD")
//              .roles("LORD")
//              .build();
//
//      return new InMemoryUserDetailsManager(lord);
//   }

}