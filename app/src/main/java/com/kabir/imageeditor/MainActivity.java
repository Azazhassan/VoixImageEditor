package com.kabir.imageeditor;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kabir.imageeditor.adapters.CardsAdapter;
import com.outstarttech.kabir.eidcardeditor.R;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase userDatabase;
    DatabaseReference userReference;

    private NavigationView navigationView1;
    public DrawerLayout mDrawerLayout;
    public android.support.v7.app.ActionBarDrawerToggle mToggle;
    public Toolbar mToolbar;

    RelativeLayout completeEidCards;
    RelativeLayout cardsBackgrounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        completeEidCards = findViewById(R.id.completeCards);
        cardsBackgrounds = findViewById(R.id.cardsBackgrounds);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userDatabase = FirebaseDatabase.getInstance();


        navigationView1 = (NavigationView) findViewById(R.id.drawerNewActivity);
        navigationView1.setNavigationItemSelectedListener(this);
        firebaseAuth = FirebaseAuth.getInstance();

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutNewActivity);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        completeEidCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CardsActivity.class));
            }
        });

        cardsBackgrounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CardsActivity2.class));
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.


        switch (item.getItemId()) {

            case R.id.home: {
                finish();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                //do somthing
                break;
            }
            case R.id.contact: {
                String msg = "Your Message: ";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[] { "outstarttech@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "[Support] Eid Recipes");
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                //do somthing
                break;
            }
            case R.id.rate: {
                rateApp();
                break;
            }
            case R.id.recipes: {
                finish();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                //do somthing
                break;
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void rateApp(){
        try
        {
            Uri uri1 = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri1);
            startActivity(goToMarket);
        }catch (ActivityNotFoundException e){
            Uri uri1 = Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri1);
            startActivity(goToMarket);
        }
    }
}
