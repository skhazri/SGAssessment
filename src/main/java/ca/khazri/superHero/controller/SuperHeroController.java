package ca.khazri.superHero.controller;

import ca.khazri.superHero.dto.request.MissionRequest;
import ca.khazri.superHero.dto.request.SuperHeroMissionRequest;
import ca.khazri.superHero.dto.request.SuperHeroRequest;
import ca.khazri.superHero.dto.response.*;
import ca.khazri.superHero.model.Mission;
import ca.khazri.superHero.model.SuperHero;
import ca.khazri.superHero.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/superheroes")

public class SuperHeroController {
    private final SuperHeroService superHeroService;

    @Autowired
    public SuperHeroController ( SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse> getAllSuperHeroes ( ) {
        List<SuperHero> heroes = superHeroService.findAllSuperHeroes ( );
        try {
            return new ResponseEntity ( heroes, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "Unable to find heroes" ), HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/{superHeroId}")
    public ResponseEntity<ApiResponse> getSuperHeroById (@Valid @PathVariable Long superHeroId) {
        try {
            SuperHero superHero = superHeroService.findSuperHeroById ( superHeroId );
            SuperHeroResponse superHeroResponse = new SuperHeroResponse (
                    superHero.getFirstName(),
                    superHero.getLastName(),
                    superHero.getSuperheroName(),
                    superHero.getMissions())  ;

            return new ResponseEntity ( superHeroResponse, HttpStatus.OK );

        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "Super Hero Not Found" ), HttpStatus.NOT_FOUND );
        }
    }


    @PostMapping
    public ResponseEntity<ApiResponse> createSuperHero (@Valid @RequestBody SuperHeroRequest superHeroRequest) {
        try {
            SuperHero superHero = superHeroService.createSuperHero ( superHeroRequest );
            return new ResponseEntity ( superHero, HttpStatus.OK );
            //return new ResponseEntity<> ( new SuccessResponse ( "The mission  is added successfully" ), HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "The superHero cannot be added" ), HttpStatus.BAD_REQUEST );
        }
    }

    @PutMapping("/{superHeroId}")
    public ResponseEntity<ApiResponse> updateSuperHero(@RequestBody  @Valid  SuperHeroRequest superHeroRequest, @PathVariable Long superHeroId) {
        try {
            SuperHero superHero = superHeroService.updateSuperHero ( superHeroRequest,superHeroId );
            return new ResponseEntity ( superHero, HttpStatus.OK );

            //return new ResponseEntity<> ( new SuccessResponse ( "The mission  is updated successfully" ), HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "The superHero does not exist" ), HttpStatus.NOT_FOUND );
        }
    }

    @DeleteMapping("/{superHeroId}")
    public ResponseEntity<ApiResponse> deleteSuperHero(@PathVariable Long superHeroId) {
        try {
           superHeroService.deleteSuperHero ( superHeroId );
            return new ResponseEntity <>( new SuccessResponse ( "The superHero is deleted successfully" ), HttpStatus.OK );

        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "This superHero does not exist" ), HttpStatus.NOT_FOUND );
        }
    }
    @PostMapping("/addSuperHeroMission")
    public ResponseEntity<ApiResponse> addSuperHeroMission(@Valid @RequestBody SuperHeroMissionRequest superHeroMissionRequest) {
        try {
             superHeroService.addSuperHeroMission (superHeroMissionRequest);
            return new ResponseEntity <>( new SuccessResponse ( "The Mission is added to Superhero successfully" ), HttpStatus.OK );

        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "Unable to add the mission to the Super Hero" ), HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("/{superHeroId}/{missionId}")
    public ResponseEntity<ApiResponse> removeSuperHeroMission(@PathVariable Long superHeroId, @PathVariable Long missionId) {
        try {
            ApiResponse apiResponse = superHeroService.removeSuperHeroMission ( superHeroId, missionId );
            return new ResponseEntity (apiResponse.getMessages () , HttpStatus.OK );

        } catch (Exception e) {
            return new ResponseEntity<> ( new ErrorResponse ( "Unable to remove SuperHero mission " ), HttpStatus.BAD_REQUEST );
        }
    }
}
