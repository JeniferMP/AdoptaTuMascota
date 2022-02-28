package com.example.adoptatumascota;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adoptatumascota.clases.Usuario;
import com.example.adoptatumascota.databinding.NavHeaderMenuAdoptanteBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.ContentView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adoptatumascota.databinding.ActivityMenuAdoptanteBinding;

public class MenuAdoptanteActivity extends AppCompatActivity {

    EditText txtprueba;
    TextView txtus, textemail;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuAdoptanteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuAdoptanteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Usuario usuario= (Usuario) getIntent().getSerializableExtra("usuario");
        setSupportActionBar(binding.appBarMenuAdoptante.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.anunciosFragment, R.id.misAnunciosFragment, R.id.agregarAnuncioFragment, R.id.cerrarSesionFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_adoptante);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        isAdoptante(navigationView,usuario.getRol_ID());
        llenadoUsuario(navigationView, usuario.getUsu_nombre(), usuario.getUsu_apellidos(),usuario.getUsu_correo());
    }
    public void isAdoptante(NavigationView navigationView, int rol){
        if (rol ==2){
            Menu menuNav= navigationView.getMenu();
            MenuItem nav_item2 = menuNav.findItem(R.id.agregarAnuncioFragment);
            MenuItem nav_item3 = menuNav.findItem(R.id.misAnunciosFragment);
            nav_item2.setVisible(false);
            nav_item3.setVisible(false);
        }
    }

    public void llenadoUsuario(NavigationView navigationView, String nombre, String apellidos, String correo){
        View vistaHeader = navigationView.getHeaderView(0);

        TextView txt_usuario = vistaHeader.findViewById(R.id.nomusuario);
        TextView txt_correo = vistaHeader.findViewById(R.id.textViewemail);

        txt_usuario.setText(nombre + apellidos);
        txt_correo.setText(correo);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_adoptante);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}