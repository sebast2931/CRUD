package com.example.sebastian.crud;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {

    EditText editNombre, editApellidos, editEdad,editCedula;
    Button bt_modificar;
    Button bt_borrar;
    String id;
    String nombre;
    String apellidos;
    String edad;
    String cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);


        Bundle b = getIntent().getExtras();

        if (b!=null)
        {
            id = b.getString("Id");
            nombre = b.getString("Nombre");
            apellidos = b.getString("Apellidos");
            edad = b.getString("Edad");
            cedula = b.getString("Cedula");
        }


        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellidos = (EditText) findViewById(R.id.editApellidos);
        editEdad = (EditText) findViewById(R.id.editEdad);
        editCedula = (EditText) findViewById(R.id.editCedula);


        editNombre.setText(nombre);
        editApellidos.setText(apellidos);
        editEdad.setText(edad);
        editCedula.setText(cedula);

        bt_modificar = (Button) findViewById(R.id.bt_modificar);
        bt_borrar = (Button) findViewById(R.id.bt_borrar);

        bt_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(Integer.parseInt(id));
                onBackPressed();
            }
        });

        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar(Integer.parseInt(id),editNombre.getText().toString(), editApellidos.getText().toString(),
                          editEdad.getText().toString(), editCedula.getText().toString());
                onBackPressed(); // para regresar hacia atras
            }
        });

    }

    private void Modificar(int id, String Nombre, String Apellidos, String Edad, String Cedula)
    {
        BaseHelper Helper = new BaseHelper(this, "demo", null,1);
        SQLiteDatabase db = Helper.getWritableDatabase();

        String Sql = "UPDATE PERSONAS SET Nombre='" + Nombre + "',Apellidos='" + Apellidos + "',Edad='" + Edad + "',Cedula='" + Cedula + "' WHERE Id=" + id;
        db.execSQL(Sql);
        db.close();
    }


    private void Eliminar(int id)
    {
        BaseHelper Helper = new BaseHelper(this, "demo", null,1);
        SQLiteDatabase db = Helper.getWritableDatabase();

        String Sql = "DELETE FROM PERSONAS  WHERE Id=" + id;
        db.execSQL(Sql);
        db.close();
    }

}
