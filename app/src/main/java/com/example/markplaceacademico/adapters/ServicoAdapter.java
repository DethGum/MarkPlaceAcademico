package com.example.markplaceacademico.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.markplaceacademico.R;
import com.example.markplaceacademico.models.Servico;

import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ViewHolder> {

    List<Servico> listaServicos;

    public ServicoAdapter(List<Servico> listaServicos) {
        this.listaServicos = listaServicos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_servico,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Servico servico = listaServicos.get(position);

        holder.textTitulo.setText(servico.getTitulo());

        holder.textDescricao.setText(servico.getDescricao());

        holder.textCategoria.setText(servico.getCategoria());

        holder.textPreco.setText(
                "R$ " + servico.getPreco()
        );
    }

    @Override
    public int getItemCount() {
        return listaServicos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitulo,
                textDescricao,
                textCategoria,
                textPreco;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitulo = itemView.findViewById(R.id.textTitulo);
            textDescricao = itemView.findViewById(R.id.textDescricao);
            textCategoria = itemView.findViewById(R.id.textCategoria);
            textPreco = itemView.findViewById(R.id.textPreco);
        }
    }
}