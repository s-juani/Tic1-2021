package application.entities.session;

import application.entities.ent.AdministradorEntity;
import application.entities.ent.OperadorEntity;
import application.entities.ent.TuristaEntity;

public class currentSession {

    private TuristaEntity turist;
    private OperadorEntity operador;
    private AdministradorEntity admin;

    public currentSession(TuristaEntity turist) {
        this.turist = turist;
        this.operador = null;
        this.admin = null;

    }

    public currentSession(OperadorEntity operador){
        this.operador = operador;
        this.turist = null;
        this.admin = null;
    }

    public currentSession(AdministradorEntity admin){
        this.admin = admin;
        this.turist = null;
        this.operador = null;
    }

    public <T> T getActiveUser() {
        if (turist != null){
            return  (T) turist;
        } else if (operador != null) {
            return (T) operador;
        } else {
            return (T) admin;
        }
    }


}
