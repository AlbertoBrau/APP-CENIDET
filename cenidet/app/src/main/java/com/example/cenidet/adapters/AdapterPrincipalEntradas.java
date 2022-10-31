package com.example.cenidet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cenidet.R;
import com.example.cenidet.models.ModelPrincipal;
import com.example.cenidet.utils.OnClickListenerPrincipal;
import com.example.cenidet.utils.OnClickListenerPrincipalEntradas;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterPrincipalEntradas extends RecyclerView.Adapter<AdapterPrincipalEntradas.ViewHolder>{

    private List<ModelPrincipal> mList;
    private OnClickListenerPrincipalEntradas mListener;

    public AdapterPrincipalEntradas(List<ModelPrincipal> mList, OnClickListenerPrincipalEntradas mListener) {
        this.mList = mList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_principal_entradas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelPrincipal model = mList.get(position);

        holder.setClickListener(mListener, model);

        Picasso.get().load(model.getImagen()).into(holder.img);

        holder.codigo.setText(model.getCodigo());
        String texto = "";
        switch (model.getnCase()){
            case 1:
                texto = indentificaSexo(model.getSexo()) + " docente "+model.getNombre()+" tiene pendiente a entregar equipo.";
                break;
            case 2:
                texto = indentificaSexo(model.getSexo()) + " docente "+model.getNombre()+" autorizo la salida de equipo a "+ model.getList().get(0).getNombre()+" y tiene pendiente a entregar equipo.";
                break;
            case 3:
                texto = indentificaSexo(model.getSexo()) + " docente "+model.getNombre()+" autorizo la salida de equipo para el/la alumno(a) "+ model.getList().get(0).getNombre()+" y tiene pendiente a entregar equipo.";
                break;
            case 4:
                texto = indentificaSexo(model.getSexo()) + " docente "+model.getNombre()+" autorizo la salida de equipo a varios alumnos  y tiene pendiente a entregar equipo.";
                break;
        }
        holder.texto.setText(texto.toString());
    }

    private String indentificaSexo(String sexo){
        return (sexo.equals("masculino")) ? "El" : "La";
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void add(ModelPrincipal model){
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

    class ViewHolder extends RecyclerView.ViewHolder{
        View view;

        ImageView img;
        TextView codigo, texto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            img = (ImageView) itemView.findViewById(R.id.imageView);
            codigo = (TextView) itemView.findViewById(R.id.codigo);
            texto = (TextView) itemView.findViewById(R.id.texto);
        }

        void setClickListener(OnClickListenerPrincipalEntradas listener, ModelPrincipal model){
            view.setOnClickListener(view -> listener.onClickEntradas(model));
        }
    }

}
