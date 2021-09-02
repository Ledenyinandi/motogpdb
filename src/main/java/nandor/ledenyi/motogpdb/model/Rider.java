package nandor.ledenyi.motogpdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int bikeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Country country;

    @OneToMany(mappedBy = "winningRider")
    @JsonIgnore
    private List<GrandPrix> grandPrixs;

    public Rider(Long id, String name, int bikeNumber, Country country) {
        this.id = id;
        this.name = name;
        this.bikeNumber = bikeNumber;
        this.country = country;
    }
}
