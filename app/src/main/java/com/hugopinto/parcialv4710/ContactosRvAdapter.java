package com.hugopinto.parcialv4710;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class ContactosRvAdapter extends RecyclerView.Adapter<ContactosRvAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Contacto> mlistaContactos;

    public ContactosRvAdapter(Context Context, List<Contacto> listaContactos){

       mlistaContactos = listaContactos;
       mContext = Context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_contacto,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView nombre;

        nombre=holder.nombre;

        nombre.setText(mlistaContactos.get(position).getNombre());




    }

    @Override
    public int getItemCount() {
        return mlistaContactos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;

        public ViewHolder(View itemView) {
            super(itemView);

            nombre=itemView.findViewById(R.id.name);





        }
    }




}
