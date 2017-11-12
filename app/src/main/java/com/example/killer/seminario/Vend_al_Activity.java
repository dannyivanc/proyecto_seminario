package com.example.killer.seminario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Vend_al_Activity extends AppCompatActivity {
    private SharedPreferences preferencias;
    private Button boton_ir_citas;
    private Button boton_crear_galeria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vend_al_);
        seccion_botones();
        preferencias=getSharedPreferences("preferencias", Context.MODE_PRIVATE);

        //para ir a citas
        boton_ir_citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ir_a_ver_citas();
            }
        });

        //para ir a crear galeria
        boton_crear_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ir_crear_galeria();
            }
        });


    }

    public void seccion_botones(){
        boton_ir_citas=(Button) findViewById(R.id.id_b_ver_citas);
        boton_crear_galeria=(Button)findViewById(R.id.id_b_crear_galeria);
    }


    //para ir a crear
    private void ir_a_ver_citas(){
        Intent intent=new Intent(Vend_al_Activity.this,Ver_citas_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    //para ir a galeria
    private void ir_crear_galeria(){
        Intent intent=new Intent(Vend_al_Activity.this,Crear_galeria_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    //Menu para olvidar y salir
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_salir:
                salir();
                return true;
            case R.id.menu_salir_olvidar:
                salir_y_borrar();
                salir();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void salir(){
        Intent intent=new Intent(this,IniciarActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
    private void salir_y_borrar(){
        preferencias.edit().clear().apply();
    }

}