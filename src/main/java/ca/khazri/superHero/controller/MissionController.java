package ca.khazri.superHero.controller;

import ca.khazri.superHero.dto.request.MissionRequest;
import ca.khazri.superHero.dto.response.*;
import ca.khazri.superHero.model.Mission;
import ca.khazri.superHero.service.MissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/missions")

public class MissionController {
    private final MissionService missionService;
    private MissionRequest missionRequest;

    @Autowired
    public MissionController (MissionService missionService) {
        this.missionService = missionService;
    }

    // Get All missions
    @GetMapping
    public ResponseEntity<ApiResponse> getAllMissions ( ) {
        List<Mission> missions = missionService.findAllMissions ( );
        try {
            return new ResponseEntity ( missions, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "Unable to find missions" ), HttpStatus.BAD_REQUEST );

        }
    }

    //  Get  mission
    @GetMapping("/{missionId}")
    public ResponseEntity<ApiResponse> getMissionById (@Valid @PathVariable Long missionId) {
        try {
            Mission mission = ( ( missionService.findMissionById ( missionId ) ) );
            MissionResponse missionResponse = new MissionResponse ( mission.getMissionName ( ), mission.getIsCompleted ( ), mission.getIsDeleted ( ),
                    mission.getHeroes ( ) );
            return new ResponseEntity ( mission, HttpStatus.OK );

        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "Mission Not Found" ), HttpStatus.NOT_FOUND );
        }
    }

    //create mission
    @PostMapping()
    public ResponseEntity<ApiResponse> createMission (@Valid @RequestBody MissionRequest missionRequest) {
        try {
            Mission mission = missionService.createMission ( missionRequest );
            return new ResponseEntity ( mission, HttpStatus.OK );
            //return new ResponseEntity<> ( new SuccessResponse ( "The mission  is added successfully" ), HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "The mission cannot be added" ), HttpStatus.BAD_REQUEST );
        }
    }

    // update mission
    @PutMapping("/{missionId}")
    public ResponseEntity<ApiResponse> updateMission (@Valid @RequestBody MissionRequest missionRequest, @PathVariable Long missionId) {
        try {
            Mission mission = missionService.updateMission ( missionRequest, missionId );
            return new ResponseEntity ( mission, HttpStatus.OK );

            //return new ResponseEntity<> ( new SuccessResponse ( "The mission  is updated successfully" ), HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "The mission does not exist" ), HttpStatus.NOT_FOUND );
        }
    }

    // delete mission
    @DeleteMapping("/{missionId}")
    public ResponseEntity<ApiResponse> deleteMission (@PathVariable Long missionId) {
        try {
            Mission mission = missionService.softDeleteMission ( missionId );
            return new ResponseEntity ( mission, HttpStatus.OK );
            //return new ResponseEntity <>( new SuccessResponse ( "The mission is deleted successfully" ), HttpStatus.OK );

        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "This mission does not exist" ), HttpStatus.NOT_FOUND );
        }
    }

}

