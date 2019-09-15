package ca.khazri.superHero.dto.request;

import java.io.Serializable;

public class MissionRequest implements Serializable {

    private String missionName;
    private boolean isCompleted;
    private boolean isDeleted;

    public MissionRequest() {
        super();
    }

    public MissionRequest(String missionName, boolean isCompleted, boolean isDeleted) {
        super();
        this.missionName = missionName;
        this.isCompleted = isCompleted;
        this.isDeleted = isDeleted;
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
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
