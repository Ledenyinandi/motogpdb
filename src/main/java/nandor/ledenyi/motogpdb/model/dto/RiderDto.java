package nandor.ledenyi.motogpdb.model.dto;

import lombok.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RiderDto {

    private Long id;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull
    @Min(value = 2, message = "Number of the bike must be higher than 1")
    @Max(value = 99, message = "Number of the bike must be less than 100")
    private int bikeNumber;

    private Long countryId;
}
