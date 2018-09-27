package bt.unicamp.ft.m183711_v188110.vista_me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.fragments.LoginFragment;
import bt.unicamp.ft.m183711_v188110.vista_me.fragments.ProductsFragment;
import bt.unicamp.ft.m183711_v188110.vista_me.fragments.RegisterFragment;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.BuyItemEventListener;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.LoginListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentManagerActivity,
        BuyItemEventListener,LoginListener {


    private android.support.v4.app.FragmentManager fragmentManager;
    private ProductsFragment productsFragment;
    private Login login;
    private MenuItem loginButton;
    private MenuItem registerButton;
    private MenuItem myOrderButton;
    private MenuItem loggoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        productsFragment = new ProductsFragment(new ArrayList(Arrays.asList(Products.products)),this,this);
        OpenFragment(productsFragment,"Products",false);

        loginButton = navigationView.getMenu().findItem(R.id.login);
        registerButton = navigationView.getMenu().findItem(R.id.register);
        myOrderButton = navigationView.getMenu().findItem(R.id.myOrders);
        loggoutButton = navigationView.getMenu().findItem(R.id.loggout);
        login = new Login(this);
        changeLoginStatus();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.login) {

            LoginFragment loginFragment = new LoginFragment(login,this);
            OpenFragment(loginFragment,"Login",true);

        } else if (id == R.id.register) {
            RegisterFragment registerFragment = new RegisterFragment(login,this);
            OpenFragment(registerFragment,"register",true);
        } else if (id == R.id.myOrders) {

        }else if (id == R.id.loggout){
            if(login != null)
            login.loggout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void OpenFragment(Fragment f, String tag,boolean addToBackStack) {
        FragmentTransaction ftrans = fragmentManager.beginTransaction();
        ftrans.replace(R.id.frame, f, tag);
        if(addToBackStack)
            ftrans.addToBackStack(tag);
        ftrans.commit();
    }

    @Override
    public void RemoveFragment(Fragment f) {
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.remove(f);
        trans.commit();
        fragmentManager.popBackStack();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    @Override
    public void BuyItem(Object obj) {
        Product p = (Product) obj;

        Toast.makeText(getApplicationContext(),"O produto '"+p.getNome()+"' foi adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeLoginStatus() {
        loginButton.setVisible(!login.isLogged());
        registerButton.setVisible(!login.isLogged());
        myOrderButton.setVisible(login.isLogged());
        loggoutButton.setVisible(login.isLogged());
    }
}

