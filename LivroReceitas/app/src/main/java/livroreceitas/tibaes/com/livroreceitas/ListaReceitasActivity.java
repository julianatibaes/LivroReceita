package livroreceitas.tibaes.com.livroreceitas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import livroreceitas.tibaes.com.livroreceitas.adapter.ReceitaAdapter;
import livroreceitas.tibaes.com.livroreceitas.adapter.RecyclerViewOnClickListener;
import livroreceitas.tibaes.com.livroreceitas.model.Receita;

public class ListaReceitasActivity extends AppCompatActivity implements RecyclerViewOnClickListener {

    private RecyclerView receitaListView;
    private List<Receita> receitaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receitas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        receitaList = new ArrayList<Receita>();
        receitaList.add(new Receita("Brigadeiro", (float) 0.5));
        receitaList.add(new Receita("Cupcake", (float) 4.0));
        receitaList.add(new Receita("Cookie", (float) 3.5));
        receitaList.add(new Receita("Manjar", (float) 2.0));
        receitaList.add(new Receita("Torta de frango", (float) 2.5));
        receitaList.add(new Receita("Pizza", (float) 4.0));
        receitaList.add(new Receita("Bolo de cenoura", (float) 3.5));
        receitaList.add(new Receita("Bolo de chocolate", (float) 3.0));


        receitaListView = (RecyclerView) findViewById(R.id.rv_list);
        receitaListView.setHasFixedSize(true);
        ReceitaAdapter adapter = new ReceitaAdapter(this, receitaList);
        adapter.setRecyclerViewOnClickListener(this);

        /* O LayoutManager é responsável por medir e posicionar as visualizações de itens dentro do
        Recycler View.
        Essa forma de trabalhar funciona de forma um pouco diferente quando estamos utilizando o
        Recycler View dentro de um fragmento.
         */

        RecyclerView.LayoutManager mManager = new LinearLayoutManager(getApplicationContext());
        receitaListView.setLayoutManager(mManager);
        receitaListView.setItemAnimator(new DefaultItemAnimator());

        receitaListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_receitas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickListener(View view, int position) {

    }
}
