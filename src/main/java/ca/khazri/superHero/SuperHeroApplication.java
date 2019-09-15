package ca.khazri.superHero;
//
import ca.khazri.superHero.model.Mission;
import ca.khazri.superHero.model.SuperHero;
import ca.khazri.superHero.repository.MissionRepository;
import ca.khazri.superHero.repository.SuperHeroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@Slf4j

public class SuperHeroApplication implements CommandLineRunner
{
	@Autowired
	private SuperHeroRepository superHeroRepository;

	@Autowired
	private MissionRepository missionRepository;

	public static void main(String[] args) {
		SpringApplication.run(SuperHeroApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		// Create a SuperHero
		SuperHero superHero = new SuperHero ( "Sarra", "khazri", "Superwoman" );

		// Create two missions
		Mission mission1 = new Mission ( "save the world", false, false );
		Mission mission2 = new Mission ( "save the world2", false, true );

		superHero.getMissions().add ( mission1 );
		superHero.getMissions().add( mission2 );

		mission1.getHeroes().add(superHero );
		mission1.getHeroes().add(superHero );

		superHeroRepository.save ( superHero );

	}
}
