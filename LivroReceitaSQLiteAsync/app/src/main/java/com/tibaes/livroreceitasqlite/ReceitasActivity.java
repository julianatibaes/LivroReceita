package com.tibaes.livroreceitasqlite;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ReceitasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        if (getResources().getBoolean(R.bool.modoGrande)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction tx = fragmentManager.beginTransaction();
            tx.replace(R.id.list_receita, new ReceitasFragment());
            tx.replace(R.id.new_receita, new NovaReceitaFragment());
            tx.commit();
        }
    }


}
