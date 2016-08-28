package ar.edu.utn.frsf.dmg.teamflash.lab01c2016.ui;

import android.content.Context;

/**
 * Created by AdminUser on 26/08/2016.
 */
public interface IMainView {

    Context getContext();

    void setMsgResultadoError(String msg);

    void setMailError();

    void setCuitError();

    void setImporteError();

    void setDiasSeleccionado(int dias);

    void setRendimiento(Double rendimiento);

    void setMsgResultado(Double montoRend);

}
