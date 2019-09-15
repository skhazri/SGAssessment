package ca.khazri.superHero.service;

import ca.khazri.superHero.dto.request.MissionRequest;
import ca.khazri.superHero.exception.ResourceNotFoundException;
import ca.khazri.superHero.model.Mission;
import ca.khazri.superHero.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    @Autowired
    public MissionService( MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public List<Mission> findAllMissions() {
        return (List<Mission>) missionRepository.findAll();
    }

    public Mission findMissionById(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new ResourceNotFoundException ("mission", "id", missionId));
    }

    public Mission createMission(MissionRequest missionRequest) {
        Mission mission = new Mission();
        mission.setMissionName(missionRequest.getMissionName());
        mission.setIsCompleted (missionRequest.isCompleted());
        mission.setIsDeleted (missionRequest.isDeleted());
        return missionRepository.save(mission);
    }

    public Mission updateMission( MissionRequest missionRequest,Long missionId) {
        return missionRepository.findById(missionId).map(mission -> {
            mission.setMissionName(missionRequest.getMissionName());
            mission.setIsCompleted (missionRequest.isCompleted());
            mission.setIsDeleted (missionRequest.isDeleted());
            return missionRepository.save(mission);
        }).orElseThrow(() -> new ResourceNotFoundException("Mission", "id", missionId));
    }

    public Mission softDeleteMission(Long missionId) {
        return missionRepository.findById(missionId).map(mission -> {
            mission.setIsDeleted (true);
            return missionRepository.save(mission);
        }).orElseThrow(() -> new ResourceNotFoundException("Mission", "id", missionId));
    }

}