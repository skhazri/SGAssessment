package ca.khazri.superHero.service;

import ca.khazri.superHero.dto.request.MissionRequest;
import ca.khazri.superHero.dto.request.SuperHeroMissionRequest;
import ca.khazri.superHero.dto.request.SuperHeroRequest;
import ca.khazri.superHero.dto.response.ApiResponse;
import ca.khazri.superHero.dto.response.ErrorResponse;
import ca.khazri.superHero.dto.response.SuccessResponse;
import ca.khazri.superHero.model.Mission;
import ca.khazri.superHero.model.SuperHero;
import ca.khazri.superHero.repository.MissionRepository;
import ca.khazri.superHero.repository.SuperHeroRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SuperHeroServiceTest {

    @MockBean
    private MissionRepository missionRepository;

    @MockBean
    private SuperHeroRepository superHeroRepository;

    @Mock
    private MissionService missionService;

    @Mock
    private SuperHeroService superHeroService;

    @Before
    public void setup(){
        // With this call to initMocks we tell Mockito to process the annotations
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllSuperHeroes ( ) {

        // given
        SuperHero superHero1 = new SuperHero (1L,"FirstName1","LastName1","SuperHeroName1");
        SuperHero superHero2 = new SuperHero (2L,"FirstName2","LastName2","SuperHeroName2");
        SuperHero superHero3 = new SuperHero (3L,"FirstName3","LastName3","SuperHeroName3");


        List heroes = new ArrayList<Mission> (  );
        heroes.add(superHero1);
        heroes.add(superHero2);
        heroes.add(superHero3);

        // when
        when(superHeroService.findAllSuperHeroes ()).thenReturn(heroes);

        // assert
        List<SuperHero> foundSuperHeroes = superHeroService.findAllSuperHeroes ();
        // assert
        assertEquals(foundSuperHeroes.size (),3);
        assertThat(foundSuperHeroes.get(0).getId()).isEqualTo(1);
        assertThat(foundSuperHeroes.get(0).getFirstName()).isEqualTo("FirstName1");
        assertThat(foundSuperHeroes.get(0).getLastName()).isEqualTo("LastName1");
        assertThat(foundSuperHeroes.get(0).getSuperheroName()).isEqualTo("SuperHeroName1");
        assertThat(foundSuperHeroes.get(1).getId()).isEqualTo(2);
        assertThat(foundSuperHeroes.get(1).getFirstName()).isEqualTo("FirstName2");
        assertThat(foundSuperHeroes.get(1).getLastName()).isEqualTo("LastName2");
        assertThat(foundSuperHeroes.get(1).getSuperheroName()).isEqualTo("SuperHeroName2");
        assertThat(foundSuperHeroes.get(2).getId()).isEqualTo(3);
        assertThat(foundSuperHeroes.get(2).getFirstName()).isEqualTo("FirstName3");
        assertThat(foundSuperHeroes.get(2).getLastName()).isEqualTo("LastName3");
        assertThat(foundSuperHeroes.get(2).getSuperheroName()).isEqualTo("SuperHeroName3");
    }

    @Test
    public void findSuperHeroById ( ) {

        // given
        SuperHero superHero = new SuperHero (1L,"FirstName","LastName","SuperHeroName");

        // when
        when(superHeroService.findSuperHeroById (superHero.getId ())).thenReturn(superHero);


        // then
        SuperHero foundHero = superHeroService.findSuperHeroById(superHero.getId ());

        // assert
        assertThat(foundHero.getId()).isEqualTo(1);
        assertThat(foundHero.getFirstName ()).isEqualTo("FirstName");
        assertThat(foundHero.getLastName ()).isEqualTo("LastName");
        assertThat(foundHero.getSuperheroName ()).isEqualTo("SuperHeroName");
    }

    @Test
    public void createSuperHero ( ) {

        // given
        SuperHeroRequest superheroRequest = new SuperHeroRequest("FirstName","LastName", "SuperHeroName");
        SuperHero superHero = new SuperHero(1L, "FirstName","LastName", "SuperHeroName");

        // when
        when(superHeroService.createSuperHero (superheroRequest)).thenReturn(superHero);

        // then
        SuperHero createdSuperHero = superHeroService.createSuperHero(superheroRequest);
        // assert

        assertThat(createdSuperHero.getId()).isEqualTo(1);
        assertThat(createdSuperHero.getFirstName()).isEqualTo("FirstName");
        assertThat(createdSuperHero.getLastName()).isEqualTo("LastName");
        assertThat(createdSuperHero.getSuperheroName()).isEqualTo("SuperHeroName");
    }

    @Test
    public void updateSuperHero ( ) {

        // given
        SuperHeroRequest superheroRequest = new SuperHeroRequest("FirstName","LastName", "SuperHeroName");
        SuperHero superHero = new SuperHero(1L, "updatedFirstName","updatedLastName", "updatedSuperHeroName");

        // when
        when(superHeroService.updateSuperHero (superheroRequest,1L)).thenReturn(superHero);

        // then
        SuperHero updateSuperHero = superHeroService.updateSuperHero(superheroRequest,1L);
        //assert
        assertThat(updateSuperHero.getId()).isEqualTo(1);
        assertThat(updateSuperHero.getFirstName()).isEqualTo("updatedFirstName");
        assertThat(updateSuperHero.getLastName()).isEqualTo("updatedLastName");
        assertThat(updateSuperHero.getSuperheroName()).isEqualTo("updatedSuperHeroName");
    }

    @Test
    public void deleteSuperHero ( ) {

        // given
        SuperHero superHero = new SuperHero(1L, "FirstName","LastName", "SuperHeroName");
        SuccessResponse successResponse = new SuccessResponse ("Super Hero is deleted");
        // when
        when(superHeroService.deleteSuperHero (superHero.getId())).thenReturn(successResponse);

        // then
        ApiResponse apiResponse = superHeroService.deleteSuperHero(superHero.getId());
        //assert
        assertThat(apiResponse.getSuccess()).isEqualTo(true);
        assertThat(apiResponse.getMessages ().get ( 0 )).isEqualTo("Super Hero is deleted");
    }

    @Test
    public void addSuperHeroMission ( ) {
        // given
        SuperHero superHero = new SuperHero(1L, "FirstName","LastName", "SuperHeroName");
        Mission mission = new Mission(1L, " mission",false, false);
        SuperHeroMissionRequest superheroMissionRequest = new SuperHeroMissionRequest(superHero.getId(), mission.getId());
        SuccessResponse successResponse = new SuccessResponse( "Mission added to Superhero");

      //   when
        when(superHeroService.addSuperHeroMission (superheroMissionRequest)).thenReturn(successResponse);
        //then
        ApiResponse apiResponse = superHeroService.addSuperHeroMission (superheroMissionRequest);

        // assert
        assertThat(apiResponse.getSuccess()).isEqualTo(true);
        assertThat(apiResponse.getMessages().get ( 0 )).isEqualTo("Mission added to Superhero");
    }

    @Test
    public void removeSuperHeroMission ( ) {

        //Success case
        // given
        SuperHero superHero = new SuperHero(1L, "FirstName","LastName", "SuperHeroName");
        Mission mission = new Mission(1L, " mission",false, false);
        SuccessResponse successResponse = new SuccessResponse( "Mission removed from Superhero");

        // when
        when(superHeroService.removeSuperHeroMission (superHero.getId (),mission.getId ())).thenReturn(successResponse);

        // then
        ApiResponse apiResponse = superHeroService.removeSuperHeroMission (superHero.getId (),mission.getId ());
        //assert
        assertThat(apiResponse.getSuccess()).isEqualTo(true);
        assertThat(apiResponse.getMessages().get ( 0 )).isEqualTo("Mission removed from Superhero");

        //Failure CASE
        // given
        Mission completedMission = new Mission(1L, " mission",true, false);
        ApiResponse errorResponse = new ErrorResponse ( "Unable to remove a completed mission");

        // when
        when(superHeroService.removeSuperHeroMission (superHero.getId (),mission.getId ())).thenReturn(errorResponse);
        // then
        ApiResponse FailedApiResponse = superHeroService.removeSuperHeroMission (superHero.getId (),mission.getId ());
        assertThat(FailedApiResponse.getSuccess()).isEqualTo(false);
        assertThat(FailedApiResponse.getMessages().get ( 0 )).isEqualTo("Unable to remove a completed mission");
    }
}