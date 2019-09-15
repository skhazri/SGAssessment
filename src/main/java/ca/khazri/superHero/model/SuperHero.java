package ca.khazri.superHero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name="superhero")
@Entity
@Data
@NoArgsConstructor

@Setter
@Getter
@ToString(exclude="missions")
@EqualsAndHashCode(exclude="missions")

public class SuperHero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private String superheroName;

    @ManyToMany
            (fetch = FetchType.LAZY,
            cascade = {
                CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REMOVE
            })

    @JoinTable(name="superhero_mission",
    joinColumns = {@JoinColumn(name = "superhero_id")} ,
    inverseJoinColumns = {@JoinColumn(name = "mission_id")}
    )

    public Set<Mission> missions = new HashSet<Mission> ();


    public SuperHero (String firstName, String lastName, String superheroName, Set<Mission> missions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.superheroName = superheroName;
        this.missions = missions;
    }
    public SuperHero(@NotBlank Long id, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String superheroName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.superheroName = superheroName;
    }

    public SuperHero (String firstName, String lastName, String superheroName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.superheroName = superheroName;
    }

    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    public void removeMission(Mission mission) {
        this.missions.remove(mission);
    }

}
