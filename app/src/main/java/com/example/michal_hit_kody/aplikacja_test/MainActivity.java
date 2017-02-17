package com.example.michal_hit_kody.aplikacja_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button potwierdz;
    Button wyczysc;
    Button strona;
    EditText login;
    EditText haslo;
    TextView napis_haslo;
    TextView napis_user;
    TextView konto;
    TextView przypomnienie;
    private AdView mAdView;

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //mAdView = (AdView) findViewById(R.id.reklama);
       // AdRequest adRequest = new AdRequest.Builder()
      //          .build();
      //  mAdView.loadAd(adRequest);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

       NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
       navigationView.setNavigationItemSelectedListener(this);

        potwierdz = (Button) findViewById(R.id.button);
        wyczysc = (Button) findViewById(R.id.button2);
        login = (EditText) findViewById(R.id.editText);
        haslo = (EditText) findViewById(R.id.editText2);
        napis_haslo = (TextView) findViewById(R.id.textView7);
        napis_user = (TextView) findViewById(R.id.textView6);
        konto = (TextView) findViewById(R.id.textView2);
        przypomnienie = (TextView) findViewById(R.id.textView4);


        potwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent c = new Intent(MainActivity.this, Pierwsza.class);
                startActivity(c);
            }
        });

        wyczysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                napis_haslo.setVisibility(view.VISIBLE);
                napis_user.setVisibility(view.VISIBLE);
                login.setText("");
                haslo.setText("");

            }
        });

        login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String empty;
                empty = haslo.getText().toString();
                if(empty.equals(""))
                {
                    napis_haslo.setVisibility(view.VISIBLE);
                }
                napis_user.setVisibility(view.INVISIBLE);
                return false;
            }
        });


        haslo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String empty;
                empty = login.getText().toString();
                if (empty.equals(""))
                {
                    napis_user.setVisibility(view.VISIBLE);
                }
                napis_haslo.setVisibility(view.INVISIBLE);
                return false;
            }
        });




        konto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Tu będzie przypomnienie hasła");
            }
        });

        przypomnienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Tu będzie tworzenie konta");
            }
        });



    }
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
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

        } else if (id == R.id.nav_share) {

            Uri uri = Uri.parse("http://www.onet.pl"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        } else if (id == R.id.nav_send) {
            Uri uri = Uri.parse("http://www.interia.pl"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        } else if (id == R.id.nev_manage1) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
