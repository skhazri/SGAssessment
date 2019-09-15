package ca.khazri.superHero.repository;

import ca.khazri.superHero.model.Mission;
import ca.khazri.superHero.model.SuperHero;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
@Rollback(false)
@Transactional


public class SuperHeroRepositoryTest {

    private final Long superHeroId = 1L;
    @Autowired
    private SuperHeroRepository superHeroRepository;

    @Test
    public void givenEmptyDBWhenFindOneByIdThenReturnEmptyOptional() {
        Optional<SuperHero> foundHero = superHeroRepository.findById (superHeroId);

        assertThat(foundHero.isPresent()).isEqualTo(false);
    }
    @Test
    public void givenSuperHeroInDBWhenFindOneByIdThenReturnOptionalWitMSuperHero() {
        // given
        SuperHero superHero = new SuperHero ("FirstName","LastName","SuperHeroName");
        superHeroRepository.save(superHero);

        // when
        Optional<SuperHero> foundHero = superHeroRepository.findById (superHero.getId ());

        // then
        assertThat(foundHero.isPresent ()).isEqualTo ( true );

        assertThat(foundHero
                .get()
                .getId ()).isEqualTo(superHero.getId ());
        assertThat(foundHero
                .get()
                .getFirstName ()).isEqualTo("FirstName");

        assertThat(foundHero
                .get()
                .getLastName ()).isEqualTo("LastName");
        assertThat(foundHero
                .get()
                .getSuperheroName ()).isEqualTo("SuperHeroName");
    }

    @Test
   public void givenSuperHeroListInDBWhenFindAllThenReturnListWitSuperHero() {
    // given
        SuperHero superHero1 = new SuperHero ("FirstName1","LastName1","SuperHeroName1");
        SuperHero superHero2 = new SuperHero ("FirstName2","LastName2","SuperHeroName2");
        SuperHero superHero3 = new SuperHero ("FirstName3","LastName3","SuperHeroName3");


        List heroes = new ArrayList<Mission> (  );
        heroes.add(superHero1);
        heroes.add(superHero2);
        heroes.add(superHero3);

        superHeroRepository.saveAll ( heroes );

        // when
        List<SuperHero> foundSuperHeroes = superHeroRepository.findAll ( );

        // assert
        assertEquals(foundSuperHeroes.size (),3);
        assertThat(foundSuperHeroes.get(0).getId()).isEqualTo(superHero1.getId ());
        assertThat(foundSuperHeroes.get(0).getFirstName()).isEqualTo("FirstName1");
        assertThat(foundSuperHeroes.get(0).getLastName()).isEqualTo("LastName1");
        assertThat(foundSuperHeroes.get(0).getSuperheroName()).isEqualTo("SuperHeroName1");
        assertThat(foundSuperHeroes.get(1).getId()).isEqualTo(superHero2.getId ());
        assertThat(foundSuperHeroes.get(1).getFirstName()).isEqualTo("FirstName2");
        assertThat(foundSuperHeroes.get(1).getLastName()).isEqualTo("LastName2");
        assertThat(foundSuperHeroes.get(1).getSuperheroName()).isEqualTo("SuperHeroName2");
        assertThat(foundSuperHeroes.get(2).getId()).isEqualTo(superHero3.getId ());
        assertThat(foundSuperHeroes.get(2).getFirstName()).isEqualTo("FirstName3");
        assertThat(foundSuperHeroes.get(2).getLastName()).isEqualTo("LastName3");
        assertThat(foundSuperHeroes.get(2).getSuperheroName()).isEqualTo("SuperHeroName3");
    }


    @After
    public void cleanUp() {
        superHeroRepository.deleteAll();
    }}