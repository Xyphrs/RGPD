package com.example.rgpd.Form;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.rgpd.Pickers.DatePickerFragment;
import com.example.rgpd.Pickers.TimePickerFragment;
import com.example.rgpd.R;
import com.example.rgpd.databinding.FragmentFormBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormFragment extends Fragment {

    FragmentFormBinding binding;
    NavController navController;
    FirebaseFirestore db;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentFormBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        db = FirebaseFirestore.getInstance();

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding.fecha.setOnClickListener(v -> DatePicker());
        binding.hora.setOnClickListener(v -> TimePicker());

        binding.enviar.setOnClickListener(v -> {
            CreateData();
            SystemClock.sleep(1000);
            navController.navigate(R.id.action_formFragment_to_showDataFragment);
        });


    }

    private void CreateData() {
        String vNombre = binding.nombre.getText().toString();
        String vEmail = binding.email.getText().toString();
        String vTelefono = binding.telefono.getText().toString();

        String vDia = binding.fecha.getText().toString();
        String vHora = binding.hora.getText().toString();
        String vFecha = vDia + ", " + vHora;

        String vAuthFoto = String.valueOf(binding.authfoto.isChecked());
        String vAuthComunicado = String.valueOf(binding.authcomunicado.isChecked());

        Map<String, String> map = new HashMap<>();
        map.put("Nombre", vNombre);
        map.put("Email", vEmail);
        map.put("Telefono", vTelefono);
        map.put("Fecha", vFecha);
        map.put("Authfoto", vAuthFoto);
        map.put("Authcomunicado", vAuthComunicado);

        db.collection("test").add(map)
                .addOnSuccessListener(documentReference -> {
                    mainViewModel.testId = documentReference.getId();
                    Toast.makeText(getContext(), "Datos enviados", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Los datos no se pueden enviar", Toast.LENGTH_SHORT).show());
    }

    private void TimePicker() {
        @SuppressLint("DefaultLocale")
        TimePickerFragment newFragment = TimePickerFragment.newInstance((view, hourOfDay, minute) -> {
            binding.hora.setText(String.format("%02d:%02d", hourOfDay, minute));
        });


        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    private void DatePicker() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = day + " / " + (month+1) + " / " + year;
            binding.fecha.setText(selectedDate);
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}