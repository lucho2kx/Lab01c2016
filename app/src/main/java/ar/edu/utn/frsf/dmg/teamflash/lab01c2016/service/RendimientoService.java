package ar.edu.utn.frsf.dmg.teamflash.lab01c2016.service;

import ar.edu.utn.frsf.dmg.teamflash.lab01c2016.model.DatosUsuario;

/**
 * Created by AdminUser on 26/08/2016.
 */
public interface RendimientoService {

    interface RendimientoCallBack {

        void onError(String msg);

        void onSuccess(Double montoRend);
    }

    Double rendimientoInversion(DatosUsuario datos);

    void realizarInversionPlazoFijo(DatosUsuario datosUsuario, RendimientoCallBack callBack);

}
