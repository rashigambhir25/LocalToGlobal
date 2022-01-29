package com.example.localtoglobal.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.localtoglobal.R;
import com.example.localtoglobal.recommendationRetro.HomeFragment;
import com.example.localtoglobal.search.SearchProductList;
import com.example.localtoglobal.userRetro.MyProfileFragment;
import com.example.localtoglobal.cartRetro.CartFragment;
import com.example.localtoglobal.categoryRetro.CategoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        HomeFragment homeFragment = new HomeFragment();
        CartFragment cartFragment = new CartFragment();
        MyProfileFragment myProfileFragmentFragment = new MyProfileFragment();
        CategoryFragment categoryFragment = new CategoryFragment();
        setCurrentFragment(homeFragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navi);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.cart:
                    setCurrentFragment(cartFragment);
                    break;

                case R.id.my_profile:
                    setCurrentFragment(myProfileFragmentFragment);
                    break;
                case R.id.category:
                    setCurrentFragment(categoryFragment);
                    break;

                default:
                    setCurrentFragment(homeFragment);
                    break;
            }
            return true;
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.Fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent i = new Intent(getApplicationContext(), SearchProductList.class);
                i.putExtra("query", s);
                startActivity(i);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }
}