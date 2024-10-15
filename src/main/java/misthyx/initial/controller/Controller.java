package misthyx.initial.controller;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

   @GetMapping("common")
   String common() {
      return "<h1> Welcome! </h1>";
   }

   @GetMapping("closed")
   @DenyAll
   String closed() {
      return "<h1> YOU'RE NOT SUPPOSED TO SEE CONTENTS OF THIS PAGE! </h1>";
   }

   @GetMapping("open")
   @PermitAll
   String open() {
      return "<h1> PERMIT ALL! </h1>";
   }

   @GetMapping("lord")
   @PreAuthorize("hasAuthority ('lord')")
   String lord() {
      return "<h1> THIS IS A PAGE EXCLUSIVELY FOR LORD! </h1>";
   }

   @GetMapping("info")
   String info() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      return "<h2>authenticated:" + auth.isAuthenticated() + "<br>" +    // true
              "authorities: " + auth.getAuthorities() + "<br>" +  // []
              "credentials: " + auth.getCredentials() + "<br><br>" +  // null
              "principal: " + auth.getPrincipal() + "</h2>";
      /* principal: org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true,
      AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[]]
      */
   }

   //==========

   @GetMapping("/welcome")
   public String welcome(){
      return "This is unprotected page";
   }

   @GetMapping("/users")
   @PreAuthorize("hasAuthority('ROLE_USER')")
   public String pageForUser(){
      return "This is page for only users";
   }


   @GetMapping("/admins")
   @PreAuthorize("hasAuthority('ROLE_ADMIN')")
   public String pageForAdmins(){
      return "This is page for only admins";
   }


   @GetMapping("/all")
   public String pageForAll(){
      return "This is page for all employees";
   }

}