package com.tibaes.livroreceitaretrofit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.tibaes.livroreceitaretrofit.dao.ReceitaDAO;
import com.tibaes.livroreceitaretrofit.model.Receita;
import com.tibaes.livroreceitaretrofit.retrofit.RetrofitInializador;
import com.tibaes.livroreceitaretrofit.service.ReceitaRetrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        if(!getResources().getBoolean(R.bool.modoGrande)) {
            Intent intent = getActivity().getIntent();
            receita = (Receita) intent.getSerializableExtra("goReceita");
        }else{
            Bundle parametros = getArguments();
            if (parametros != null) {
                receita  = (Receita) parametros.getSerializable("goReceita");
            }
        }

        if (receita != null) {
            etNome.setText(receita.getNome());
            rbNivelDificuldade.setRating(receita.getNivelDificuldade());
            /*
            etNome.setText(receita.getNome());
            etIngredientes.setText(receita.getIngredientes());
            etModoPreparo.setText(receita.getModoPreparo());
            etTempoPreparo.setText(receita.getTempoPreparo().toString());
            etQuantidadePorcoes.setText(receita.getQuantidadePorcoes().toString());
            */
            Button btnExcluir = (Button) view.findViewById(R.id.btn_excluir);
            btnExcluir.setVisibility(View.VISIBLE);
            btnExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Call<Void> call = new ReceitaRetrofit().getReceitaService().excluir(receita.getId().toString());
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            ReceitaDAO dao = new ReceitaDAO(getContext());
                            dao.excluir(receita);
                            dao.close();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("onFailure", "requisicao falhou");
                        }
                    });



                    //new RemoveReceitaTask(receita).execute();

                    getActivity().finish();
                }
            });
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> call;
                if (receita == null || receita.getId() == null) {
                    receita = new Receita(etNome.getText().toString(), rbNivelDificuldade.getRating());
                    ReceitaDAO dao = new ReceitaDAO(getContext());
                    dao.inserir(receita);

                     // instancia o objeto call com o método inserir da interface
                    call = new ReceitaRetrofit().getReceitaService().inserir(receita);

                    // para rodar a minha requisição de forma assíncrona
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Log.i("onResponse", "requisicao com sucesso");
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("onFailure", "requisicao falhou");
                        }
                    });
                    //call = new RetrofitInializador().getReceitaService().insere(receita);
                    /*call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Log.i("onResponse", "requisicao com sucesso");

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("onFailure", "requisicao falhou");
                        }
                    });
                    */
                }  else{
                    call = new ReceitaRetrofit().getReceitaService().alterar(receita);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Log.i("onResponse", "requisicao com sucesso");
                            receita.setNome(etNome.getText().toString());
                            receita.setNivelDificuldade(rbNivelDificuldade.getRating());
                            /*receita.setIngredientes(etIngredientes.getText().toString());
                            receita.setModoPreparo(etModoPreparo.getText().toString());
                            receita.setTempoPreparo(Integer.parseInt(etTempoPreparo.getText().toString()));
                            receita.setQuantidadePorcoes(Integer.parseInt(etQuantidadePorcoes.getText().toString()));
                             */
                            ReceitaDAO dao = new ReceitaDAO(getContext());
                            dao.alterar(receita);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("onFailure", "requisicao falhou");
                        }
                    });
                }

                //TODO (2) enviar os dados para o servidor
                //new InsereReceitaTask(receita).execute();




                if(getResources().getBoolean(R.bool.modoGrande)) {
                   /* FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction tx = manager.beginTransaction();

                    ListFragment listFragment  = new ListFragment();
                    tx.replace(R.id.rv_lista, listFragment);
                    tx.commit();
                    */
                }
                else{
                   getActivity().finish();

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

}
