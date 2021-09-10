package nandor.ledenyi.motogpdb.mapper;

import nandor.ledenyi.motogpdb.model.Country;
import nandor.ledenyi.motogpdb.model.GrandPrix;
import nandor.ledenyi.motogpdb.model.Rider;
import nandor.ledenyi.motogpdb.model.dto.GrandPrixDto;
import org.springframework.stereotype.Component;

@Component
public class GrandPrixMapper {

    public GrandPrixDto convertToDto(GrandPrix grandPrix) {
        return new GrandPrixDto(
                grandPrix.getId(),
                grandPrix.getName(),
                grandPrix.getCountry().getId(),
                grandPrix.getWinningRider().getId());
    }

    public GrandPrix convertToEntity(GrandPrixDto grandPrixDto, Country country, Rider winningRider) {
        return new GrandPrix(
                grandPrixDto.getId(),
                grandPrixDto.getName(),
                country,
                winningRider
        );
    }
}
