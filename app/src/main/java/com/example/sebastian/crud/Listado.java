package com.example.sebastian.crud;

import android.content.Intent;
import  android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        listView = (ListView) findViewById(R.id.listView);

        CargarListado();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Listado.this,listado.get(position),Toast.LENGTH_SHORT).show();
                //int clave = Integer.parseInt(listado.get(position).split("-")[0]);
                String clave = (listado.get(position).split(" ")[0]);
                String nombre = (listado.get(position).split(" ")[1]);
                String apellidos = (listado.get(position).split(" ")[2]);
                String edad = (listado.get(position).split(" ")[3]);
                String cedula = (listado.get(position).split(" ")[4]);

                Intent intent = new Intent(Listado.this,Modificar.class);
                intent.putExtra("Id", clave);
                intent.putExtra("Nombre", nombre);
                intent.putExtra("Apellidos", apellidos);
                intent.putExtra("Edad", edad);
                intent.putExtra("Cedula", cedula);

                startActivity(intent);
            }
        });

        //if para colocar la flecha hacia atras
        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CargarListado()
    {
        listado = ListaPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaPersonas()
    {
        ArrayList<String> datos = new ArrayList<String>();
        BaseHelper Helper = new BaseHelper(this, "demo", null,1);
        SQLiteDatabase db = Helper.getReadableDatabase();
        String sql = "SELECT Id,Nombre,Apellidos,Edad,Cedula FROM PERSONAS";
        Cursor c = db.rawQuery(sql,null);

        if (c.moveToFirst())
        {
            do
            {
                String linea = c.getString(0)+" "+ c.getString(1)+" "+ c.getString(2)+" "
                                + c.getString(3)+" "+ c.getString(4);
                datos.add(linea);

            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }

}
