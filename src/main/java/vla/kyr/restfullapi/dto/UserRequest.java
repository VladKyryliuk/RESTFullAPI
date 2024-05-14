package vla.kyr.restfullapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User Request", description = "Uses for sending requests in DB")
public class UserRequest {

    @NotNull(message = "firstname shouldn't be null!")
    @Schema(name = "firstName",
            description = "User first name",
            example = "John",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private String firstName;

    @NotNull(message = "lastname shouldn't be null!")
    @Schema(name = "lastName",
            description = "User last name",
            example = "Lennon",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private  String lastName;

    @Email(message = "invalid email address")
    @Schema(name = "email",
            description = "User Email",
            example = "john.lennon@mail.com",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private String email;

    @NotNull(message = "birthDate shouldn't be null!")
    @Schema(name = "birthDate",
            description = "User Birth Date",
            example = "2003-04-05",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private LocalDate birthDate;

    @Nullable
    @Schema(name = "address",
            description = "(Optional field) User residential address",
            example = "Suite 198 70333 Clinton Causeway, South Leonardo, KS 96234",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private String address;

    @Nullable
    @Pattern(regexp = "^\\d{12}$", message = "phone number shouldn't be less 12 numbers!")
    @Schema(name = "phoneNumber",
            description = "(Optional field) User phone number",
            example = "380660481994",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private String phoneNumber;

}
