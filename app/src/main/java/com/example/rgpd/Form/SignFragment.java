package com.example.rgpd.Form;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rgpd.Form.MainViewModel;
import com.example.rgpd.R;
import com.example.rgpd.databinding.FragmentPrivacyBinding;
import com.example.rgpd.databinding.FragmentSignBinding;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignFragment extends Fragment {

    FragmentSignBinding binding;
    FirebaseFirestore db;
    private MainViewModel mainViewModel;
    NavController navController;
    Bitmap signature;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSignBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        navController = Navigation.findNavController(view);

        binding.limpiar.setOnClickListener(v -> binding.signaturePad.clear());
        binding.next.setOnClickListener(v -> {
            signature = binding.signaturePad.getTransparentSignatureBitmap();
            Toast.makeText(getContext(), "Firma Aceptada", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.action_signFragment_to_privacyFragment);
            mainViewModel.signature = signature;

        });

        binding.cancel.setOnClickListener(v -> {
            Toast.makeText(getContext(), "No has Firmado", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.action_signFragment_to_privacyFragment);
        });
    }
}