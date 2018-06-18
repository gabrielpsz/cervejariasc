package com.example.leon.floripapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class InformacoesActivity extends AppCompatActivity {

    private Cervejaria cervejaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);

        final CheckBox favorito = (CheckBox) findViewById(R.id.favorito);
        TextView nome = (TextView) findViewById((R.id.nome));
        Button mapsButton = (Button) findViewById((R.id.mapa));
        TextView horarioFuncionamento = (TextView) findViewById((R.id.horarioFuncionamento));
        TextView dataFuncionamento = (TextView) findViewById((R.id.dataFuncionamento));
        TextView descricao = (TextView) findViewById((R.id.descricao));

        Intent intent = getIntent();
        cervejaria = (Cervejaria) intent.getSerializableExtra("cervejaria");

        favorito.setChecked(cervejaria.isFavorito());
        nome.setText(cervejaria.getNome());
        horarioFuncionamento.setText(cervejaria.getHorarioFuncionamento());
        dataFuncionamento.setText(cervejaria.getDataFuncionamento());
        descricao.setText(cervejaria.getDescricao());

        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+cervejaria.getNome()+", " +cervejaria.getLocalizacao());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cervejaria.setFavorito(favorito.isChecked());
                CervejariaDAO dao = new CervejariaDAO(InformacoesActivity.this);
                dao.salvaAlteracao(cervejaria);
                dao.close();
            }
        });
    }

}

