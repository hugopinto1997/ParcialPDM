package com.hugopinto.parcialv4710;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class ContactosRvAdapter extends RecyclerView.Adapter<ContactosRvAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Contacto> mlistaContactos;
    private Context contexto;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        TextView name;
        ImageView imagen;
        imagen=holder.imagen;
        name=holder.nombre;
        if(mlistaContactos.get(position).getImagendraw() == null){
            imagen.setImageAlpha(R.drawable.msn);
        }else{
            imagen.setImageURI(Uri.parse(mlistaContactos.get(position).getImagendraw().toString()));
        }
        name.setText(mlistaContactos.get(position).getNombre());
        name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent newIntent = new Intent(v.getContext(),MainContactClick.class);
                Bundle caja = new Bundle();
                caja.putSerializable("Llave", mlistaContactos.get(position));
                newIntent.putExtras(caja);
                mContext.startActivity(newIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlistaContactos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);

            nombre=itemView.findViewById(R.id.name);
            imagen=itemView.findViewById(R.id.imgl);





        }
    }




}
