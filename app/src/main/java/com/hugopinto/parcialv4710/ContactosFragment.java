package com.hugopinto.parcialv4710;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.hugopinto.parcialv4710.R.id.action_search;
import static com.hugopinto.parcialv4710.R.id.add;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactosFragment extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    List<Contacto> list = new ArrayList<>();
    private ContactosRvAdapter adapter;
    List<Contacto> backup = new ArrayList<>();

    static final int REQUEST_CODE_ASK_PERMISSION = 2018;
    int Read;

    private void accessPermission(){
        Read = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS);

        if(Read != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CODE_ASK_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case REQUEST_CODE_ASK_PERMISSION:
                if(grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getContext(), "Ha autorizado el permiso", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getContext(),"Permiso denegado",Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ContactosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactosFragment newInstance(String param1, String param2) {
        ContactosFragment fragment = new ContactosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            accessPermission();


        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode==2){
            if(data.hasExtra("Clave")==true);
            Contacto p = (Contacto)data.getExtras().getSerializable("Clave");
            list.add(0,p);
            backup.add(0,p);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //inflando la vista contactos
        v=inflater.inflate(R.layout.fragment_contactos,container,false);
        recyclerView=v.findViewById(R.id.contactos_recycler);
        accessPermission();

        //seteando el layout manager en este caso grid
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        RecyclerView.LayoutManager layoutManager = gridLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ContactosRvAdapter(getContext(),getContactos());


        recyclerView.setAdapter(adapter);

        SearchView search = getActivity().findViewById(R.id.action_search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                list.clear();
                query = query.toLowerCase();

                for(int i=0; i < backup.size(); i++){
                    if(list.get(i).getNombre().toLowerCase().contains(query) ){
                        list.add(backup.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                return true;


            }



            @Override
            public boolean onQueryTextChange(String newText) {
                list.clear();
                if(newText.length() ==0) {
                    list.addAll(backup);
                    adapter.notifyDataSetChanged();
                }
                else {
                    newText = newText.toLowerCase();
                    for(int i=0; i < backup.size(); i++){
                        if(backup.get(i).getNombre().toLowerCase().contains(newText)){
                            list.add(backup.get(i));
                        }
                    }

                    adapter.notifyDataSetChanged();
                    return true;
                }

                return true;
            }
        });




        return v;


    }
    private List<Contacto> getContactos(){
        Cursor cursor= getContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null, null
                , null,ContactsContract.Contacts.DISPLAY_NAME+" ASC");
        Uri fotografia= Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+
                "://"+getResources().getResourcePackageName(R.drawable.msn2)+'/'+
                getResources().getResourceTypeName(R.drawable.msn2)+'/'+getResources().getResourceEntryName(R.drawable.msn2));
        while(cursor.moveToNext()) {
            Contacto x = new Contacto("","","","","","","",fotografia.toString());
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String apellido = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE));
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String ad = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_SOURCE));
            x.setNombre(name);
            x.setApellido(apellido);
            x.setId(id);
            x.setAddress(ad);


            Cursor phone = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            ,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+id,null,null);
            String phon;
            while (phone.moveToNext()){
                phon=phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if(phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))==null){
                    x.setImagendraw(fotografia.toString());
                }else{
                    x.setImagendraw(phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)));
                }
                x.setNumber(phon);
            }
            phone.close();


           list.add(x);
           backup.add(x);
        }



        return list;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}