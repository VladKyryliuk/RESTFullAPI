package vla.kyr.restfullapi.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private  String lastName;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name =  "birth_date")
    private LocalDate birthDate;

    @Nullable
    @Column(name = "address")
    private String address;

    @Nullable
    @Column(name = "phone_number")
    private String phoneNumber;


    public User(@NonNull String firstName, @NonNull String lastName,
                @NonNull String email, @NonNull LocalDate birthDate, @Nullable String address, @Nullable String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
