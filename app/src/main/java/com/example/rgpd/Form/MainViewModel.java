package com.example.rgpd.Form;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    public String testId;
    public String nombreForm;
    public String dniForm;
    public String emailForm;
    public String telefonoForm;
    public String diaForm;
    public String horaForm;
    public boolean authFotoForm;
    public boolean authComunicadoForm;
    public Bitmap signature;
    public Users users = null;
    public List<Users> usersList = new ArrayList<>();
}
