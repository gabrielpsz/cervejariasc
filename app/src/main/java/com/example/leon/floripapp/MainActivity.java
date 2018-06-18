package com.example.leon.floripapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.list_view);

        carregaLista();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cervejaria cervejaria = (Cervejaria)lista.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this,InformacoesActivity.class);
                intent.putExtra("cervejaria",cervejaria);
                startActivity(intent);
            }
        });
    }

    @NonNull
    private ListView carregaLista() {
        CervejariaDAO dao = new CervejariaDAO(this);
        List<Cervejaria> pontos = dao.buscaCervejarias();
        dao.close();

        ArrayAdapter<Cervejaria> adapter = new ArrayAdapter<Cervejaria>(this,android.R.layout.simple_list_item_1,pontos);
        lista.setAdapter(adapter);
        return lista;
    }

    private ListView carregaListaPorFavorito() {
        CervejariaDAO dao = new CervejariaDAO(this);
        List<Cervejaria> pontos = dao.buscaCervejariasFavoritas();
        dao.close();

        ArrayAdapter<Cervejaria> adapter = new ArrayAdapter<Cervejaria>(this,android.R.layout.simple_list_item_1,pontos);
        lista.setAdapter(adapter);
        return lista;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_informacoes_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ordenaFavoritos:
                carregaListaPorFavorito();
                break;
            case R.id.ordenaNome:
                carregaLista();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
