package me.dvit.tazaapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    WebView webView;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Language languageClass = new Language();
    String Language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        languageClass.getLanguage(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        boolean test =  hasActiveInternetConnection(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }
    @SuppressWarnings("StatementWithEmptyBody")
       public boolean hasActiveInternetConnection(Context context) {
        if (isNetworkAvailable()) {
            webView.loadUrl(URLDecoder.decode(this.getString(R.string.home_url)));
        } else {
            webView.setBackgroundColor(0);
            webView.setBackgroundResource(R.drawable.no_internet_connection);
        }
        return false;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else  if (webView.canGoBack()) {
            webView.goBack();
    }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
       if (id == R.id.home) {
            if (isNetworkAvailable()) {
                webView.loadUrl(URLDecoder.decode(this.getString(R.string.home_url)));

            } else {
                Toast.makeText(this , R.string.no_internet_text , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.fruits) {
            if (isNetworkAvailable()) {
                webView.loadUrl(URLDecoder.decode(this.getString(R.string.fruits_url)));

            } else {
                Toast.makeText(this ,  R.string.no_internet_text  , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.vegtables) {
            if (isNetworkAvailable()) {
                webView.loadUrl(URLDecoder.decode(this.getString(R.string.veg_url)));

            } else {
                Toast.makeText(this ,  R.string.no_internet_text  , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.ready_vegtables) {
            if (isNetworkAvailable()) {
                webView.loadUrl(URLDecoder.decode(this.getString(R.string.ready_vegtables_url)));

            } else {
                Toast.makeText(this ,  R.string.no_internet_text  , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.offer) {
            if (isNetworkAvailable()) {
                webView.loadUrl(URLDecoder.decode(this.getString(R.string.offer_url)));

            } else {
                Toast.makeText(this ,  R.string.no_internet_text  , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.cotact) {
            if (isNetworkAvailable()) {
                webView.loadUrl(URLDecoder.decode(this.getString(R.string.contact_url)));

            } else {
                Toast.makeText(this ,  R.string.no_internet_text , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.arabic) {
            if (checkLanguage().equalsIgnoreCase("en")) {
                languageClass.setLanguage(webView, "ar");
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.activity_main_drawer);
                refreshApplication();
            }
            else {
                languageClass.setLanguage(webView, "en");
                refreshApplication();
            }
            if (isNetworkAvailable()) {

                webView.loadUrl(URLDecoder.decode(this.getString(R.string.home_url)));
            } else {
                Toast.makeText(this ,  R.string.no_internet_text  , Toast.LENGTH_LONG).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private String checkLanguage() {
        Language = languageClass.getLanguage(getApplicationContext());
        return Language;
    }
    private void refreshApplication() {
        this.finish();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}