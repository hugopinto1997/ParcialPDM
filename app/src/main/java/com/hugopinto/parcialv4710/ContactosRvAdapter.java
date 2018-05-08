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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public abstract class ContactosRvAdapter extends RecyclerView.Adapter<ContactosRvAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Contacto> mlistaContactos;
    private Context contexto;
    public int Contador=0;

    public ContactosRvAdapter(Context Context, List<Contacto> listaContactos){

       mlistaContactos = listaContactos;
       mContext = Context;


    }
    public ContactosRvAdapter(List<Contacto> listaContactos){

        mlistaContactos = listaContactos;


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
        CheckBox star;
        ImageView delete;
        delete = holder.delete;
        star = holder.star;
        imagen=holder.imagen;
        name=holder.nombre;
        if(mlistaContactos.get(position).getImagendraw() == null){
            imagen.setImageAlpha(R.drawable.msn2);
        }else{
            imagen.setImageURI(Uri.parse(mlistaContactos.get(position).getImagendraw().toString()));
        }
        name.setText(mlistaContactos.get(position).getNombre());
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mlistaContactos.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mlistaContactos.size()-position);
                notifyDataSetChanged();
            }
        });
        name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent newIntent = new Intent(v.getContext(),MainContactClick.class);
                Bundle caja = new Bundle();
                caja.putSerializable("Llave", mlistaContactos.get(position));
                newIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(position));
                newIntent.putExtras(caja);
                mContext.startActivity(newIntent);
            }
        });
        imagen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent newIntent = new Intent(view.getContext(),imagenfull.class);
                Bundle caja = new Bundle();
                caja.putSerializable("imagen", mlistaContactos.get(position));
                newIntent.putExtras(caja);
                mContext.startActivity(newIntent);
            }
        });
        star.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(mContext, "sirve", Toast.LENGTH_SHORT).show();
                mlistaContactos.get(position).setCheck(isChecked);
                onVerClick(buttonView,position);
                Contador(Contador);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mlistaContactos.size();
    }
    public abstract void onVerClick(View v,int pos);
    public abstract void Contador(int cont);


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        ImageView imagen;
        CheckBox star;
        ImageView delete;

        public ViewHolder(View itemView) {
            super(itemView);
            star = itemView.findViewById(R.id.estrella);
            nombre=itemView.findViewById(R.id.name);
            imagen=itemView.findViewById(R.id.imgl);
            delete= itemView.findViewById(R.id.del);





        }
    }




}
