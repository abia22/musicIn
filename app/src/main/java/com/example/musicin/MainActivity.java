package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.musicin.data.Data;
import com.example.musicin.data.Musician;
import com.google.android.material.button.MaterialButton;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       data = Data.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        if(email != null) {
            Intent intent = new Intent(MainActivity.this, MusicianHubActivity.class);
            intent.putExtra("email", email);
            finishAffinity();
            startActivity(intent);
        }

        ImageCarousel carousel = findViewById(R.id.carousel);
        carousel.registerLifecycle(getLifecycle());


        List<CarouselItem> list = new ArrayList<>();

        list.add( new CarouselItem(
                "https://www.allbusiness.com/asset/2019/01/Music-Band-rehearsing.jpg",
                 "Form a band with other users"
        ));

        list.add( new CarouselItem(
                "https://media.istockphoto.com/photos/crowd-on-a-music-festival-picture-id1191818259?k=20&m=1191818259&s=612x612&w=0&h=jGCzUvIdF65oa0SaT8Vmdvc_YpnyR5ROOMZb_FuoPYM=",
                 "Find shows to play at"
        ));

        list.add( new CarouselItem(
                "https://apexsoundandlight.ca/files/images/products/manufacturers/martin-professional-lighting.jpg",
                 "Give a hand on the backstage"
        ));

        carousel.setData(list);

        MaterialButton login_bttn = findViewById(R.id.login_btt);
        login_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        MaterialButton sign_in_bttn = findViewById(R.id.sign_in_btt);
        sign_in_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SelectIndEntActivity.class));
            }
        });



    }
}