package com.example.cenidet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cenidet.R;
import com.example.cenidet.json.Equipo;
import com.example.cenidet.json.EquipoEntrada;
import com.example.cenidet.models.ModelEntradaAux;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterEquipoEntrada extends RecyclerView.Adapter<AdapterEquipoEntrada.ViewHolder>{

    private List<EquipoEntrada> mList;
    private List<ModelEntradaAux> list = new ArrayList<ModelEntradaAux>();

    public AdapterEquipoEntrada(List<EquipoEntrada> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entrada, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EquipoEntrada equipo = mList.get(position);
        list.add(new ModelEntradaAux(holder.switchB));

        holder.tvEquipo.setText(equipo.getNombre());
        holder.tvMarca.setText("Modelo: "+equipo.getModelo());
        holder.tvNS.setText("Número de serie: "+equipo.getNo_serie());


        Picasso.get().load(equipo.getFoto()).error(R.drawable.ic_no_imagen).into(holder.imgPhoto);

        holder.switchB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (equipo.isEntregado()){
                    equipo.setEntregado(false);
                }else{
                    equipo.setEntregado(true);
                }
            }
        });
    }

    public boolean seleccionar (int ns){
        boolean tf = false;

       for (int i = 0; i < mList.size(); i++){
           if (mList.get(i).getNo_serie() == ns){
               list.get(i).activarSwitchB();
               mList.get(i).setEntregado(true);
               tf = true;
               break;
           }
       }

       return tf;
    }

    public List<EquipoEntrada> getmList() {
        return mList;
    }

    public boolean isSeleccionado(){
        boolean tf = false;

        for (EquipoEntrada entrada: mList){
            if (entrada.isEntregado()) {
                tf = true;
                break;
            }
        }

        return tf;
    }

    public void add(EquipoEntrada model){
        if (!mList.contains(model)){
            mList.add(model);
            notifyItemInserted(mList.size() -1);
        }
    }

    public void delete (int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void eliminaTodo(){
        if (mList.size() != 0){
            for (int i = mList.size()-1; i >= 0; i--){
                mList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView imgPhoto;
        TextView tvEquipo, tvMarca, tvNS;
        SwitchMaterial switchB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            imgPhoto = (ImageView) itemView.findViewById(R.id.imgPhoto);
            tvEquipo = (TextView) itemView.findViewById(R.id.tvEquipo);
            tvMarca = (TextView) itemView.findViewById(R.id.tvMarca);
            tvNS = (TextView) itemView.findViewById(R.id.tvNS);

            switchB = (SwitchMaterial) itemView.findViewById(R.id.switchB);
        }
    }
}
