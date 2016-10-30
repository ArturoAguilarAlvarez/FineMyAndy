package com.example.arturo.finemyandy;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText email,contraseña;
    private Button btLogout,btLinterna,btVibar,btgps;

    MediaPlayer sonido1;

    Button btSonido;

    int a=0;
    int b=0;

    android.hardware.Camera camara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
