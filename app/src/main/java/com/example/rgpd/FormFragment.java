package com.example.rgpd;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.rgpd.Pickers.DatePickerFragment;
import com.example.rgpd.Pickers.TimePickerFragment;
import com.example.rgpd.databinding.FragmentFormBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormFragment extends Fragment {

    FragmentFormBinding binding;
    NavController navController;
    EditText nombre;
    EditText email;
    EditText telefono;
    EditText fecha;
    EditText hora;
    CheckBox authfoto;
    CheckBox authcomunicado;
    Button enviar;
    FirebaseFirestore db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        db = FirebaseFirestore.getInstance();

        nombre = view.findViewById(R.id.nombre);
        email = view.findViewById(R.id.email);
        telefono = view.findViewById(R.id.telefono);
        fecha = view.findViewById(R.id.fecha);
        hora = view.findViewById(R.id.hora);
        authfoto = view.findViewById(R.id.authfoto);
        authcomunicado = view.findViewById(R.id.authcomunicado);
        enviar = view.findViewById(R.id.enviar);

        fecha.setOnClickListener(v -> DatePicker());
        hora.setOnClickListener(v -> TimePicker());

        enviar.setOnClickListener(v -> {
            CreateData();
            navController.navigate(R.id.action_formFragment_to_showDataFragment);
        });
    }

    private void CreateData() {
        String vNombre = nombre.getText().toString();
        String vEmail = email.getText().toString();
        String vTelefono = telefono.getText().toString();

        String vDia = fecha.getText().toString();
        String vHora = hora.getText().toString();
        String vFecha = vDia + ", " + vHora;

        String vAuthFoto = String.valueOf(authfoto.isChecked());
        String vAuthComunicado = String.valueOf(authcomunicado.isChecked());

        Map<String, String> map = new HashMap<>();
        map.put("Nombre", vNombre);
        map.put("Email", vEmail);
        map.put("Telefono", vTelefono);
        map.put("Fecha", vFecha);
        map.put("Authfoto", vAuthFoto);
        map.put("Authcomunicado", vAuthComunicado);
        db.collection("test").add(map)
                .addOnSuccessListener(documentReference -> Toast.makeText(getContext(), "Datos enviados", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Los datos no se pueden enviar", Toast.LENGTH_SHORT).show());

        Bundle bundle = new Bundle();
        bundle.putString("Nombre", vNombre);
        bundle.putString("Email", vEmail);
        bundle.putString("Telefono", vTelefono);
        bundle.putString("Fecha", vFecha);
        bundle.putString("Authfoto", vAuthFoto);
        bundle.putString("Authcomunicado", vAuthComunicado);

        ShowDataFragment showDataFragment = new ShowDataFragment();
        showDataFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().commit();
    }

    private void TimePicker() {
        @SuppressLint("DefaultLocale")
        TimePickerFragment newFragment = TimePickerFragment.newInstance((view, hourOfDay, minute) -> {
            hora.setText(String.format("%02d:%02d", hourOfDay, minute));
        });


        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    private void DatePicker() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = day + " / " + (month+1) + " / " + year;
            fecha.setText(selectedDate);
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}