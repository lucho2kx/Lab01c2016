package ar.edu.utn.frsf.dmg.teamflash.lab01c2016.service.Impl;

import android.util.Log;

import ar.edu.utn.frsf.dmg.teamflash.lab01c2016.model.DatosUsuario;
import ar.edu.utn.frsf.dmg.teamflash.lab01c2016.service.RendimientoService;

/**
 * Created by AdminUser on 26/08/2016.
 */
public class RendimientoServiceImpl implements RendimientoService {

    public RendimientoServiceImpl() {
    }

    @Override
    public Double rendimientoInversion(DatosUsuario datos) {
         return calcularInteres(datos);
    }

    @Override
    public void realizarInversionPlazoFijo(DatosUsuario datosUsuario, RendimientoCallBack callBack) {
        try {
            // Aquí debería guardar la inversión en la Capa de acceso a aatos
            // Vamos a dar por hecho que se realizó de manera correcta dicho guardado
            callBack.onSuccess(calcularInteres(datosUsuario));
        }
        catch (Exception e) {
            // Error al guardar la Inversión
            callBack.onError(e.getMessage().toString());
        }
    }

    private Double calcularInteres(DatosUsuario datos) {
        // Cálculo del interés
        Log.i("datos.getMonto()===>",datos.getMonto().toString());
        Log.i("datos.getTasaInteres()=",datos.getTasaInteres().toString());
        Log.i("datos.getCantDias()=>",datos.getCantDias().toString());
        Double n1= 1+((datos.getTasaInteres())/100);
        Log.i("n1=>",""+n1);
        Double exp= datos.getCantDias().doubleValue()/360;
        Log.i("exp=>",""+exp);
        Log.i("Math.pow=>",""+Math.pow(n1,exp));
        Double res= datos.getMonto() * (( Math.pow(n1,exp) - 1));
        Log.i("res=>",""+res);
        return res;
    }
}
