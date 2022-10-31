package com.example.cenidet.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cenidet.R;
import com.example.cenidet.activities.DetalleActivity;
import com.example.cenidet.activities.EquipoActivity;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.models.ModelPrincipal;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterEquipo extends RecyclerView.Adapter<AdapterEquipo.ViewHolder>{

    private List<Equipo> mList;
    private Context context;

    public AdapterEquipo(Context context) {
        this.mList = new ArrayList<Equipo>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_equipo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Equipo equipo = mList.get(position);

        holder.tvEquipo.setText(equipo.getNombre());
        holder.tvMarca.setText("Modelo: "+equipo.getModelo());
        holder.tvNS.setText("Número de serie: "+equipo.getNo_serie());

        Picasso.get().load(equipo.getFoto()).error(R.drawable.ic_no_imagen).into(holder.imgPhoto);

        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
            }
        });

        holder.detalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalleActivity.class);
                intent.putExtra("equipo", equipo);
                v.getContext().startActivity(intent);
            }
        });
    }

    public void add(Equipo model){
        if (!mList.contains(model)){
            mList.add(model);
            notifyItemInserted(mList.size() -1);
        }
    }

    public void delete (int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView imgPhoto;
        TextView tvEquipo, tvMarca,tvNS;
        MaterialButton detalles;
        ImageButton eliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            imgPhoto = (ImageView) itemView.findViewById(R.id.imgPhoto);
            tvEquipo = (TextView) itemView.findViewById(R.id.tvEquipo);
            tvMarca = (TextView) itemView.findViewById(R.id.tvMarca);
            tvNS = (TextView) itemView.findViewById(R.id.tvNS);
            eliminar = (ImageButton) itemView.findViewById(R.id.eliminar);
            detalles = (MaterialButton) itemView.findViewById(R.id.detalles);
        }
    }
}
