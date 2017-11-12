package com.example.killer.seminario;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Pattern;

public class IniciarActivity extends AppCompatActivity {
    private EditText edittext_id_v;
    private EditText edittext_pass_v;
    private Switch switch_redordar;
    private Button boton_ini_ses;
    private SharedPreferences preferencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);
        secion_ui();
        preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);

        guardar_credencial();

        boton_ini_ses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r_id = edittext_id_v.getText().toString();
                String r_pass = edittext_pass_v.getText().toString();
                if (logear(r_id, r_pass)) {
                    ir_a_vend_al();
                    guardar_preferencias(r_id, r_pass);
                }

            }
        });

    }

    private void secion_ui() {
        edittext_id_v = (EditText) findViewById(R.id.id_id_vendedor);
        edittext_pass_v = (EditText) findViewById(R.id.id_pass_vendedor);
        switch_redordar = (Switch) findViewById(R.id.id_swith_recordar_v);
        boton_ini_ses = (Button) findViewById(R.id.id_boton_in_ses);
    }

    private boolean validar_id_v(String v_id) {
        return v_id.length() > 0;

    }

    private boolean validar_pass(String pass) {
        return pass.length() > 8;
    }

    private boolean logear(String c_id, String pass) {
        if (!validar_id_v(c_id)) {
            Toast.makeText(this, "Id no valido", Toast.LENGTH_LONG).show();
            return false;
        } else if (!validar_pass(pass)) {
            Toast.makeText(this, "Contraseña no valida", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void ir_a_vend_al() {
        Intent intent = new Intent(IniciarActivity.this, Vend_al_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void guardar_preferencias(String c_id, String pass) {
        if (switch_redordar.isChecked()) {
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("c_id", c_id);
            editor.putString("pass", pass);
            editor.apply();
        }
    }
    public void guardar_credencial() {
        String id = obtener_id_usuario();
        String pass = obtener_pass_usuario();
        if(!TextUtils.isEmpty(id)&& !TextUtils.isEmpty(pass)){
            edittext_id_v.setText(id);
            edittext_pass_v.setText(pass);
        }
    }

    private String obtener_id_usuario() {
        return preferencias.getString("c_id", "");
    }

    private String obtener_pass_usuario() {
        return preferencias.getString("pass", "");
    }

}