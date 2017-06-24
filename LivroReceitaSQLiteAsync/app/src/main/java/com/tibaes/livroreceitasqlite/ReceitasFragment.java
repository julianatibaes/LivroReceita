package com.tibaes.livroreceitasqlite;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tibaes.livroreceitasqlite.adapter.ReceitaAdapter;
import com.tibaes.livroreceitasqlite.adapter.RecyclerViewOnClickListener;
import com.tibaes.livroreceitasqlite.dao.ReceitaDAO;

import com.tibaes.livroreceitasqlite.model.Receita;


import java.util.List;


public class ReceitasFragment extends Fragment implements RecyclerViewOnClickListener {

    private RecyclerView receitaListView;
    private List<Receita> receitaList;

    public ReceitasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ativar o menu na activity.
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receitas, container, false);
        receitaListView = (RecyclerView) view.findViewById(R.id.rv_lista);
        receitaListView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        receitaListView.setLayoutManager(llm);

        FloatingActionButton btnNovaReceita = (FloatingActionButton) view.findViewById(R.id.btn_new_receita);
        btnNovaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Receita receita = new Receita();
                receita.setId(57);
                receita.setNome("oi");
                receita.setNivelDificuldade((float) 12.3);

                List<Receita> receitaList = new ArrayList<Receita>();
                receitaList.add(receita);


                ReceitaDAO receitaDAO = new ReceitaDAO(getContext());
                Boolean verdade = receitaDAO.existe(receitaList.get(0));
                if(verdade)
                    Log.d("LOG:", "verdade");
                else
                    Log.d("LOG:", "mentira");
                */

               if (!getResources().getBoolean(R.bool.modoGrande)) {
                    Intent intent = new Intent(getContext(), NovaReceitaActivity.class);
                    startActivity(intent);
                } else {
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction tx = manager.beginTransaction();

                    NovaReceitaFragment novaReceitaFragment = new NovaReceitaFragment();
                    tx.replace(R.id.new_receita, novaReceitaFragment);
                    tx.commit();
                }

            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

       /* Call<ReceitaSync> call = new RetrofitInicializador().getReceitaService().lista();
        call.enqueue(new Callback<ReceitaSync>() {

            @Override
            public void onResponse(Call<ReceitaSync> call, Response<ReceitaSync> response) {
                ReceitaSync receitaSync = response.body();
                ReceitaDAO dao = new ReceitaDAO(getActivity());
                dao.sincroniza(receitaSync.getReceitas());
                dao.close();
            }

            @Override
            public void onFailure(Call<ReceitaSync> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
            }
        });
        */
        carregaDados();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void carregaDados() {
        /*
        receitaList = new ArrayList<Receita>();
        receitaList.add(new Receita("Brigadeiro", (float) 0.5));
        receitaList.add(new Receita("Cupcake", (float) 4.0));
        receitaList.add(new Receita("Cookie", (float) 3.5));
        receitaList.add(new Receita("Manjar", (float) 2.0));
        receitaList.add(new Receita("Torta de frango", (float) 2.5));
        receitaList.add(new Receita("Pizza", (float) 4.0));
        receitaList.add(new Receita("Bolo de cenoura", (float) 3.5));
        receitaList.add(new Receita("Bolo de chocolate", (float) 3.0));
        */



        RecyclerView.LayoutManager mManager = new LinearLayoutManager(getActivity().getApplicationContext());
        receitaListView.setLayoutManager(mManager);
        receitaListView.setItemAnimator(new DefaultItemAnimator());

        ReceitaDAO dao = new ReceitaDAO(getContext());
        receitaList = dao.pesquisar();
        dao.close();

        ReceitaAdapter adapter = new ReceitaAdapter(getActivity(), receitaList);
        adapter.setRecyclerViewOnClickListener(this);

        receitaListView.setAdapter(adapter);
    }

    @Override
    public void onClickListener(View view, int position) {
        ReceitaAdapter adapter = (ReceitaAdapter) receitaListView.getAdapter();
        Receita receita = adapter.getReceita(position);

        if (getResources().getBoolean(R.bool.modoGrande)) {
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction tx = manager.beginTransaction();

            NovaReceitaFragment novaReceitaFragment = new NovaReceitaFragment();

            Bundle parametros = new Bundle();
            parametros.putSerializable("goReceita", receita);
            novaReceitaFragment.setArguments(parametros);
            tx.replace(R.id.new_receita, novaReceitaFragment);
            tx.commit();
        } else {
            Intent intent = new Intent(getActivity(), NovaReceitaActivity.class);
            intent.putExtra("goReceita", receita);
            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_lista_receitas, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
