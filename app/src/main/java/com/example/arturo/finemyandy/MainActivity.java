package com.example.arturo.finemyandy;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email,contraseña;
    private Button btLogout,btLinterna,btVibar,btGPS;

    MediaPlayer sonido1;

    Button btSonido;

    int a=0;
    int b=0;

    android.hardware.Camera camara;

    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sonido1= MediaPlayer.create(this, R.raw.sonido1);

        btVibar=(Button) findViewById(R.id.btVibrar);
        btLinterna=(Button) findViewById(R.id.btLinterna);
        btSonido= (Button)findViewById(R.id.btSonar);
        btGPS=(Button)findViewById(R.id.btGPS);



        final Vibrator vibradorr=(Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        btVibar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibradorr.vibrate(800);
            }
        });
        btGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create class object
                gps = new GPSTracker(MainActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "tu localizacion es - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }


        });

        btLinterna.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                if (a==0){
                    camara= android.hardware.Camera.open();
                    android.hardware.Camera.Parameters parameters=camara.getParameters();
                    parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                    camara.setParameters(parameters);
                    camara.startPreview();
                    a=1;
                }else {
                    android.hardware.Camera.Parameters parameters=camara.getParameters();
                    parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
                    camara.setParameters(parameters);
                    camara.startPreview();
                    camara.release();
                    a=0;
                }
            }
        });
        btSonido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sonido1.start();
            }
        });
    }
}
