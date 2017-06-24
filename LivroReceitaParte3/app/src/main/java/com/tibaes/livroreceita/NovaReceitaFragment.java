package com.tibaes.livroreceita;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.firebase.database.DatabaseReference;
import com.tibaes.livroreceita.database.FirebaseHelper;
import com.tibaes.livroreceita.model.Receita;


public class NovaReceitaFragment extends Fragment {

    EditText etNome, etIngredientes, etModoPreparo, etTempoPreparo, etQuantidadePorcoes;
    RatingBar rbNivelDificuldade;
    Button btnSalvar;

    Receita receita;

    public NovaReceitaFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_nova_receita, container, false);

        etNome = (EditText) view.findViewById(R.id.et_nome);
        etIngredientes = (EditText) view.findViewById(R.id.et_ingredientes);
        etModoPreparo = (EditText) view.findViewById(R.id.et_modo_preparo);
        etTempoPreparo = (EditText) view.findViewById(R.id.et_tempo_preparo);
        etQuantidadePorcoes = (EditText) view.findViewById(R.id.et_quantidade_porcoes);
        rbNivelDificuldade = (RatingBar) view.findViewById(R.id.rb_nivel_dificuldade);

        btnSalvar = (Button) view.findViewById(R.id.btn_salvar);

        receita = new Receita();

        Intent intent = getActivity().getIntent();
        receita = (Receita) intent.getSerializableExtra("goReceita");
        if (receita != null) {
            etNome.setText(receita.getNome());
            rbNivelDificuldade.setRating(receita.getNivelDificuldade());
            Button btnExcluir = (Button) view.findViewById(R.id.btn_excluir);
            btnExcluir.setVisibility(View.VISIBLE);
            btnExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseHelper.deleteData(receita.getId());
                    getActivity().finish();
                }
            });

        /*
            etNome.setText(receita.getNome());
            etIngredientes.setText(receita.getIngredientes());
            etModoPreparo.setText(receita.getModoPreparo());
            etTempoPreparo.setText(receita.getTempoPreparo().toString());
            etQuantidadePorcoes.setText(receita.getQuantidadePorcoes().toString());
        */
        }


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            FirebaseHelper firebaseHelper = new FirebaseHelper(getContext());

            if (receita == null || receita.getId() == null) {
                receita = new Receita(etNome.getText().toString(), rbNivelDificuldade.getRating());
            }
            else{

                receita.setNome(etNome.getText().toString());
                /*receita.setIngredientes(etIngredientes.getText().toString());
                receita.setModoPreparo(etModoPreparo.getText().toString());
                receita.setTempoPreparo(Integer.parseInt(etTempoPreparo.getText().toString()));
                receita.setQuantidadePorcoes(Integer.parseInt(etQuantidadePorcoes.getText().toString()));
                 */
                receita.setNivelDificuldade(rbNivelDificuldade.getRating());

            }
            firebaseHelper.saveData(receita);
            getActivity().finish();
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
}
