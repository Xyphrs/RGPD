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

import com.example.rgpd.Form.MainViewModel;
import com.example.rgpd.R;
import com.example.rgpd.databinding.FragmentPrivacyBinding;
import com.google.firebase.firestore.FirebaseFirestore;

public class PrivacyFragment extends Fragment {
    FragmentPrivacyBinding binding;
    FirebaseFirestore db;
    private MainViewModel mainViewModel;
    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentPrivacyBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = Navigation.findNavController(view);

        binding.aceptar.setOnClickListener(v -> navController.navigate(R.id.action_privacyFragment_to_signFragment));
        binding.cancelar.setOnClickListener(v -> navController.navigate(R.id.action_privacyFragment_to_formFragment));

    }
}