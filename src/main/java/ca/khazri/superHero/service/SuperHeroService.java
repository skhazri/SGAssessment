package ca.khazri.superHero.service;
//
import ca.khazri.superHero.dto.request.SuperHeroMissionRequest;
import ca.khazri.superHero.dto.request.SuperHeroRequest;
import ca.khazri.superHero.dto.response.ApiResponse;
import ca.khazri.superHero.dto.response.ErrorResponse;
import ca.khazri.superHero.dto.response.SuccessResponse;
import ca.khazri.superHero.exception.ResourceNotFoundException;
import ca.khazri.superHero.model.Mission;
import ca.khazri.superHero.model.SuperHero;
import ca.khazri.superHero.repository.MissionRepository;
import ca.khazri.superHero.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SuperHeroService {
    private SuperHeroRepository superHeroRepository;
    private MissionRepository missionRepository;
    private MissionService missionService;

    @Autowired
    public SuperHeroService (final SuperHeroRepository superHeroRepository, final MissionRepository missionRepository, MissionService missionService) {
        this.superHeroRepository = superHeroRepository;
        this.missionRepository = missionRepository;
        this.missionService = missionService;
    }

    public List<SuperHero> findAllSuperHeroes() {
        return superHeroRepository.findAll ();
    }

    public SuperHero findSuperHeroById(Long superHeroId ) {
        return superHeroRepository.findById (superHeroId )
                .orElseThrow ( () -> new ResourceNotFoundException ("SuperHero","id",superHeroId) );
    }

    public SuperHero createSuperHero( SuperHeroRequest superHeroRequest) {

        SuperHero superHero = new SuperHero (  );
        superHero.setFirstName ( superHeroRequest.getFirstName ());
        superHero.setLastName ( superHeroRequest.getLastName () );
        superHero.setSuperheroName (superHeroRequest.getSuperheroName ());
        return superHeroRepository.save ( superHero );

    }

    public SuperHero updateSuperHero(SuperHeroRequest superHeroRequest, Long superHeroId) {
        return superHeroRepository.findById ( superHeroId ).map ( superHero -> {
            superHero.setFirstName ( superHeroRequest.getFirstName () );
            superHero.setLastName ( superHeroRequest.getLastName () );
            superHero.setSuperheroName ( superHeroRequest.getSuperheroName () );
            return superHeroRepository.save ( superHero );

        } ).orElseThrow(() -> new ResourceNotFoundException("SuperHero", "id", superHeroId));
    }

    public ApiResponse deleteSuperHero(Long superHeroId) {
        return superHeroRepository.findById(superHeroId).map(superHero -> {
            superHeroRepository.delete(superHero);
            return new SuccessResponse ( "Super Hero is deleted" );

        }).orElseThrow(() -> new ResourceNotFoundException("SuperHero", "id", superHeroId));
    }



    public ApiResponse addSuperHeroMission(SuperHeroMissionRequest superHeroMissionRequest) {

        Mission mission =  missionService.findMissionById ( superHeroMissionRequest.getMissionId () );
        SuperHero superHero = this.findSuperHeroById ( superHeroMissionRequest.getSuperHeroId () );
        superHero.addMission ( mission );
        superHeroRepository.save ( superHero );
        return  new SuccessResponse("Mission added to Superhero");
    }



    public ApiResponse  removeSuperHeroMission(Long superHeroId, Long missionId) {
        Mission mission = missionService.findMissionById ( missionId );
        SuperHero superHero = this.findSuperHeroById ( superHeroId );

        if(mission.getIsCompleted ()) {
            return  new ErrorResponse ("Unable to remove a completed mission");
        }
         superHero.removeMission ( mission );
         superHeroRepository.save ( superHero );
        return  new SuccessResponse("Mission removed from Superhero");

    }
}
