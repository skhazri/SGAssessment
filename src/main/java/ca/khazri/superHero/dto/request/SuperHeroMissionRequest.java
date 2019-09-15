package ca.khazri.superHero.dto.request;

import java.io.Serializable;

public class SuperHeroMissionRequest  implements Serializable {

    private Long superHeroId;
    private Long missionId;

    public SuperHeroMissionRequest() {
        super();
    }

    public SuperHeroMissionRequest(Long superHeroId, Long missionId) {
        super();
        this.superHeroId = superHeroId;
        this.missionId = missionId;
    }

    public Long getSuperHeroId() {
        return superHeroId;
    }
    public void setSuperHeroId(Long superHeroId) {
        this.superHeroId = superHeroId;
    }
    public Long getMissionId() {
        return missionId;
    }
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }
}
