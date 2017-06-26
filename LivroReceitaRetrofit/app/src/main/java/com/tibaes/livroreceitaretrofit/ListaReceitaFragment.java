package com.tibaes.livroreceitaretrofit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.view.View;
import android.view.ViewGroup;

import com.tibaes.livroreceitaretrofit.adapter.ReceitaAdapter;
import com.tibaes.livroreceitaretrofit.adapter.RecyclerViewOnClickListener;
import com.tibaes.livroreceitaretrofit.dao.ReceitaDAO;
import com.tibaes.livroreceitaretrofit.model.Receita;
import com.tibaes.livroreceitaretrofit.retrofit.RetrofitInializador;
import com.tibaes.livroreceitaretrofit.service.ReceitaRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListaReceitaFragment extends Fragment implements RecyclerViewOnClickListener {

    private List<Receita> receitaList;
    private RecyclerView receitaListView;

    public ListaReceitaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_receita, container, false);

        receitaListView = (RecyclerView) view.findViewById(R.id.rv_lista);
        receitaListView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        receitaListView.setLayoutManager(llm);

        FloatingActionButton btnNovaReceita = (FloatingActionButton) view.findViewById(R.id.btn_new_receita);
        btnNovaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getResources().getBoolean(R.bool.modoGrande)) {
                    Intent intent = new Intent(getContext(), NovaReceitaActivity.class);
                    startActivity(intent);
                } else {
                   /* FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction tx = manager.beginTransaction();

                    NovaReceitaFragment novaReceitaFragment = new NovaReceitaFragment();
                    tx.replace(R.id.new_receita, novaReceitaFragment);
                    tx.commit();
                    */
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        //carregaDados();
        Call<List<Receita>> retornaReceitas = new ReceitaRetrofit().getReceitaService().listar();
        retornaReceitas.enqueue(new Callback<List<Receita>>() {
            @Override
            public void onResponse(Call<List<Receita>> call, Response<List<Receita>> response) {

                // response.body o json retornado do webservice e convertido para lista
                // a maneira com que ele retorna foi definida na interface
                List<Receita> receitas= response.body();
                ReceitaDAO dao = new ReceitaDAO(getContext());
                dao.sincronizar(receitas);
                dao.close();
                carregaDados();
            }

            @Override
            public void onFailure(Call<List<Receita>> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
            }
        });
    }

    private void carregaDados() {
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
           /* FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction tx = manager.beginTransaction();

            NovaReceitaFragment novaReceitaFragment = new NovaReceitaFragment();

            Bundle parametros = new Bundle();
            parametros.putSerializable("goReceita", receita);
            novaReceitaFragment.setArguments(parametros);
            tx.replace(R.id.new_receita, novaReceitaFragment);
            tx.commit();
            */
        } else {
            Intent intent = new Intent(getActivity(), NovaReceitaActivity.class);
            intent.putExtra("goReceita", receita);
            startActivity(intent);
        }
    }
}
