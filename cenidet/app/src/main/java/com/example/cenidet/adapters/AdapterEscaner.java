package com.example.cenidet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cenidet.R;
import com.example.cenidet.models.ModelJSON;

import java.util.List;

public class AdapterEscaner extends RecyclerView.Adapter<AdapterEscaner.ViewHolder>  {

    private List<ModelJSON> mList;

    public AdapterEscaner(List<ModelJSON> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_caracteristicas, parent, false);
        return new AdapterEscaner.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelJSON model = mList.get(position);

        holder.titulo.setText(model.getTitulo());
        holder.descripcion.setText(model.getValor());
    }

    @Override
    public int getItemCount() {
         return mList.size();
    }

    public void add(ModelJSON model){
        if (!mList.contains(model)){
            mList.add(model);
            notifyItemInserted(mList.size() -1);
        }
    }

    public void eliminaTodo(){
        if (mList.size() != 0){
            for (int i = mList.size()-1; i >= 0; i--){
                mList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView titulo, descripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            titulo = (TextView) itemView.findViewById(R.id.titulo);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion);
        }
    }
}
