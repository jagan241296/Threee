package com.isummit.om.sample;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Folder path for Firebase Storage.
    String Storage_Path = "Img/";

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
    private FirebaseAuth mAuth;
    private String name,email,uid;
    private Uri photoUrl;
    private boolean emailVerified;
    String USERNAME_KEY ="UserName";
    String EMAIL_KEY = "email";
    String prefName = "userNamePref";
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences userPrefs = getSharedPreferences(prefName, MODE_PRIVATE);
        String userName = userPrefs.getString(USERNAME_KEY, "");
        String email = userPrefs.getString(EMAIL_KEY, "");
        storageReference = FirebaseStorage.getInstance().getReference();

        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        if(userName==""||email=="")
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

         viewPager = (ViewPager) findViewById(R.id.pager);
         adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
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
        user_email = header.findViewById(R.id.user_email);
        profile=header.findViewById(R.id.profile_pic);

        username.setText(userName);
        user_email.setText(email);

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
        if (id == R.id.action_settings) {
            return true;
        }

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
                sendIntent.putExtra(Intent.EXTRA_TEXT, "hello username,invited you to try 3i Summit App Click here to download ");
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
                Intent guest=new Intent(MainActivity.this,Guest.class);
                startActivity(guest);
                break;
            }
            case R.id.prev: {
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
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.brandslam.threeisummit")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.brandslam.threeisummit")));
                }
                break;
            }

            case R.id.exit:
            {
                System.exit(1);
            }
        }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

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
        Intent aa=new Intent(MainActivity.this,Youtubes.class);
        startActivity(aa);
    }}
