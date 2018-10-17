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
        languageClass.getLanguage(getApplicationContext()); // get language from shared prefs and set it to activity
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
    public boolean hasActiveInternetConnection(Context context) {
        if (isNetworkAvailable()) {
            webView.loadUrl("http://tazaegy.ew2s.com/en/mobilesplashar/");
            webView.setBackgroundResource(R.drawable.no_internet_connection);

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.home) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/en/mobilesplashen/");
            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();

            }
        }
        else if (id == R.id.about) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/en/about-us/");
            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.fruits) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/en/fruits/");
            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.vegtables) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/en/vegetables/");
            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.vegtables2) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/en/product-category/vegetables-ready/");
            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.offer) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/en/offer/");
            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.cotact) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/en/contact-us/");
            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.language) {
            if (checkLanguage().equalsIgnoreCase("en")) {
                languageClass.setLanguage(webView, "ar");
                refreshApplication();
//                ArabicBtn.setText("العربية");

            }
            else {
                languageClass.setLanguage(webView, "ar");
                refreshApplication();
//                ArabicBtn.setText("English");

            }
            if (isNetworkAvailable()) {
                navigationView.getMenu().clear();
                //navigationView.inflateMenu(R.menu.activity_main_drawer);
                webView.loadUrl("http://tazaegy.ew2s.com/en/mobilesplashar/");
            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.activity_main_drawer);

            }
        }
        else if (id == R.id.home1) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/en/mobilesplashar/");
            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.about1) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/about-us/");

            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.fruits1) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/%D8%A7%D9%84%D9%81%D8%A7%D9%83%D9%87%D9%87/");

            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.vegtables1) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/%D8%A7%D9%84%D8%AE%D8%B6%D8%B1%D9%88%D8%A7%D8%AA/");

            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.vegtables21) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/product-category/%D8%AE%D8%B6%D8%B1%D8%A7%D9%88%D8%A7%D8%AA-%D9%85%D8%B9%D9%84%D8%A8%D8%A9/");

            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();

            }
        }
        else if (id == R.id.offer1) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/offer/");

            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();

            }
        }
        else if (id == R.id.cotact1) {
            if (isNetworkAvailable()) {
                webView.loadUrl("http://tazaegy.ew2s.com/contact-us/");

            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();

            }
        }
        else if (id == R.id.language1) {
            if (isNetworkAvailable()) {
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.english_menu);
                webView.loadUrl("http://tazaegy.ew2s.com/en/mobilesplashen/");

            } else {
                Toast.makeText(this , "Oooops ! You lost your Internet connection please reconnect and try again" , Toast.LENGTH_LONG).show();
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.english_menu);
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
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
    }
}