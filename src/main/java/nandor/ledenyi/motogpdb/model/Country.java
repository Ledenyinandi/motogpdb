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
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<GrandPrix> grandPrixs;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Rider> riders;

    public Country(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
