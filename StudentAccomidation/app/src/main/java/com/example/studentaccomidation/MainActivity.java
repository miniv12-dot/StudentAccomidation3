package com.example.studentaccomidation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.studentaccomidation.databinding.ActivityMainBinding;
import com.example.studentaccomidation.fragments.NotificationsFragment;
import com.example.studentaccomidation.fragments.FavouriteListFragment;
import com.example.studentaccomidation.fragments.HomeFragment;
import com.example.studentaccomidation.fragments.ProfileFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        showHomeFragment();
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId= menuItem.getItemId();
                if (itemId == R.id.item_home){
                    showHomeFragment();

                }else if (itemId == R.id.item_notifications){
                    notificationsFragment();

                }else if (itemId == R.id.item_favourite){
                    showFavouriteListFragment();

                }else if (itemId == R.id.item_profile){
                    showProfileFragment();

                }
                return true;
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void showHomeFragment(){
        binding.toolbarTitleTv.setText("Home");
        HomeFragment homeFragment= new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.fragmentsFl.getId(), homeFragment,"HomeFragment");
        fragmentTransaction.commit();

    }
    private void notificationsFragment(){
        binding.toolbarTitleTv.setText("Notifications");
        NotificationsFragment notificationsFragment= new NotificationsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.fragmentsFl.getId(), notificationsFragment,"notificationsFragment");
        fragmentTransaction.commit();

    }
    private void showFavouriteListFragment(){
        binding.toolbarTitleTv.setText("Favourite");
        FavouriteListFragment favouriteListFragment= new FavouriteListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.fragmentsFl.getId(), favouriteListFragment,"FavoriteListFragment");
        fragmentTransaction.commit();

    }
    private void showProfileFragment(){
        binding.toolbarTitleTv.setText("Profile");
        ProfileFragment profileFragment= new ProfileFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.fragmentsFl.getId(), profileFragment,"ProfileFragment");
        fragmentTransaction.commit();

    }
}
