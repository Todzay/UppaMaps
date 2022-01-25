package fr.unvipau.uppamaps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MaterialCardView building = findViewById(R.id.building);
        building.setOnClickListener(view -> {
                startActivity(new Intent(this,SplashScreenActivity.class));

        });

        MaterialCardView settings = findViewById(R.id.settings);
        settings.setOnClickListener(view -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        MaterialCardView exit = findViewById(R.id.exit);
        exit.setOnClickListener(view -> {
            finish();
        });

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

        TextView building = findViewById(R.id.tv_building), setting = findViewById(R.id.tv_setting),
                exit = findViewById(R.id.tv_exit);

        if (getSharedPreferences("prefs",MODE_PRIVATE).getBoolean("isFrench",true)){
            building.setText("Bâtiments");
            setting.setText("Réglages");
            exit.setText("Sortir");
        }else{
            building.setText("Buildings");
            setting.setText("Settings");
            exit.setText("Exit");
        }

    }
}