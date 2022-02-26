package com.example.adoptatumascota;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adoptatumascota.databinding.ActivityMenuAdoptanteBinding;

public class MenuAdoptanteActivity extends AppCompatActivity {

    EditText txtprueba;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuAdoptanteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuAdoptanteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String prueba= "Protector";
        String usuario= getIntent().getStringExtra("usuario");
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

        isAdoptante(navigationView,prueba);

    }
    public void isAdoptante(NavigationView navigationView, String usuario){
        if (usuario =="Adoptante"){
            Menu menuNav= navigationView.getMenu();
            MenuItem nav_item2 = menuNav.findItem(R.id.agregarAnuncioFragment);
            MenuItem nav_item3 = menuNav.findItem(R.id.misAnunciosFragment);
            nav_item2.setVisible(false);
            nav_item3.setVisible(false);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_adoptante);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}