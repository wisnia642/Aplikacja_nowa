package com.example.michal_hit_kody.aplikacja_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class Pierwsza extends AppCompatActivity {

    Button dalej;
    Button clean;
    Button wstecz;
    AutoCompleteTextView dane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pierwsza);

        dalej = (Button) findViewById(R.id.button3);
        clean = (Button) findViewById(R.id.button5);
        wstecz = (Button) findViewById(R.id.button4);
        dane = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // przejście do następnego layout'u
            }
        });

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dane.setText("");
            }
        });

        wstecz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(Pierwsza.this, MainActivity.class);
                startActivity(c);
            }
        });
    }
}
