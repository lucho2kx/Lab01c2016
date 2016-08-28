package ar.edu.utn.frsf.dmg.teamflash.lab01c2016.ui;

import ar.edu.utn.frsf.dmg.teamflash.lab01c2016.model.DatosUsuario;

/**
 * Created by AdminUser on 26/08/2016.
 */
public interface IMainPresenter {

    void onClickPlazoFijo(String mail, String cuit, String importe, Integer cantDiasSel, boolean renovarPF);

    void rendimientoUpDate(String mail, String cuit, String importe, Integer cantDiasSel);

    void upDateCantSeleccionado(int cantSel);

}
