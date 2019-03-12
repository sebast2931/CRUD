package com.example.sebastian.crud;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editNombre, editApellidos, editEdad,editCedula;
    Button bt_guardar, bt_mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellidos = (EditText) findViewById(R.id.editApellidos);
        editEdad = (EditText) findViewById(R.id.editEdad);
        editCedula = (EditText) findViewById(R.id.editCedula);

        bt_guardar = (Button) findViewById(R.id.bt_guardar);
        bt_mostrar = (Button) findViewById(R.id.bt_mostrar);

        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(editNombre.getText().toString(), editApellidos.getText().toString(),
                        editEdad.getText().toString(), editCedula.getText().toString());
                editNombre.setText("");
                editApellidos.setText("");
                editEdad.setText("");
                editCedula.setText("");
            }
        });

        bt_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Listado.class));
            }
        });

    }

    private void guardar(String Nombre, String Apellidos, String Edad, String Cedula)
    {
        BaseHelper Helper = new BaseHelper(this, "demo", null,1);
        SQLiteDatabase db = Helper.getWritableDatabase();

        try {

            ContentValues c = new ContentValues();
            c.put("Nombre", Nombre);
            c.put("Apellidos", Apellidos);
            c.put("Edad", Edad);
            c.put("Cedula", Cedula);
            db.insert("PERSONAS",null,c);
            db.close();
            Toast.makeText(this,"Registro Insertado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
