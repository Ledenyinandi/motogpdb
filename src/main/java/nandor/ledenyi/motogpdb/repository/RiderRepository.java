package nandor.ledenyi.motogpdb.repository;

import nandor.ledenyi.motogpdb.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Long> {
}