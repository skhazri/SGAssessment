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
import ca.khazri.superHero.model.Mission;
import ca.khazri.superHero.repository.SuperHeroRepository;
import ca.khazri.superHero.service.MissionService;
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

public class MissionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MissionService missionService;

    @InjectMocks
    private MissionController missionController;
//    @Mock
//    private SuperHeroRepository superHeroRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(missionController).build();
    }


    @Test
    public void getAllMissions() throws Exception {
        // given
        List<Mission> missions = Arrays.asList(new Mission(1L, "mission1", false, false),
                new Mission(2L, "mission2", true, false));

        // when
        when(missionService.findAllMissions()).thenReturn(missions);
        mockMvc.perform(get("/api/missions")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].missionName", is("mission1")))
                .andExpect(jsonPath("$[0].isCompleted", is(false)))
                .andExpect(jsonPath("$[0].isDeleted", is(false)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].missionName", is("mission2")))
                .andExpect(jsonPath("$[1].isCompleted", is(true)))
                .andExpect(jsonPath("$[1].isDeleted", is(false)));
        verify(missionService, times(1)).findAllMissions();
        verifyNoMoreInteractions(missionService);
    }

    @Test
    public void getMissionById() throws Exception {
        Mission mission = new Mission("mission", true, false);
        when(missionService.findMissionById(1L)).thenReturn(mission);
        mockMvc.perform(get("/api/missions/{missionId}", 1)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.missionName", is("mission")))
                .andExpect(jsonPath("$.isCompleted", is(true)))
                .andExpect(jsonPath("$.isDeleted", is(false)));
        verify(missionService, times(1)).findMissionById(1L);
        verifyNoMoreInteractions(missionService);
    }

    @Test
    public void createMission() throws Exception {
        MissionRequest missionRequest = new MissionRequest("mission", true, false);
        Mission mission = new Mission(1L, "mission", true, false);
        when(missionService.createMission(missionRequest)).thenReturn(mission);
        mockMvc.perform(
                post("/api/missions").contentType(MediaType.APPLICATION_JSON).content(Utils.asJsonString(mission)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMission() throws Exception {
        MissionRequest missionRequest = new MissionRequest("mission", true, false);
        Mission mission = new Mission(1L, "updatedMission", true, false);
        when(missionService.updateMission ( missionRequest, mission.getId() ) ).thenReturn(mission);
        mockMvc.perform(put("/api/missions/{missionId}", mission.getId()).contentType(MediaType.APPLICATION_JSON)
                .content( Utils.asJsonString(mission))).andExpect(status().isOk());
    }

    @Test
    public void deleteMission() throws Exception {
        Mission mission = new Mission(1L, "mission", false, false);
        Mission softDeletedMission = new Mission(1L, "mission", false, true);
        when(missionService.softDeleteMission(1L)).thenReturn(softDeletedMission);
        mockMvc.perform(delete("/api/missions/{missionId}", mission.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.isCompleted", is(false)))
                .andExpect(jsonPath("$.isDeleted", is(true)));
        verify(missionService, times(1)).softDeleteMission(mission.getId());
        verifyNoMoreInteractions(missionService);
    }

}
