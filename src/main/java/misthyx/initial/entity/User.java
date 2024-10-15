package misthyx.initial.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="test_auths")
public class User {

   //1	Alex	Alex	lord
   //2	Mary	Mary	wife
   //3	Byte	Byte	dog

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name="username")
   private String name;

   private String password;
   private String role;

}