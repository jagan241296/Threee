package com.isummit.om.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "Images";

    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference;
    DatabaseReference databaseReference;

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabLayout;
    private ImageView profile;
    private TextView username, user_email;
    private DatabaseReference mref;
    String USERNAME_KEY ="UserName";
    String prefName = "userNamePref";
    private String userName, val;
    private String EVENT_TIME="event_time";
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences userPrefs = getSharedPreferences(prefName, MODE_PRIVATE);
        userName = userPrefs.getString(USERNAME_KEY, "");
        storageReference = FirebaseStorage.getInstance().getReference();

        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        if(userName=="")
        {
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            finish();
        }

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });

        tabLayout =  findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_about_us_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_event_white_24dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

         viewPager =  findViewById(R.id.pager);
         adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        navigationView = findViewById(R.id.nav_view);


        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);

        username = header.findViewById(R.id.username);
        profile=header.findViewById(R.id.profile_pic);

        username.setText(userName);


       // progressDialog = new ProgressDialog(MainActivity.this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
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

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.invite:
            {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello, "+userName+" invited you to try 3I Summit App Click on link to download: https://play.google.com/store/apps/details?id=com.isummit.om.sample");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                // Handle the camera action
                break;
            }

            case R.id.aboutus:
            {
                TabLayout.Tab tab = tabLayout.getTabAt(1);
                tab.select();
                break;
            }
            case R.id.guests:
            {

                boolean netConnected=isNetworkAvailable();
                if(netConnected==false)
                {
                    Toast.makeText(MainActivity.this, "Network Error...",Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent guest=new Intent(MainActivity.this,Guest.class);
                startActivity(guest);
                break;
            }
            case R.id.prev: {
                boolean netConnected=isNetworkAvailable();
                if(netConnected==false)
                {
                    Toast.makeText(MainActivity.this, "Network Error...",Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent prev=new Intent(MainActivity.this,Prev.class);
                startActivity(prev);
                break;
            }

            case R.id.event:
            {
                TabLayout.Tab tab = tabLayout.getTabAt(2);
                tab.select();
                break;
            }

            case R.id.gallery:
            {
                boolean netConnected=isNetworkAvailable();
                if(netConnected==false)
                {
                    Toast.makeText(MainActivity.this, "Network Error...",Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent gallery=new Intent(MainActivity.this,DisplayImagesActivity.class);
                startActivity(gallery);
                break;
            }

            case R.id.feedback: {
                Intent feeds=new Intent(MainActivity.this,FeedBack.class);
                startActivity(feeds);
                break;
            }

            case R.id.testi: {
                boolean netConnected=isNetworkAvailable();
                if(netConnected==false)
                {
                    Toast.makeText(MainActivity.this, "Network Error...",Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent testi=new Intent(MainActivity.this,Testimonials.class);
                startActivity(testi);
                break;
            }

            case R.id.gatePass: {
                Intent gatepass=new Intent(MainActivity.this,GatePass.class);
                startActivity(gatepass);
                break;
            }

            case R.id.locate_us:
            {
                boolean netConnected=isNetworkAvailable();
                if(netConnected==false)
                {
                    Toast.makeText(MainActivity.this, "Network Error...",Toast.LENGTH_SHORT).show();
                    break;
                }
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=18.706676,73.658540(3I Summit 2018)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                break;
            }

            case R.id.rate_us:
            {

                try {
                    boolean netConnected=isNetworkAvailable();
                    if(netConnected==false)
                    {
                        Toast.makeText(MainActivity.this, "Network Error...",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.isummit.om.sample")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.isummit.om.sample")));
                }
                break;
            }

            case R.id.exit:
            {
                DatabaseReference.goOffline();
                System.exit(1);
            }
        }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        int size = navigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }
    public void youlive(View v){


        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        v.startAnimation(myAnim);
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                boolean netConnected=isNetworkAvailable();
                if(netConnected==true)
                {
                    Intent aa=new Intent(MainActivity.this,Youtubes.class);
                    startActivity(aa);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Network Error...",Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}