package com.vishal.cinemate.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vishal.cinemate.R;

public class MainMenuActivity extends AppCompatActivity {
    private AppBarLayout main_appBarLayout;
    private Toolbar main_toolbar;
    private NavHostFragment navHostFragment;
    private NavController navController;
    private BottomNavigationView main_bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        initialize();
        setListener();
    }

    private void setListener() {
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                main_appBarLayout.setExpanded(true,true);
            }
        });
    }

    private void initialize() {
        main_appBarLayout = findViewById(R.id.main_appBarLayout);
        main_toolbar = findViewById(R.id.main_toolbar);
        main_bottomNavView = findViewById(R.id.main_bottomNavView);

        setSupportActionBar(main_toolbar);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_navHostFragment);
        navController = navHostFragment.getNavController();
        //this is for setting the name
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.nowPlayingFragment, R.id.upcomingFragment).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(main_bottomNavView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.socialmedia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_git:
                openUrl("https://github.com/Vishallab");
                return true;
            case R.id.menu_instagram:
                openUrl("https://www.instagram.com/ig_viishal/");
                return true;
            case R.id.menu_linkedin:
                openUrl("https://www.linkedin.com/in/vishalmishra01/");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openUrl(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
