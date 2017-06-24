package com.tibaes.livroreceita.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tibaes.livroreceita.R;
import com.tibaes.livroreceita.model.Receita;

import java.util.List;

/**
 * Created by Juliana Tibães on 05/06/2017.
 * LivroReceita
 */

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaAdapter.MyViewHolder>  {

    private List<Receita> receitaList;
    private LayoutInflater layoutInflater;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;

    public ReceitaAdapter(Context context, List<Receita> receitaList) {
            this.receitaList = receitaList;
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }

    public Receita getReceita(int position){
            return receitaList.get(position);
            }

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener recyclerViewOnClickListener){
            this.recyclerViewOnClickListener = recyclerViewOnClickListener;
            }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.item_lista, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
            }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tvNome.setText(receitaList.get(position).getNome());
            holder.tvNota.setRating(receitaList.get(position).getNivelDificuldade());
            }

    @Override
    public int getItemCount() {
            return receitaList.size();
            }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvNome;
        public RatingBar tvNota;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNome = (TextView) itemView.findViewById(R.id.item_list_nome);
            tvNota = (RatingBar) itemView.findViewById(R.id.item_list_nota);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnClickListener != null) {
                recyclerViewOnClickListener.onClickListener(v, getPosition());
            }
        }
    }

    public void remove(Receita receita){

    }
}
