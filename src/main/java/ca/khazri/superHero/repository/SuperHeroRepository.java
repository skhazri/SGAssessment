package ca.khazri.superHero.repository;

import ca.khazri.superHero.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional

public interface SuperHeroRepository  extends JpaRepository<SuperHero, Long> {
}
