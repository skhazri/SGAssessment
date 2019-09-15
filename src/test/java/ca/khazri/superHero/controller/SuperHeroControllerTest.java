package ca.khazri.superHero.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;

import ca.khazri.superHero.SuperHeroApplication;
import ca.khazri.superHero.dto.request.MissionRequest;
import ca.khazri.superHero.dto.request.SuperHeroMissionRequest;
import ca.khazri.superHero.dto.request.SuperHeroRequest;
import ca.khazri.superHero.dto.response.ApiResponse;
import ca.khazri.superHero.dto.response.SuccessResponse;
import ca.khazri.superHero.model.Mission;
import ca.khazri.superHero.model.SuperHero;
import ca.khazri.superHero.repository.SuperHeroRepository;
import ca.khazri.superHero.service.MissionService;
import ca.khazri.superHero.service.SuperHeroService;
import ca.khazri.superHero.utils.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class SuperHeroControllerTest {
    private MockMvc mockMvc;

    @Mock
    private MissionService missionService;

    @Mock
    private SuperHeroService superHeroService;

//    @InjectMocks
//    private MissionController missionController;

    @InjectMocks
    private SuperHeroController superHeroController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(superHeroController).build();
    }



    @Test
    public void getAllSuperHeroes ( ) throws Exception {
        // given
        List<SuperHero> heroes = Arrays.asList(new SuperHero(1L, "FirstName","LastName", "SuperHeroName"));

        // when
        when(superHeroService.findAllSuperHeroes ()).thenReturn(heroes);
        mockMvc.perform(get("/api/superheroes")).andExpect(status().isOk())
                .andExpect(content().contentType( MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("FirstName")))
                .andExpect(jsonPath("$[0].lastName", is("LastName")))
                .andExpect(jsonPath("$[0].superheroName", is("SuperHeroName")));
        verify(superHeroService, times(1)).findAllSuperHeroes ();
        verifyNoMoreInteractions(superHeroService);
    }

    @Test
    public void getSuperHeroById ( ) throws Exception {

        // given
        SuperHero superHero = new SuperHero(1L, "FirstName","LastName", "SuperHeroName");

        // when
        when(superHeroService.findSuperHeroById (1L)).thenReturn(superHero);
        mockMvc.perform(get("/api/superheroes/{superHeroId}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType( MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.firstName", is("FirstName")))
                .andExpect(jsonPath("$.lastName", is("LastName")))
                .andExpect(jsonPath("$.superheroName", is("SuperHeroName")));
        verify(superHeroService, times(1)).findSuperHeroById (1L);
        verifyNoMoreInteractions(superHeroService);

    }

    @Test
    public void createSuperHero ( ) throws Exception {
        SuperHeroRequest superHeroRequest = new SuperHeroRequest("FirstName","LastName", "SuperHeroName");
        SuperHero superHero = new SuperHero(1L,"FirstName","LastName", "SuperHeroName");
        when(superHeroService.createSuperHero (superHeroRequest)).thenReturn(superHero);
        mockMvc.perform(
                post("/api/superheroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.asJsonString(superHero)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateSuperHero ( ) throws Exception {

        SuperHeroRequest superHeroRequest = new SuperHeroRequest("FirstName","LastName", "SuperHeroName");
        SuperHero superHero = new SuperHero(1L,"FirstName","LastName", "SuperHeroName");
        when(superHeroService.updateSuperHero ( superHeroRequest,superHero.getId())).thenReturn(superHero);
        mockMvc.perform(put("/api/superheroes/{superheroId}", superHero.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.asJsonString(superHero)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSuperHero ( ) throws Exception {

        SuperHero superHero = new SuperHero(1L,"FirstName","LastName", "SuperHeroName");
        ApiResponse apiResponse = new SuccessResponse ("The superHero is deleted successfully");
        when(superHeroService.deleteSuperHero (superHero.getId())).thenReturn(apiResponse);
        mockMvc.perform(delete("/api/superheroes/{superheroId}", superHero.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.messages[0]", is("The superHero is deleted successfully")));
        verify(superHeroService, times(1)).deleteSuperHero (superHero.getId());
        verifyNoMoreInteractions(superHeroService);
    }

    @Test

    public void addSuperHeroMission ( ) throws Exception {

        SuperHero superHero = new SuperHero(1L,"FirstName","LastName", "SuperHeroName");
            Mission mission = new Mission(1L, "mission", false, false);
            SuperHeroMissionRequest superHeroMissionRequest = new SuperHeroMissionRequest(superHero.getId(),
                    mission.getId());
            ApiResponse apiResponse = new SuccessResponse ("Mission added to Superhero");
            when(superHeroService.addSuperHeroMission (superHeroMissionRequest)).thenReturn(apiResponse);
            mockMvc.perform(post("/api/superheroes/addSuperHeroMission")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Utils.asJsonString(superHeroMissionRequest)))
                    .andExpect(status().isOk());
        }



    @Test
    public void removeSuperHeroMission ( ) throws Exception {

        SuperHero superHero = new SuperHero(1L,"FirstName","LastName", "SuperHeroName");
        Mission mission = new Mission(1L, "mission", false, false);
        ApiResponse apiResponse = new SuccessResponse ("The superHero is deleted successfully");

        when(superHeroService.removeSuperHeroMission (superHero.getId(),mission.getId ())).thenReturn(apiResponse);
        mockMvc.perform(delete("/api/superheroes/{superHeroId}/{missionId}", superHero.getId(),mission.getId ()))
                .andExpect(status().isOk());
        verify(superHeroService, times(1)).removeSuperHeroMission (superHero.getId(),mission.getId ());
        verifyNoMoreInteractions(superHeroService);
    }
}