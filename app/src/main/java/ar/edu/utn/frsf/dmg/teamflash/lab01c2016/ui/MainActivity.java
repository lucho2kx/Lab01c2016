package ar.edu.utn.frsf.dmg.teamflash.lab01c2016.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import ar.edu.utn.frsf.dmg.teamflash.lab01c2016.R;

public class MainActivity extends AppCompatActivity implements IMainView {

    private static final String TAG= "lab01c2016.MainActivity";

    private IMainPresenter presenter;
    private EditText mail;
    private EditText cuit;
    private EditText importe;
    private SeekBar cantDias;
    private TextView diasSeleccionado;
    private TextView montoRendimiento;
    private CheckBox renAutoVenc;
    private Button plazoFijo;
    private TextView msgResultado;
    private RelativeLayout coordinatorLayout;

    private Integer cant= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail= (EditText) findViewById(R.id.mail_editText);
        cuit= (EditText) findViewById(R.id.cuit_editText);
        importe= (EditText) findViewById(R.id.importe_editText);
        cantDias= (SeekBar) findViewById(R.id.cant_dias_seekBar);
        diasSeleccionado= (TextView) findViewById(R.id.dias_selec_textView);
        montoRendimiento= (TextView) findViewById(R.id.monto_rend_textView);
        renAutoVenc= (CheckBox) findViewById(R.id.ren_auto_venc_checkBox);
        plazoFijo= (Button) findViewById(R.id.plazo_fijo_button);
        msgResultado= (TextView) findViewById(R.id.msg_res_textView);
        coordinatorLayout= (RelativeLayout) findViewById(R.id.coordinatorLayout_main);

        plazoFijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.onClickPlazoFijo(mail.getText().toString(),
                        cuit.getText().toString(),
                        importe.getText().toString(),
                        cant,
                        renAutoVenc.isChecked());

            }
        });

        cantDias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i(TAG,"dias seleccionado= "+i);
                presenter.upDateCantSeleccionado(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
                presenter.rendimientoUpDate(mail.getText().toString(),
                        cuit.getText().toString(),
                        importe.getText().toString(),
                        cant);
            }
        });

        presenter= new MainPresenter(this);
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void setMailError() {
        mail.setError(getString(R.string.msg_err_mail));
        this.requestFocus(mail);
    }

    @Override
    public void setCuitError() {
        cuit.setError(getString(R.string.msg_err_cuit));
        this.requestFocus(cuit);
    }

    @Override
    public void setImporteError() {
        importe.setError(getString(R.string.msg_err_importe));
        this.requestFocus(importe);
    }

    @Override
    public void setRendimiento(Double rendimiento) {
        montoRendimiento.setText("$"+String.format("%.3f", rendimiento));
    }

    @Override
    public void setDiasSeleccionado(int dias) {
        cant= new Integer(dias);
        diasSeleccionado.setText(dias+" días");
    }

    @Override
    public void setMsgResultadoError(String msg) {
        msgResultado.setTextColor(getResources().getColor(R.color.msg_error));
        msgResultado.setText(msg.toString());

    }

    @Override
    public void setMsgResultado(Double montoRend) {
        String msg= getContext().getString(R.string.plazo_fijo_realizado)
                + getContext().getString(R.string.recibira)
                + " " + String.format("%.3f", montoRend) + " "
                + getContext().getString(R.string.al_venc);
        msgResultado.setTextColor(getResources().getColor(R.color.msg_correcto));
        msgResultado.setText(msg.toString());
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
