package com.example.cenidet.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cenidet.R;
import com.example.cenidet.models.ModelAlumno;
import com.example.cenidet.models.ModelPrincipal;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class AdapterAlumno extends RecyclerView.Adapter<AdapterAlumno.ViewHolder> {

    private List<ModelAlumno> mList;

    public AdapterAlumno(List<ModelAlumno> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_alumno, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelAlumno model = mList.get(position);

        holder.nombre.setText(model.getNombre());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.isValidar()){
                    model.setValidar(false);
                    holder.btn.setText("validar");
                    holder.btn.setTextColor(Color.parseColor("#00574B"));
                }else{
                    model.setValidar(true);
                    holder.btn.setText("cancelar");
                    holder.btn.setTextColor(Color.parseColor("#F44336"));
                }
            }
        });
    }

    public void add(ModelAlumno model){
        if (!mList.contains(model)){
            mList.add(model);
            notifyItemInserted(mList.size() -1);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public boolean isFullList (){
        for (ModelAlumno m: mList){
            if (m.isValidar() == false){
                return false;
            }
        }
        return true;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        MaterialButton btn;
        TextView nombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            btn = (MaterialButton) itemView.findViewById(R.id.btn);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
        }
    }

}
