package ca.khazri.superHero.dto.response;

import java.util.HashSet;
import java.util.Set;

import ca.khazri.superHero.model.SuperHero;

public class MissionResponse  extends SuccessResponse {
	
	private String missionName;

    private boolean isCompleted;

    private boolean isDeleted;

    private Set<SuperHero> superHeroes = new HashSet<>();

    public MissionResponse(String message) {
		super(message);
	}

	public MissionResponse(String missionName, boolean isCompleted, boolean isDeleted, Set<SuperHero> superHeroes) {
		super();
		this.missionName = missionName;
        this.isCompleted = isCompleted;
        this.isDeleted = isDeleted;
        this.superHeroes = superHeroes;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<SuperHero> getSuperHeroes() {
        return superHeroes;
    }

    public void setSuperHeroes(Set<SuperHero> superHeroes) {
        this.superHeroes = superHeroes;
    }

}
