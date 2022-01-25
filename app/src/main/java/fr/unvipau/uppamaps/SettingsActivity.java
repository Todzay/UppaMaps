package fr.unvipau.uppamaps;

import static fr.unvipau.uppamaps.R.id.textview3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_actvity);

        SharedPreferences sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        RadioGroup group = findViewById(R.id.group);
        RadioGroup group2 = findViewById(R.id.group2);
        RadioButton default_mode = findViewById(R.id.default_mode);
        RadioButton bicycling = findViewById(R.id.bicycling);
        RadioButton car = findViewById(R.id.car);
        RadioButton transit = findViewById(R.id.transit);
        RadioButton french = findViewById(R.id.french);
        RadioButton english = findViewById(R.id.english);
        group.setOnCheckedChangeListener((radioGroup, i) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (default_mode.isChecked()){
                editor.putString("mode","&mode=w");
            }else if (bicycling.isChecked()){
                editor.putString("mode","&mode=b");
            }else if (car.isChecked()){
                editor.putString("mode","&mode=d");
            }else if (transit.isChecked()){
                editor.putString("mode","&mode=r");
            }
            editor.apply();
        });

        switch (sharedPreferences.getString("mode","")){
            case "&mode=w":
                group.check(R.id.default_mode);
                break;
            case "&mode=b":
                group.check(R.id.bicycling);
                break;
            case "&mode=d":
                group.check(R.id.car);
                break;
            case "&mode=r":
                group.check(R.id.transit);
                break;
        }

        group2.setOnCheckedChangeListener((radioGroup, i) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (french.isChecked()){
                editor.putBoolean("isFrench",true);
            }else if (english.isChecked()){
                editor.putBoolean("isFrench",false);
            }
            editor.apply();
            onResume();
        });

        if (sharedPreferences.getBoolean("isFrench",true)){
            group2.check(R.id.french);
        }else{
            group2.check(R.id.english);
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

        TextView textView = findViewById(R.id.textview), textView2 = findViewById(R.id.textview2), langue = findViewById(R.id.textview3),Fr = findViewById(R.id.french),En = findViewById(R.id.english);
        RadioButton default_mode = findViewById(R.id.default_mode) ,
                bicycling = findViewById(R.id.bicycling), car = findViewById(R.id.car), transit = findViewById(R.id.transit);

        if (getSharedPreferences("prefs", MODE_PRIVATE).getBoolean("isFrench",true)){
            textView.setText("Réglages");
            textView2.setText("Sélectionnez le mode");
            default_mode.setText("Marche");
            bicycling.setText("Vélo");
            car.setText("Voiture");
            transit.setText("Transport en commun");
            langue.setText("Langue");
            Fr.setText("Français");
            En.setText("Anglais");
        }
        else{
            textView.setText("Settings");
            textView2.setText("Select Mode");
            default_mode.setText("Walking");
            bicycling.setText("Bicycling");
            car.setText("Driving");
            transit.setText("Public Transit");
            langue.setText("Language");
            Fr.setText("French");
            En.setText("English");
        }

    }
}