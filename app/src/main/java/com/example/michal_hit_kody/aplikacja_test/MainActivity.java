package com.example.michal_hit_kody.aplikacja_test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static ResultSet rs;
    static Statement st;
    PreparedStatement ps;
    FileInputStream fis = null;
    Connection connection = null;

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

    String[] user = new String[15];
    String[] pass = new String[15];
    String[] id = new String[15];
    String[] stan = new String[15];

    String hash1,user_spr,pass_spr;
    int x=0,Warunek_do_przejścia=0;

    private static final String SAMPLE_DB_NAME = "Baza";
    private static final String SAMPLE_TABLE_NAME = "Karta";

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();
    }

/*

    //tworzenie polaczenia z baza danych
    public void connect()
    {
        String url="jdbc:mysql://91.203.133.34:3306/regatzo_app";
        String user="regatzo_admin";
        String pass="Regatzo123";

       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            showToast("brak polaczenia z internetem" + e);
            Log.i("aaa",String.valueOf(e));
            return;
        }

        try {
            connection = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            showToast("brak polaczenia z internetem" + e);
            Log.i("aaa",String.valueOf(e));
            return;
        }

    }
*/

    private void ToDataBase() {
        try {
            SQLiteDatabase sampleDB = this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
            sampleDB.execSQL("CREATE TABLE IF NOT EXISTS users (Id VARCHAR,Uzytkownik VARCHAR,Haslo VARCHAR,Email VARCHAR,Stan VARCHAR);");
        } catch (Exception e) {
        }

    }

    private void Hash() {
        try {
            String input = haslo.getText().toString();
            hash1 = "%032x440472108104" + String.valueOf(input.hashCode());

        } catch (Exception e) {
        }
    }


    public void InsertLoginDataSqligt() {
        ToDataBase();

        try {
            SQLiteDatabase sampleDB = this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null); //%032x44047210810492668751
            sampleDB.execSQL("INSERT INTO users (Id,Uzytkownik,Haslo,Email,Stan) VALUES ('0','admin','%032x44047210810492668751','','0') ");
            sampleDB.close();
        } catch (Exception e) {
            showToast(""+e);
        }
    }
/*

    public void InsertLoginDataMysql() {
        connect();
        if (connection != null) {
            try {
                st = connection.createStatement();
            } catch (SQLException e1) {
                //e1.printStackTrace();
            }


            String sql1 = "INSERT INTO Logowanie (Id,Uzytkownik,Haslo,Sala_sprzedazy,Magazyn,Kuchnia,Wszystko) VALUES" +
                    " (?,?,?,?,?,?,?)";

            try {
                ps = connection.prepareStatement(sql1);
                ps.setString(1, "0");
                ps.setString(2, "admin");
                ps.setString(3, "%032x44047210810492668751");
                ps.setString(4, "1");
                ps.setString(5, "1");
                ps.setString(6, "1");
                ps.setString(7, "1");
                ps.executeUpdate();
                connection.commit();

            } catch (SQLException e) {

            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                showToast("brak połączenia z internetem");
            }
        }
    }
*/

    public void UpdateSql1() {
        ToDataBase();

        try {
            SQLiteDatabase sampleDB = this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
            sampleDB.execSQL("UPDATE users SET Stan=('1') WHERE Uzytkownik='" + login + "'");
            sampleDB.close();
        } catch (Exception e) {

        }

/*
        connect();
        if (connection != null) {
            try {
                st = connection.createStatement();
            } catch (SQLException e1) {
                //e1.printStackTrace();
            }


            String sql1 = "UPDATE logowanie SET Stan=('true') WHERE Uzytkownik='" + login + "'";

            try {
                st.executeUpdate(sql1);
            } catch (SQLException e1) {
                // e1.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                showToast("brak połączenia z internetem");
            }
        }*/
    }

    private void Read_Login_SqlLigt() {
        x = 0;
        ToDataBase();

        SQLiteDatabase sampleDB = this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);

        try {
            Cursor c = sampleDB.rawQuery("select * from users", null);

            while (c.moveToNext()) {
                String zm = String.valueOf(c.getString(1));
                if (zm != null) {
                    id[x] = String.valueOf(c.getString(0));
                    user[x] = String.valueOf(c.getString(1));
                    pass[x] = String.valueOf(c.getString(2));
                    stan[x] = String.valueOf(c.getString(3));

                    x++;
                }
            }
            sampleDB.close();
        } catch (Exception e) {

        }

    }
/*
    public void Read_Login_MySql() {
        x = 0;
        connect();
        if (connection != null) {

            try {
                st = connection.createStatement();
            } catch (SQLException e1) {
                e1.printStackTrace();
                Log.i("myTag", "1" + e1);
            }

            String sql = ("select * from logowanie");

            try {
                rs = st.executeQuery(sql);
            } catch (SQLException e1) {
                //  e1.printStackTrace();
                Log.i("myTag", "2" + e1);
            }
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String zm = rs.getString("Uzytkownik");
                    if (zm != null) {
                        uzytkonkik[x] = rs.getString("Uzytkownik");
                        // showToast(uzytkonkik[x]);
                        haslo[x] = rs.getString("Haslo");
                        sala_sprzedazy[x] = rs.getString("Sala_sprzedazy");
                        magazyn[x] = rs.getString("Magazyn");
                        kuchnia[x] = rs.getString("Kuchnia");
                        wszystko[x] = rs.getString("Wszystko");
                        x++;
                    }

                }
            } catch (SQLException e1) {
                e1.printStackTrace();
                Log.i("myTag", "3" + e1);
            }

            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                Log.i("myTag", "4" + se);
                showToast("brak polaczenia z internetem");
            }

        }

    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Włączanie reklam
        //mAdView = (AdView) findViewById(R.id.reklama);
       // AdRequest adRequest = new AdRequest.Builder()
      //          .build();
      //  mAdView.loadAd(adRequest);


            Read_Login_SqlLigt();
        if (user[0] == null) {
              showToast("brakuje danych");

            //odczytywanie danych logowania SQLight i tworzenie konta
            InsertLoginDataSqligt();
            Read_Login_SqlLigt();
        }

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

                try {
                    Hash();
                    user_spr = login.getText().toString();
                    for (int i = 0; i < user.length; i = i + 0) {
                        if (user_spr.equals(user[i])) {
                            if (hash1.equals(pass[i])) {
                                UpdateSql1();

                                Intent c = new Intent(MainActivity.this, Pierwsza.class);
                                startActivity(c);

                                Warunek_do_przejścia=1;
                            }
                        }
                        i++;
                    }

                    if (TextUtils.isEmpty(login.getText().toString()) & TextUtils.isEmpty(haslo.getText().toString())) {

                        showToast("Uzupełnij wszystkie pola");
                    } else if (Warunek_do_przejścia == 0) {
                        showToast("Błędny login lub hasło");
                    }
                } catch (Exception e) {
                    showToast("błąd"+e);
                }


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

                Intent c = new Intent(MainActivity.this, Przyp_pass.class);
                startActivity(c);
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
      //  getMenuInflater().inflate(R.menu.main, menu);
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
