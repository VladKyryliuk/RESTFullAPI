package vla.kyr.restfullapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "User Response", description = "Uses for getting responses")
public class UserResponse {

    @Schema(name = "id",
            description = "user identifier",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(name = "firstName",
            description = "User first name",
            example = "John",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private String firstName;

    @Schema(name = "lastName",
            description = "User last name",
            example = "Lennon",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private  String lastName;

    @Schema(name = "email",
            description = "User Email",
            example = "john.lennon@mail.com",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private String email;

    @Schema(name = "birthDate",
            description = "User Birth Date",
            example = "2003-04-05",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private LocalDate birthDate;

    @Schema(name = "address",
            description = "(Optional field) User residential address",
            example = "Suite 198 70333 Clinton Causeway, South Leonardo, KS 96234",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private String address;

    @Schema(name = "phoneNumber",
            description = "(Optional field) User phone number",
            example = "380660481994",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private String phoneNumber;
}
