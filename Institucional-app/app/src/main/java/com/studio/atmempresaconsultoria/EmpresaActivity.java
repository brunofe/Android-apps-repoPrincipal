package com.studio.atmempresaconsultoria;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class EmpresaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);
        TextView sobreEmpresa = (TextView) findViewById(R.id.texto_empresaId);
        sobreEmpresa.setMovementMethod(new ScrollingMovementMethod());
    }
}
