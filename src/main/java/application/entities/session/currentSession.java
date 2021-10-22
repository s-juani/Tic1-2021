package application.entities.session;

import application.entities.ent.TuristaEntity;

public class currentSession {

    private TuristaEntity activeUser;

    public currentSession(TuristaEntity activeUser) {
        this.activeUser = activeUser;
    }

    public TuristaEntity getActiveUser() {
        return activeUser;
    }


}
