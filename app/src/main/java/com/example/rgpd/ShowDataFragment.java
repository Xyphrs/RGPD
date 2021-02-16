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
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowDataFragment extends Fragment {

    FragmentShowDataBinding binding;
    FormFragment formFragment;
    Button edit;
    FirebaseFirestore db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentShowDataBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = this.getArguments();
        if (bundle != null){
            String vNombre = bundle.getString("Nombre");
            String vEmail = bundle.getString("Email");
            String vTelefono = bundle.getString("Telefono");
            String vFecha = bundle.getString("Fecha");
            String vAuthFoto = bundle.getString("Authfoto");
            String vAuthComunicado = bundle.getString("Authcomunicado");

            System.out.println("Bundle isn't null");

            binding.nombredata.setText(vNombre);
            binding.emaildata.setText(vEmail);
            binding.telefonodata.setText(vTelefono);
            binding.fechadata.setText(vFecha);
            binding.authfotodata.setText(vAuthFoto);
            binding.authcomunicadodata.setText(vAuthComunicado);
        }
        System.out.println("Bundle is null");
    }
}