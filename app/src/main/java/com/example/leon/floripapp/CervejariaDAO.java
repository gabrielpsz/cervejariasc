package com.example.leon.floripapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CervejariaDAO extends SQLiteOpenHelper {

    public CervejariaDAO(Context context) {
        super(context, "GuiaTuristico", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE cervejaria (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, descricao TEXT, localizacao TEXT, dataFuncionamento TEXT, horarioFuncionamento TEXT, favorito BOOLEAN);";
        sqLiteDatabase.execSQL(sql);

        String insereDados = "INSERT INTO cervejaria VALUES (1, 'On Tap Cervejaria Artesanal', 'Cervejaria Artesanal', 'R. Waldemar Silveira de Souza, 92', 'sempre funcionando', 'sem hora', 0)," +
                "(2, 'Cervejaria Catarina', 'descr', 'Rua Laurindo Januário da Silveira, 1233', 'ta sempre lá', 'all day all night', 1)," +
                "(3, 'Cervejaria Badenia', 'descr', 'R. Intendente Broering, 3479', 'dias de semana', 'até as 10', 0);";
        sqLiteDatabase.execSQL(insereDados);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS cervejaria";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public List<Cervejaria> buscaCervejarias(){
        SQLiteDatabase database = getReadableDatabase();
        onUpgrade(database, 0, 0);
        Cursor c = database.rawQuery("SELECT * From cervejaria", null);

        List<Cervejaria> cervejarias =  new ArrayList<Cervejaria>();
        while(c.moveToNext()){
            Cervejaria cervejaria = new Cervejaria();
            cervejaria.setId(c.getInt(c.getColumnIndex("id")));
            cervejaria.setNome(c.getString(c.getColumnIndex("nome")));
            cervejaria.setDescricao(c.getString(c.getColumnIndex("descricao")));
            cervejaria.setLocalizacao(c.getString(c.getColumnIndex("localizacao")));
            cervejaria.setDataFuncionamento(c.getString(c.getColumnIndex("dataFuncionamento")));
            cervejaria.setHorarioFuncionamento(c.getString(c.getColumnIndex("horarioFuncionamento")));
            cervejaria.setFavorito((c.getInt(c.getColumnIndex("favorito"))) > 0);

            cervejarias.add(cervejaria);
        }
        c.close();
        return cervejarias;
    }

    public List<Cervejaria> buscaCervejariasFavoritas(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * From cervejaria order by favorito desc", null);

        List<Cervejaria> cervejarias =  new ArrayList<Cervejaria>();
        while(c.moveToNext()){
            Cervejaria cervejaria = new Cervejaria();
            cervejaria.setId(c.getInt(c.getColumnIndex("id")));
            cervejaria.setNome(c.getString(c.getColumnIndex("nome")));
            cervejaria.setDescricao(c.getString(c.getColumnIndex("descricao")));
            cervejaria.setLocalizacao(c.getString(c.getColumnIndex("localizacao")));
            cervejaria.setDataFuncionamento(c.getString(c.getColumnIndex("dataFuncionamento")));
            cervejaria.setHorarioFuncionamento(c.getString(c.getColumnIndex("horarioFuncionamento")));
            cervejaria.setFavorito((c.getInt(c.getColumnIndex("favorito"))) > 0);

            cervejarias.add(cervejaria);
        }
        c.close();
        return cervejarias;
    }

    public void salvaAlteracao(Cervejaria cervejaria){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        if(cervejaria.isFavorito()) {
            values.put("favorito",1);
        }else{
            values.put("favorito",0);
        }
        String[] params={String.valueOf(cervejaria.getId())};
        database.update("cervejaria",values,"id = ?",params);
    }

}
