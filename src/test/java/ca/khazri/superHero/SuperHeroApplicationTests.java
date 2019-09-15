package ca.khazri.superHero;

import ca.khazri.superHero.controller.MissionController;
import ca.khazri.superHero.controller.SuperHeroController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperHeroApplicationTests {
	@Autowired
	private MissionController missionController;
	@Autowired
	private SuperHeroController superHeroController;
	@Test
	public void contextLoads() {
		assertThat(missionController).isNotNull();
		assertThat(superHeroController).isNotNull();

	}

}
