package ar.edu.utn.frsf.dmg.teamflash.lab01c2016.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import ar.edu.utn.frsf.dmg.teamflash.lab01c2016.R;
import ar.edu.utn.frsf.dmg.teamflash.lab01c2016.model.DatosUsuario;
import ar.edu.utn.frsf.dmg.teamflash.lab01c2016.service.Impl.RendimientoServiceImpl;
import ar.edu.utn.frsf.dmg.teamflash.lab01c2016.service.RendimientoService;

/**
 * Created by AdminUser on 26/08/2016.
 */
public class MainPresenter implements IMainPresenter {
    private IMainView view;
    private RendimientoService rendimientoService;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.rendimientoService= new RendimientoServiceImpl();
    }

    @Override
    public void rendimientoUpDate(String mail, String cuit, String importe, Integer cantDiasSel) {
        if ( this.validateEmail(mail)
                && this.validateCUIT(cuit)
                && this.validateImporte(importe) ) {
            DatosUsuario datos= this.setDatosUsuario(mail, cuit, importe, cantDiasSel);

            view.setRendimiento(rendimientoService.rendimientoInversion(datos));
        }
    }

    @Override
    public void onClickPlazoFijo(String mail, String cuit, String importe, Integer cantDiasSel, boolean renovarPF) {
        if ( this.validateEmail(mail)
                && this.validateCUIT(cuit)
                && this.validateImporte(importe) ) {

            final DatosUsuario datos= this.setDatosUsuario(mail, cuit, importe, cantDiasSel);
            datos.setRenovarPlazoFijo(renovarPF);
            rendimientoService.realizarInversionPlazoFijo(datos, new RendimientoService.RendimientoCallBack() {
                @Override
                public void onError(String msg) {
                    view.setMsgResultadoError(msg);
                }

                @Override
                public void onSuccess(Double montoRend) {
                    view.setMsgResultado(montoRend);
                }
            });
        }

    }

    @Override
    public void upDateCantSeleccionado(int cantSel) {
        view.setDiasSeleccionado(cantSel);
    }

    private DatosUsuario setDatosUsuario(String mail, String cuit, String importe, Integer cantDiasSel) {
        Double impor= new Double(importe);
        Context context= view.getContext();
        DatosUsuario datos= new DatosUsuario();
        Double tasaI=0.0;

        datos.setMail(mail);
        datos.setCuit(new Long(cuit));
        datos.setMonto(impor);
        datos.setCantDias(cantDiasSel);
        if (cantDiasSel < 30) {
            if (impor <= 5000) {
                tasaI= new Double(context.getString(R.string.De_0_5000_menor_30_dias));
            }
            else {
                if (impor <= 99999) {
                    tasaI= new Double(context.getString(R.string.De_5000_99_999_menor_30_dias));
                }
                else {
                    tasaI= new Double(context.getString(R.string.Mas_99_999_menor_30_dias));
                }
            }
        }
        else {
            if (impor <= 5000) {
                tasaI= new Double(context.getString(R.string.De_0_5000_mayor_igual_30_dias));
            }
            else {
                if (impor <= 99999) {
                    tasaI= new Double(context.getString(R.string.De_5000_99_999_mayor_igual_30_dias));
                }
                else {
                    tasaI= new Double(context.getString(R.string.Mas_99_999_mayor_igual_30_dias));
                }
            }
        }

        datos.setTasaInteres(tasaI);

        return datos;
    }

    private boolean validateEmail(String email) {
        // Aquí se válida el formto del mail
        if (email.isEmpty() || !isValidEmail(email)) {
            view.setMailError();
            return false;
        }
        else
            return true;
    }

    private boolean validateCUIT(String cuit) {
        // Aquí deberíamos validar el formato del cuit
        // sólo válido que este compuesto por 11 dígitos

        if (!(cuit.length() == 11)) {
            view.setCuitError();
            return false;
        }

        return true;
    }

    private boolean validateImporte(String importe) {
        // Aquí deberíamos validar el intervalo del importe
        // pero sólo válido de que no este vacío
        if (importe.length() == 0) {
            view.setImporteError();
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
