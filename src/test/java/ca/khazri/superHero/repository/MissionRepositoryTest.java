package ca.khazri.superHero.repository;

import ca.khazri.superHero.model.Mission;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
@Rollback(false)
@Transactional


public class MissionRepositoryTest {

    private final Long missionId = 1L;
    @Autowired
    private MissionRepository missionRepository;

    @Test
    public void givenEmptyDBWhenFindOneByIdThenReturnEmptyOptional() {
        Optional<Mission> foundMission = missionRepository.findById (missionId);

        assertThat(foundMission.isPresent()).isEqualTo(false);
    }
    @Test
    public void givenMissionInDBWhenFindOneByIdThenReturnOptionalWitMission() {
        // given
        Mission mission = new Mission ("mission",true,true);
        missionRepository.save(mission);

        // when
        Optional<Mission> foundMission = missionRepository.findById (mission.getId ());

        // then
        assertThat(foundMission.isPresent ()).isEqualTo ( true );
        assertThat(foundMission
                .get()
                .getId ()).isEqualTo(mission.getId ());
        assertThat(foundMission
                .get()
                .getMissionName ()).isEqualTo("mission");
        assertThat(foundMission
                .get()
                .getIsCompleted ()).isEqualTo(true);
        assertThat(foundMission
                .get()
                .getIsDeleted ()).isEqualTo(true);



    }

    @Test
    public void givenMissionListInDBWhenFindAllThenReturnListWitMission() {
        // given
        Mission mission1 = new Mission ("mission1",true,true);
        Mission mission2 = new Mission ("mission2",true,true);
        Mission mission3 = new Mission ("mission3",true,false);
        List missions = new ArrayList<Mission> (  );
        missions.add(mission1);
        missions.add(mission2);
        missions.add(mission3);

        missionRepository.saveAll ( missions );

        // when
        List<Mission> foundMissions = missionRepository.findAll ( );

        // then
        assertThat(foundMissions.size ()).isEqualTo (3);

        assertThat(foundMissions.get(0).getId()).isEqualTo(mission1.getId ());
        assertThat(foundMissions.get(0).getMissionName()).isEqualTo("mission1");
        assertThat(foundMissions.get(0).getIsCompleted ()).isEqualTo(true);
        assertThat(foundMissions.get(0).getIsDeleted ()).isEqualTo(true);
        assertThat(foundMissions.get(1).getId()).isEqualTo(mission2.getId ());
        assertThat(foundMissions.get(1).getMissionName()).isEqualTo("mission2");
        assertThat(foundMissions.get(1).getIsCompleted ()).isEqualTo(true);
        assertThat(foundMissions.get(1).getIsDeleted ()).isEqualTo(true);
        assertThat(foundMissions.get(2).getId()).isEqualTo(mission3.getId ());
        assertThat(foundMissions.get(2).getMissionName()).isEqualTo("mission3");
        assertThat(foundMissions.get(2).getIsCompleted ()).isEqualTo(true);
        assertThat(foundMissions.get(2).getIsDeleted ()).isEqualTo(false);
    }


    @After
    public void cleanUp() {
        missionRepository.deleteAll();
    }}