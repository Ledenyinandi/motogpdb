package nandor.ledenyi.motogpdb.repository;

import nandor.ledenyi.motogpdb.model.Country;
import nandor.ledenyi.motogpdb.model.GrandPrix;
import nandor.ledenyi.motogpdb.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("select t from GrandPrix t where t.country.id = :id")
    List<GrandPrix> getGrandPrixsFromCountry(Long id);

    @Query("select t from Rider t where t.country.id = :id")
    List<Rider> getRidersFromCountry(Long id);
}
