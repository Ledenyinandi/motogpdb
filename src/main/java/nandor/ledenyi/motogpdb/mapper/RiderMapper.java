package nandor.ledenyi.motogpdb.mapper;

import nandor.ledenyi.motogpdb.model.Country;
import nandor.ledenyi.motogpdb.model.Rider;
import nandor.ledenyi.motogpdb.model.dto.RiderDto;
import org.springframework.stereotype.Component;

@Component
public class RiderMapper {

    public RiderDto convertToDto(Rider rider) {
        return new RiderDto(
                rider.getId(),
                rider.getName(),
                rider.getBikeNumber(),
                rider.getCountry().getId()
        );
    }

    public Rider convertToEntity(RiderDto riderDto, Country country) {
        return new Rider(
                riderDto.getId(),
                riderDto.getName(),
                riderDto.getBikeNumber(),
                country
        );
    }
}
