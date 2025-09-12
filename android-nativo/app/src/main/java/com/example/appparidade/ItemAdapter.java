package com.example.appparidade;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> lista;
    private List<Item> listaOriginal;
    private Context context;
    public ItemAdapter(Context context, List<Item> lista) {
        this.context = context;
        this.lista = new ArrayList<>(lista); // lista atual (filtrada)
        this.listaOriginal = new ArrayList<>(lista);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = lista.get(position);
        holder.textNome.setText(item.getNome());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalheActivity.class);
            intent.putExtra("nome", item.getNome());
            intent.putExtra("descricao", item.getDescricao());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNome;

        public ViewHolder(View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textNome);
        }
    }

    // Método de filtro
    public void filtrar(String texto) {
        lista.clear();
        if (texto.isEmpty()) {
            lista.addAll(listaOriginal);
        } else {
            texto = texto.toLowerCase();
            for (Item item : listaOriginal) {
                if (item.getNome().toLowerCase().contains(texto)) {
                    lista.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
