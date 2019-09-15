package ca.khazri.superHero.service;

import ca.khazri.superHero.dto.request.MissionRequest;
import ca.khazri.superHero.model.Mission;
import ca.khazri.superHero.repository.MissionRepository;
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

public class MissionServiceTest {


    @MockBean
    private MissionRepository missionRepository;

    @Mock
    private MissionService missionService;

@Before
public void setup(){
    // With this call to initMocks we tell Mockito to process the annotations
    MockitoAnnotations.initMocks(this);
}


    @Test
    public void findAllMissions ( ) {

         // when
        List<Mission> missions = new ArrayList<Mission> ();
        missions.add(new Mission (1L, "mission1", false, false ));
        missions.add(new Mission (2L,"mission2", false, false ));
        missions.add(new Mission (3L,"mission3", false, true ));
        when(missionService.findAllMissions()).thenReturn(missions);

        // assert
        List<Mission> foundMissions = missionService.findAllMissions();
        // assert
        assertEquals(foundMissions.size(), 3);
        assertThat(foundMissions.get(0).getId()).isEqualTo(1);
        assertThat(foundMissions.get(0).getMissionName()).isEqualTo("mission1");
        assertThat(foundMissions.get(0).getIsCompleted ()).isEqualTo(false);
        assertThat(foundMissions.get(0).getIsDeleted ()).isEqualTo(false);

        assertThat(foundMissions.get(1).getId()).isEqualTo(2);
        assertThat(foundMissions.get(1).getMissionName()).isEqualTo("mission2");
        assertThat(foundMissions.get(1).getIsCompleted ()).isEqualTo(false);
        assertThat(foundMissions.get(1).getIsDeleted ()).isEqualTo(false);

        assertThat(foundMissions.get(2).getId()).isEqualTo(3);
        assertThat(foundMissions.get(2).getMissionName()).isEqualTo("mission3");
        assertThat(foundMissions.get(2).getIsCompleted ()).isEqualTo(false);
        assertThat(foundMissions.get(2).getIsDeleted ()).isEqualTo(true);

    }

    @Test
    public void findMissionById ( ) {
        // given
        Mission mission = new Mission (1L, "mission", false, false );

        // when
        when(missionService.findMissionById(mission.getId ())).thenReturn(mission);

        // assert
        Mission foundMission = missionService.findMissionById(1L);
        assertThat(foundMission.getId()).isEqualTo(1);
        assertThat(foundMission.getMissionName()).isEqualTo("mission");
        assertThat(foundMission.getIsCompleted ()).isEqualTo(false);
        assertThat(foundMission.getIsDeleted ()).isEqualTo(false);
    }



    @Test
    public void createMission ( ) {

        // given
        MissionRequest missionRequest = new MissionRequest("mission",false, false);
        Mission mission = new Mission(1L, "mission",false, false);

        // when
        when(missionService.createMission(missionRequest)).thenReturn(mission);

        // then
        Mission createdMission = missionService.createMission(missionRequest);
        // assert

        assertThat(createdMission.getId()).isEqualTo(1);
        assertThat(createdMission.getMissionName()).isEqualTo("mission");
        assertThat(createdMission.getIsCompleted ()).isEqualTo(false);
        assertThat(createdMission.getIsDeleted ()).isEqualTo(false);
    }

    @Test
    public void updateMission ( ) {

        // given
        MissionRequest missionRequest = new MissionRequest("mission ",false, false);
        Mission mission = new Mission(1L, "missionUpdated",true, true);

        // when
        when(missionService.updateMission (missionRequest,1L)).thenReturn(mission);

        // assert
        Mission updatedMission = missionService.updateMission (missionRequest,1L);
        assertThat(updatedMission.getId()).isEqualTo(1);
        assertThat(updatedMission.getMissionName()).isEqualTo("missionUpdated");
        assertThat(updatedMission.getIsCompleted ()).isEqualTo(true);
        assertThat(updatedMission.getIsDeleted ()).isEqualTo(true);
    }

    @Test
    public void softDeleteMission ( ) {
        // given
        Mission mission = new Mission(1L, "mission",false, false);
        Mission softDeletedMission = new Mission(1L, "mission",false, true);

        // when
        when(missionService.softDeleteMission(mission.getId())).thenReturn(softDeletedMission);

        // assert
        Mission deleteMission = missionService.softDeleteMission(mission.getId());
        assertThat(deleteMission.getId()).isEqualTo(1);
        assertThat(deleteMission.getMissionName()).isEqualTo("mission");
        assertThat(deleteMission.getIsCompleted ()).isEqualTo(false);
        assertThat(deleteMission.getIsDeleted ()).isEqualTo(true);
    }
}

