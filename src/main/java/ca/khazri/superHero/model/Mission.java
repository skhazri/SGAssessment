package ca.khazri.superHero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Table(name = "mission")
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Mission  implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    private String  missionName;

    @NotNull
    private Boolean isCompleted;
    @NotNull
    private Boolean isDeleted;


    @ManyToMany (fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REMOVE},
            mappedBy =  "missions" )

    @JsonIgnore

    public Set<SuperHero> heroes = new HashSet<> (  );

    public Mission (String missionName, Boolean isCompleted, Boolean isDeleted, Set<SuperHero> heroes) {
        this.missionName = missionName;
        this.isCompleted = isCompleted;
        this.isDeleted = isDeleted;
        this.heroes = heroes;
    }

    public Mission (String missionName, Boolean isCompleted, Boolean isDeleted) {
        this.missionName = missionName;
        this.isCompleted = isCompleted;
        this.isDeleted = isDeleted;
    }

    public void addSuperHero(SuperHero superHero) {
        this.heroes.add(superHero);
    }

    public Mission(@NotBlank Long id, @NotBlank String missionName, Boolean isCompleted, Boolean isDeleted) {
        this.id = id;
        this.missionName = missionName;
        this.isCompleted = isCompleted;
        this.isDeleted = isDeleted;
    }
}
