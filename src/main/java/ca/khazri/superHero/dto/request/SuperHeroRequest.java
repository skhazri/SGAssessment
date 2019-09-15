package ca.khazri.superHero.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


//@Getter
//@Setter
//@Accessors(chain = true)
//@NoArgsConstructor
//@ToString
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@Data
public class SuperHeroRequest  implements Serializable {


    private String firstName;

    private String lastName;

    private String superheroName;

    public SuperHeroRequest (String firstName, String lastName, String superheroName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.superheroName = superheroName;
    }

    public SuperHeroRequest ( ) {
    }


    public String getFirstName ( ) {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName ( ) {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getSuperheroName ( ) {
        return superheroName;
    }

    public void setSuperheroName (String superheroName) {
        this.superheroName = superheroName;
    }
}
