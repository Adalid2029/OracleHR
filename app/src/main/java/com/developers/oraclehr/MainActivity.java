package com.developers.oraclehr;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    db manager;
    EditText codeSQL;
    ListView listaDicDatos;
    FloatingActionMenu menu;
    FloatingActionButton play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codeSQL = (EditText) findViewById(R.id.et_codeSQL);
        listaDicDatos = (ListView) findViewById(R.id.lv_tables);
        manager = new db(this);
        listaDicDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });
        showTablesListView();
        menu = (FloatingActionMenu)findViewById(R.id.menu);

        play = new FloatingActionButton(MainActivity.this);
        play.setLabelText("Create Database");
        play.setImageDrawable(ContextCompat.getDrawable(this.getApplicationContext(), R.mipmap.ic_create_db));
        //createDB.setImageDrawable(ContextCompat.getDrawable(this.mainView.getContext(), R.drawable.ic_create_db));
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditText();

            }
        });
        menu.addMenuButton(play);

    }
    public void DialogEditText(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_edittext);

        Button ok = (Button)dialog.findViewById(R.id.btn_ok);
        Button cancel= (Button)dialog.findViewById(R.id.btn_cancel);
        final EditText text = (EditText)dialog.findViewById(R.id.et_dialog);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.getText().toString().isEmpty())
                    Toast.makeText(MainActivity.this, R.string.stringEmpty, Toast.LENGTH_SHORT).show();
                else {
                    String f = text.getText().toString();
                    f = f.replace(" ", "");
                    createDataBase(f);
                    dialog.dismiss();
                }
            }
        });
        cancel = (Button)dialog.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void createDataBase(String f){
        try{
            db.DBHelper.DB_NAME=f;
            f.concat(".db");
            db.DBHelper.DB_VERSION=1;
            manager = new db(MainActivity.this);
            Toast.makeText(MainActivity.this, R.string.create_database, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.d("--->",""+e);
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu:
                try {
                    manager.runSQL(codeSQL.getText().toString());
                    Toast.makeText(this, "SQL Execute...", Toast.LENGTH_SHORT).show();
                    showTablesListView();
                    codeSQL.setText("");
                }catch(Exception e){
                    Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void showTablesListView(){
        ArrayList<String> alista = new ArrayList<>();
        MyListView myListView = new MyListView(MainActivity.this,alista);
        //ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alista);
        Cursor cursor = manager.verTablas();
        if (cursor.moveToFirst()) {
            do {
                String dato = cursor.getString(0);
                alista.add(dato);
                myListView.notifyDataSetChanged();
            } while (cursor.moveToNext());
        }
        listaDicDatos.setAdapter(myListView);
    }
}
