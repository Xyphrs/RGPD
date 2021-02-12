package com.example.rgpd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import com.google.firebase.firestore.FirebaseFirestore;

public class FormFragment extends Fragment {

    EditText nombre;
    EditText email;
    EditText telefono;
    EditText fecha;
    CheckBox authfoto;
    CheckBox authcomunicado;
    Button enviar;

    FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }
}