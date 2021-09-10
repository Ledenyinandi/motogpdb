package nandor.ledenyi.motogpdb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {

    private Long id;

    @NotBlank(message = "Name cannot be null!")
    private String name;
}
