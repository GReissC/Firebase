package com.example.aulafirebase.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aulafirebase.PessoaDetalheActivity;
import com.example.aulafirebase.R;
import com.example.aulafirebase.models.Pessoa;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PessoaAdapter extends
        RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder> {
    private final ArrayList<Pessoa>pessoas;
    FirebaseFirestore db;

    public PessoaAdapter(ArrayList<Pessoa> pessoas) {
        db = FirebaseFirestore.getInstance();
        this.pessoas = pessoas;
    }

    @NonNull
    @Override
    public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                PessoaViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_pessoa_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaViewHolder holder, int position) {
        Pessoa p = pessoas.get(position);
        holder.textNome.setText(p.getNome());
        holder.buttonEditar.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(),
                    PessoaDetalheActivity.class);
            intent.putExtra("id",p.getId());
            holder.itemView.getContext().startActivity(intent);
        });
        holder.buttonExcluir.setOnClickListener(v -> {
            db.collection("pessoas").document(p.getId()).delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            pessoas.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, pessoas.size());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Erro ao excluir", e);
                        }
                    });

        });
    }

    @Override
    public int getItemCount() {
        return pessoas != null ? pessoas.size() : 0;
    }


    public class PessoaViewHolder extends RecyclerView.ViewHolder{
        TextView textNome;
        ImageButton buttonEditar, buttonExcluir;

        public PessoaViewHolder(View itemView){
            super(itemView);
            textNome = itemView.findViewById(R.id.textView);
            buttonEditar = itemView.findViewById(R.id.imageButtonEditar);
            buttonExcluir = itemView.findViewById(R.id.imageButtonExcluir);
        }
    }
}