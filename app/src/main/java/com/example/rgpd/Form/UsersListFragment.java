package com.example.rgpd.Form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rgpd.R;
import com.example.rgpd.databinding.FragmentUsersListBinding;
import com.example.rgpd.databinding.ViewholderUsersBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class UsersListFragment extends Fragment {
    FragmentUsersListBinding binding;
    NavController navController;
    FirebaseFirestore db;
    MainViewModel mainViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentUsersListBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        navController = Navigation.findNavController(view);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        mainViewModel.users = new Users(mainViewModel.nombreForm, mainViewModel.dniForm);
        mainViewModel.usersList.add(mainViewModel.users);

        binding.anadir.setOnClickListener(v -> {
            mainViewModel.nombreForm = null;
            mainViewModel.dniForm = null;
            mainViewModel.emailForm = null;
            mainViewModel.telefonoForm = null;
            mainViewModel.diaForm = null;
            mainViewModel.horaForm = null;
            mainViewModel.authFotoForm = false;
            mainViewModel.authComunicadoForm = false;
            mainViewModel.signature = null;
            mainViewModel.testId = null;
            navController.navigate(R.id.action_usersListFragment_to_formFragment);
        });

        UsersAdapter usersAdapter = new UsersAdapter();
        binding.recycler.setAdapter(usersAdapter);

        binding.recycler.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
    }

    class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {

        @NonNull
        @Override
        public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UsersViewHolder(ViewholderUsersBinding.inflate(getLayoutInflater() ,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
            Users users = mainViewModel.usersList.get(position);

            holder.binding.nombre.setText(users.nombre);
            holder.binding.dni.setText(String.valueOf(position + 1));

        }

        @Override
        public int getItemCount() {
            return mainViewModel.usersList == null ? 0 : mainViewModel.usersList.size();
        }
    }

    static class UsersViewHolder extends RecyclerView.ViewHolder {
        ViewholderUsersBinding binding;
        public UsersViewHolder(@NonNull ViewholderUsersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
