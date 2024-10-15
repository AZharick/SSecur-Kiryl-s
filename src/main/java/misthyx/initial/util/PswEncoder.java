package misthyx.initial.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PswEncoder {
   static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);

   public static String encodePsw(String password) {
      return encoder.encode(password);
   }
}