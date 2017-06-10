package com.tibaes.livroreceita.database;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tibaes.livroreceita.LivroReceitaFragment;
import com.tibaes.livroreceita.adapter.ReceitaAdapter;
import com.tibaes.livroreceita.adapter.RecyclerViewOnClickListener;
import com.tibaes.livroreceita.model.Receita;

import java.util.ArrayList;

/**
 * Created by Juliana Tibães on 05/06/2017.
 * LivroReceita
 */

public class FirebaseHelper {

    private Context context;
    private RecyclerView listView;
    private DatabaseReference rootRef;
    private ArrayList<Receita> receitaList= new ArrayList<>();
    private ReceitaAdapter adapter;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;

    public FirebaseHelper(Context context, RecyclerViewOnClickListener recyclerViewOnClickListener, RecyclerView listView)
    {
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
        this.context= context;
        this.listView= listView;

        rootRef= FirebaseDatabase.getInstance().getReference();
        // trabalho com os dados em of line
        rootRef.keepSynced(false);
    }

    public FirebaseHelper(Context context)
    {
        this.context= context;
        rootRef= FirebaseDatabase.getInstance().getReference("receita");
        rootRef.keepSynced(false);
    }

    public  void saveData(Receita receita) {
        if (receita.getId()!=null){
            rootRef.child(receita.getId()).setValue(receita);
        }
        else{
            String id = rootRef.push().getKey();
            receita.setId(id);
            rootRef.child(id).setValue(receita);
        }
    }

    public void refreshData()
    {
        rootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });
    }

    public void getUpdates(DataSnapshot dataSnapshot){

        receitaList.clear();

        for(DataSnapshot ds :dataSnapshot.getChildren()){
            Receita receita= new Receita();
            receita.setId(ds.getValue(Receita.class).getId());
            receita.setNome(ds.getValue(Receita.class).getNome());
            receita.setNivelDificuldade(ds.getValue(Receita.class).getNivelDificuldade());

            receitaList.add(receita);
        }
        if(receitaList.size()>0){
            adapter = new ReceitaAdapter(context, receitaList);
            adapter.setRecyclerViewOnClickListener(recyclerViewOnClickListener);
            listView.setAdapter(adapter);
        }else {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
        }
    }


    public static void deleteData(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("receita").child(id);
        //removendo receita
        dR.removeValue();
    }

}
