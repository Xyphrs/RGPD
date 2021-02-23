package com.example.rgpd.Form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rgpd.R;
import com.example.rgpd.databinding.FragmentFormBinding;
import com.example.rgpd.databinding.FragmentUserSelectionBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public class UserSelectionFragment extends Fragment {
    String dni;
    FragmentUserSelectionBinding binding;
    NavController navController;
    MainViewModel mainViewModel;
    FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentUserSelectionBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        db = FirebaseFirestore.getInstance();


        binding.nuevouser.setOnClickListener(v -> navController.navigate(R.id.action_userSelectionFragment_to_formFragment));

        binding.jugado.setOnClickListener(v -> {
            binding.dniselect.setVisibility(View.VISIBLE);
            binding.ingresar.setVisibility(View.VISIBLE);
        });

        binding.ingresar.setOnClickListener(v -> {
            dni = binding.dniselect.getText().toString();
            System.out.println(dni);
            SystemClock.sleep(1500);
            db.collection("test").whereEqualTo("DNI", dni).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    int count = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document != null) {
                            Toast.makeText(getContext(), "DNI Encontrado", Toast.LENGTH_SHORT).show();
                            count++;
                            break;
                        }
                    }
                    if (count == 0) {
                        Toast.makeText(getContext(), "DNI No Encontrado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}