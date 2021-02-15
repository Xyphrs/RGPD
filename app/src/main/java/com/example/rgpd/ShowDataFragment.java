package com.example.rgpd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rgpd.databinding.FragmentShowDataBinding;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ShowDataFragment extends Fragment {

    FragmentShowDataBinding binding;
    FormFragment formFragment;
    TextView nombredata;
    TextView emaildata;
    TextView telefonodata;
    TextView fechadata;
    TextView authfotodata;
    TextView authcomunicadodata;
    Button edit;
    FirebaseFirestore db;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_data, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        nombredata = view.findViewById(R.id.nombredata);
        emaildata = view.findViewById(R.id.emaildata);
        telefonodata = view.findViewById(R.id.telefonodata);
        fechadata = view.findViewById(R.id.fechadata);
        authfotodata = view.findViewById(R.id.authfotodata);
        authcomunicadodata = view.findViewById(R.id.authcomunicadodata);
        edit = view.findViewById(R.id.editbtn);

//        nombredata.setText(String.format("Nombre: %s", m.getString("Nombre")));
//        emaildata.setText(String.format("Nombre: %s", m.getString("Email")));
//        telefonodata.setText(String.format("Nombre: %s", m.getString("Telefono")));
//        fechadata.setText(String.format("Nombre: %s", m.getString("Fecha")));
//        authfotodata.setText(String.format("Nombre: %s", m.getBoolean("Authfoto")));
//        authcomunicadodata.setText(String.format("Nombre: %s", m.getBoolean("Authcomunicado")));
    }
}