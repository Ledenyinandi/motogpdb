package nandor.ledenyi.motogpdb.repository;

import nandor.ledenyi.motogpdb.model.GrandPrix;
import nandor.ledenyi.motogpdb.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GrandPrixRepository extends JpaRepository<GrandPrix, Long> {

    @Query("select t from Rider t where t.id in (select s.winningRider from GrandPrix s where s.id = :id)")
    Rider getWinningRiderByGrandPrixId(Long id);
}
