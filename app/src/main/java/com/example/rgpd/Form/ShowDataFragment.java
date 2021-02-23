package com.example.rgpd.Form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.rgpd.R;
import com.example.rgpd.databinding.FragmentShowDataBinding;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowDataFragment extends Fragment {

    FragmentShowDataBinding binding;
    FirebaseFirestore db;
    NavController navController;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentShowDataBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        navController = Navigation.findNavController(view);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        if (mainViewModel.testId != null) {
            db.collection("test").document(mainViewModel.testId).addSnapshotListener((value, error) -> {
                binding.nombredata.setText(String.format("Nombre: %s", value.getString("Nombre")));
                binding.dnidata.setText(String.format("DNI: %s", value.getString("DNI")));
                binding.emaildata.setText(String.format("Correo: %s", value.getString("Email")));
                binding.telefonodata.setText(String.format("Telefono: %s", value.getString("Telefono")));
                binding.fechadata.setText(String.format("Fecha: %s", value.getString("Fecha")));
                binding.authfotodata.setText(String.format("Autorizacion de foto: %s", value.getString("Authfoto")));
                binding.authcomunicadodata.setText(String.format("Autorizacion para enviar publicidad: %s", value.getString("Authcomunicado")));
                
                binding.signature.setImageBitmap(mainViewModel.signature);
            });
        }

        binding.editbtn.setOnClickListener(v -> navController.navigate(R.id.action_showDataFragment_to_formFragment));
    }
}