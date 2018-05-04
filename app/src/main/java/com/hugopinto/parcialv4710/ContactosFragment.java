package com.hugopinto.parcialv4710;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
        Cursor cursor= getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, null
                , null,ContactsContract.Contacts.DISPLAY_NAME+" ASC");
        Uri fotografia= Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+
        "://"+getResources().getResourcePackageName(R.drawable.msn2)+'/'+
        getResources().getResourceTypeName(R.drawable.msn2)+'/'+getResources().getResourceEntryName(R.drawable.msn2));
        cursor.moveToFirst();
        while(cursor.moveToNext()){
                list.add(new Contacto(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA1)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE)),
                        fotografia.toString()));
            backup.add(new Contacto(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA1)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE)),
                    fotografia.toString()));


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