package com.tibaes.livroreceita;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.plus.PlusOneButton;
import com.tibaes.livroreceita.adapter.ReceitaAdapter;
import com.tibaes.livroreceita.adapter.RecyclerViewOnClickListener;
import com.tibaes.livroreceita.database.FirebaseHelper;
import com.tibaes.livroreceita.model.Receita;

import java.util.ArrayList;
import java.util.List;


public class LivroReceitaFragment extends Fragment implements RecyclerViewOnClickListener {

    private RecyclerView receitaListView;
    private List<Receita> receitaList;

    FirebaseHelper firebaseHelper;

    public LivroReceitaFragment() {
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
        View view = inflater.inflate(R.layout.fragment_livro_receita, container, false);
        receitaListView = (RecyclerView) view.findViewById(R.id.rv_lista);
        receitaListView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        receitaListView.setLayoutManager(llm);

        FloatingActionButton btnNovaReceita = (FloatingActionButton) view.findViewById(R.id.btn_new_receita);
        btnNovaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NovaReceitaActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
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

    private void carregaDados(){
       /* receitaList = new ArrayList<Receita>();
        receitaList.add(new Receita("Brigadeiro", (float) 0.5));
        receitaList.add(new Receita("Cupcake", (float) 4.0));
        receitaList.add(new Receita("Cookie", (float) 3.5));
        receitaList.add(new Receita("Manjar", (float) 2.0));
        receitaList.add(new Receita("Torta de frango", (float) 2.5));
        receitaList.add(new Receita("Pizza", (float) 4.0));
        receitaList.add(new Receita("Bolo de cenoura", (float) 3.5));
        receitaList.add(new Receita("Bolo de chocolate", (float) 3.0));


        ReceitaAdapter adapter = new ReceitaAdapter(getActivity(), receitaList);
        adapter.setRecyclerViewOnClickListener(this);

        /* O LayoutManager é responsável por medir e posicionar as visualizações de itens dentro do
        Recycler View.
        Essa forma de trabalhar funciona de forma um pouco diferente quando estamos utilizando o
        Recycler View dentro de um fragmento.


        RecyclerView.LayoutManager mManager = new LinearLayoutManager(getActivity().getApplicationContext());
        receitaListView.setLayoutManager(mManager);
        receitaListView.setItemAnimator(new DefaultItemAnimator());

        receitaListView.setAdapter(adapter);
        */
        firebaseHelper= new FirebaseHelper(getActivity(), this, receitaListView);
        firebaseHelper.refreshData();
        //firebaseHelper.adapter.setRecyclerViewOnClickListener(this);
    }

    @Override
    public void onClickListener(View view, int position) {
        ReceitaAdapter adapter = (ReceitaAdapter) receitaListView.getAdapter();
        Receita receita = adapter.getReceita(position);

        Intent intent = new Intent(getActivity(), NovaReceitaActivity.class);
        intent.putExtra("goReceita", receita);
        startActivity(intent);
    }
}
