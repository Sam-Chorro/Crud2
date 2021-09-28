package com.itca.crud2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.itca.crud_v2.R;

import java.util.ArrayList;

public class list_view_articulos extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayAdapter adaptador;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;

    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_articulos);


        listViewPersonas = (ListView) findViewById(R.id.listViewPersonas);
        searchView = (SearchView) findViewById(R.id.searchView);

        adaptador = new ArrayAdapter
                (this, android.R.layout.simple_list_item_1,
                        conexion.consultaListaArticulos1());

        listViewPersonas.setAdapter(adaptador);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text = s;
                adaptador.getFilter().filter(text);

                return false;
            }
        });


        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                String informacion = "Código: " + conexion.consultaListaArticulos().get(pos).getDescripcion() + "\n ";
                informacion += "Descripción: " + conexion.consultaListaArticulos().get(pos).getDescripcion() + "\n ";
                informacion += "Precio: " + conexion.consultaListaArticulos().get(pos).getPrecio();

                Dto articulos = conexion.consultaListaArticulos().get(pos);

                Intent intent = new Intent(list_view_articulos.this, detallesarticulos.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("articulo", articulos);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    public void back(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}