package com.example.spesa;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment1 extends Fragment {
    private static final String TAG = "Fragment1";

    Button btnNavFrag1;
    Button btnNavFrag2;
    Button btnNavFrag3;
    Button add_food_general;
    Button clear_spesa;
    Button spesa_completed;
    EditText edit_name;
    ListView listview_general;
    ListView listview_particular;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container,false);

        Log.d(TAG, "onCreateView: started.");

        btnNavFrag1 = (Button) view.findViewById(R.id.btnNavFrag1);
        btnNavFrag2 = (Button) view.findViewById(R.id.btnNavFrag2);
        btnNavFrag3 = (Button) view.findViewById(R.id.btnNavFrag3);

        edit_name = (EditText) view.findViewById(R.id.edit_name);
        add_food_general = (Button) view.findViewById(R.id.add_food_general);

        listview_general = (ListView) view.findViewById(R.id.listview_general);
        listview_particular = (ListView) view.findViewById(R.id.listview_particular);
        clear_spesa = (Button) view.findViewById(R.id.clear_spesa);
        spesa_completed = (Button) view.findViewById(R.id.spesa_completed);
        com.example.spesa.DatabaseHandler db = new com.example.spesa.DatabaseHandler(getActivity());
        com.example.spesa.DB_Part_Handler db_part = new com.example.spesa.DB_Part_Handler(getActivity());

        // LISTVIEW CONTACTS
        List<String> contact_list = new ArrayList<String>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, contact_list);
        listview_general.setAdapter(arrayAdapter);
        //Contact ale = new Contact("Ale","12345678");
        //db.addContact(ale);
        List<Contact> contacts = db.getAllContacts();
        for (com.example.spesa.Contact cn : contacts) {
            contact_list.add(cn.getName());
        }
        arrayAdapter.notifyDataSetChanged();

        // LISTVIEW FOOD
        List<String> food_list = new ArrayList<String>();
        ArrayAdapter<String> arrayAdapter_part = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, food_list);
        listview_particular.setAdapter(arrayAdapter_part);
        // Food pasta = new Food(0,"Pasta");
        // db_part.addFood(pasta);
        List<com.example.spesa.Food> foods = db_part.getAllFood();
        for (com.example.spesa.Food fd : foods) {
            food_list.add(fd.getName());
        }
        arrayAdapter_part.notifyDataSetChanged();


        //FUNZIONE PER SCRIVERE NOMI CLICCATI DELLA LISTA
        listview_general.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String name = String.valueOf(parent.getItemAtPosition(position));
                        db_part.addFood(new Food(name));
                        // AGGIORNO LISTA
                        List<Food> foods = db_part.getAllFood();
                        food_list.clear();
                        for (Food fd : foods) {
                            food_list.add(fd.getName());
                        }
                        arrayAdapter_part.notifyDataSetChanged();

                        String food_touched = String.valueOf(parent.getItemAtPosition(position)) + " added";
                        Toast.makeText(getActivity(), food_touched, Toast.LENGTH_SHORT).show();

                    }
                }
        );
        listview_general.setOnItemLongClickListener(
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    PopupMenu popup = new PopupMenu(getActivity(),view);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.delete_el:
                                    Toast.makeText(getActivity(), "Elemento eliminato", Toast.LENGTH_SHORT).show();

                                    String name_contact = String.valueOf(parent.getItemAtPosition(position));
                                    int id_contact = 0;
                                    String number_contact = "";
                                    List<com.example.spesa.Contact> contacts = db.getAllContacts();
                                    for (com.example.spesa.Contact cn : contacts) {
                                        if (cn.getName().equals(name_contact)) {
                                            // Writing Contacts to log
                                            id_contact = cn.getID();
                                            number_contact = cn.getPhoneNumber();
                                            break;
                                        }
                                    }
                                    db.deleteContact(new com.example.spesa.Contact(id_contact,name_contact, number_contact));

                                    // AGGIORNO LISTA
                                    contacts = db.getAllContacts();
                                    contact_list.clear();
                                    for (com.example.spesa.Contact cn : contacts) {
                                        contact_list.add(cn.getName());
                                    }
                                    arrayAdapter.notifyDataSetChanged();


                                    return true;
                                case R.id.delete_all:
                                    Toast.makeText(getActivity(), "Lista cancellata", Toast.LENGTH_SHORT).show();

                                    contacts = db.getAllContacts();
                                    for (com.example.spesa.Contact cn : contacts) {
                                            id_contact = cn.getID();
                                            name_contact = cn.getName();
                                            number_contact = cn.getPhoneNumber();
                                            db.deleteContact(new com.example.spesa.Contact(id_contact, name_contact, name_contact));
                                        }

                                    // AGGIORNO LISTA
                                    contacts = db.getAllContacts();
                                    contact_list.clear();
                                    for (com.example.spesa.Contact cn : contacts) {
                                        contact_list.add(cn.getName());
                                    }
                                    arrayAdapter.notifyDataSetChanged();

                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popup.inflate(R.menu.menu_item_spesa);
                    popup.show();
                    return true;
                }
            }
        );

        listview_particular.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                if (parent.getChildAt(position).getBackground() == Color.WHITE) {
                    Log.d(TAG,"Elemento deselezionato");
                    parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                } else {
                    Log.d(TAG,"Elemento selezionato");
                    parent.getChildAt(position).setBackgroundColor(Color.BLUE);
                }
                */

                Log.d(TAG,"Elemento selezionato");
                Toast.makeText(getActivity(), "Elemento selezionato", Toast.LENGTH_SHORT).show();
                parent.getChildAt(position).setBackgroundColor(Color.BLUE);

                return true;
            }
        });

        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to fragment 1", Toast.LENGTH_SHORT).show();

                ((com.example.spesa.MainActivity)getActivity()).setViewPager(0);
            }
        });
        btnNavFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to fragment 2", Toast.LENGTH_SHORT).show();

                ((com.example.spesa.MainActivity)getActivity()).setViewPager(1);
            }
        });
        btnNavFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to fragment 3", Toast.LENGTH_SHORT).show();

                ((com.example.spesa.MainActivity)getActivity()).setViewPager(2);
            }
        });
        add_food_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inserting Contacts
                Log.d("Insert: ", "Inserting ..");
                String name_contact = edit_name.getText().toString();
                String number_contact = "1";
                db.addContact(new com.example.spesa.Contact(name_contact, number_contact));

                // AGGIORNO LISTA
                List<com.example.spesa.Contact> contacts = db.getAllContacts();
                contact_list.clear();
                for (com.example.spesa.Contact cn : contacts) {
                    contact_list.add(cn.getName());
                }
                arrayAdapter.notifyDataSetChanged();
                edit_name.setText("");
            }

        });
        clear_spesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Food> foods = db_part.getAllFood();
                for (Food fd : foods) {
                    int id_contact = fd.getId();
                    String name = fd.getName();
                    db_part.deleteFood(new Food(id_contact, name));
                }

                // AGGIORNO LISTA
                foods = db_part.getAllFood();
                food_list.clear();
                for (Food fd : foods) {
                    food_list.add(fd.getName());
                }
                arrayAdapter_part.notifyDataSetChanged();

                Toast.makeText(getActivity(), "Spesa cancellata", Toast.LENGTH_SHORT).show();
            }
        });

        spesa_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}



