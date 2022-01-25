package fr.unvipau.uppamaps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.location.LocationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    ArrayList<Model> list = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        } else {

            populate();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                populate();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    void populate() {

        locationRequest = LocationRequest
                .create()
                .setInterval(60000)
                .setFastestInterval(20000)
                .setSmallestDisplacement(100f)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(locationSettingsResponse -> {
            getLocationUpdates();
        });
        task.addOnFailureListener(it->{
            if (!isLocationEnabled(this)){

                // Build the alert dialog
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Le service de localisation n'est pas activé");
                builder2.setMessage("Veuillez activer les services de localisation et le GPS ");
                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Show location settings when the user acknowledges the alert dialog
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent,1);
                    }
                });
                Dialog alertDialog = builder2.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }else{
                getLocationUpdates();
            }
        });


    }

    void getLocationUpdates() {

        SharedPreferences sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
//                super.onLocationResult(locationResult);
                if (!locationResult.getLocations().isEmpty()) {
                    Log.d("121212", "Lat" + locationResult.getLastLocation().getLatitude() + "Long" + locationResult.getLastLocation().getLongitude());

                    double lat = locationResult.getLastLocation().getLatitude();
                    double lon = locationResult.getLastLocation().getLongitude();

//                    Toast.makeText(MainActivity.this,"Loading",Toast.LENGTH_SHORT).show();

                    try {
                        InputStream is = getAssets().open("data.csv");
//                        InputStream is = getResources().openRawResource(R.raw.data);
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(is, Charset.forName("ISO-8859-1"))
                        );
                        String line;

//                        ArrayList<String> buildings = new ArrayList<>();
                        int p = 0;
                        while ((line = reader.readLine()) != null) {
                            if (p>0){

                                Model model = new Model();

                                String[] t = line.split(";", -1);
                                model.setCLE_INTERNE_BATIMENT(t[0]);
                                model.setCODE_BATIMENT(t[1]);
                                model.setLIBELLE_BATIMENT(t[2]);
                                model.setADR_ORDRE(t[3]);
                                model.setCODE_POSTAL(t[4]);
                                model.setVILLE(t[5]);
                                model.setADR_ADRESSE1(t[6]);
                                model.setADR_ADRESSE2(t[7]);
//                                model.setADR_GPS_LONGITUDE(t[8]);
                                model.setADR_GPS_LONGITUDE(""+(74.3166163+p));
//                                model.setADR_GPS_LATITUDE(t[9]);
                                model.setADR_GPS_LATITUDE(""+(31.5641139+p));
                                model.setDistance(2000);
                                list.add(model);
//                Log.d("------","-----------------");
//                            buildings.add(t[2]);

                            }
                            p++;
                        }

                        ArrayList<Model2> model2 = new ArrayList<>();
                        ArrayList<String> ville = new ArrayList<>();

                        for (int i=0; i< list.size(); i++){
                            ville.add(list.get(i).getVILLE());
                        }

                        Set<String> set = new HashSet<>(ville);
                        ville.clear();
                        ville.addAll(set);

                        ville.sort(Comparator.naturalOrder());

                        for (int i=0; i<ville.size();i++){
                            Model2 im = new Model2();
                            im.setName(ville.get(i));
                            im.setVille(true);
                            model2.add(im);
                            for (int j=0; j<list.size();j++){
                                if (list.get(j).getVILLE().equals(ville.get(i))){
                                    Model2 jm = new Model2();
                                    jm.setName(list.get(j).getLIBELLE_BATIMENT());
                                    jm.setVille(false);
                                    model2.add(jm);
                                }
                            }
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.hasFixedSize();
                        recyclerView.setAdapter(new RecyclerAdapter(model2,MainActivity.this));



                    } catch (IOException e) {
                        Log.d("123123123", "--" + e.getMessage() + "--");
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }
        };

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1){
            if (isLocationEnabled(this)){
                getLocationUpdates();
            }
        }else{
            Toast.makeText(this,"Location not enabled",Toast.LENGTH_SHORT).show();
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isLocationEnabled(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return manager != null && LocationManagerCompat.isLocationEnabled(manager);
    }

}