package com.isummit.om.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Youtubes extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerFragment playerFragment;
    private YouTubePlayer mPlayer;
    private String YouTubeKey = "";
    private DatabaseReference rootRef;
    private String Youtube_static="AIzaSyAGXHr92fQoyIRckzQ1IxYDwEMypLQMERI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtubes);

        rootRef= FirebaseDatabase.getInstance().getReference("youtubeKey");

        rootRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                YouTubeKey=dataSnapshot.getValue().toString();
                setKey();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Youtubes.this, "Error Fetching Data" , Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
    public void setKey()
    {
        DatabaseReference.goOffline();
        playerFragment =(YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_player_fragment);

        playerFragment.initialize(Youtube_static, this);
    }

    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        mPlayer = player;

        //Enables automatic control of orientation


        //Show full screen in landscape mode always
        mPlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);

        //System controls will appear automatically
        mPlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);

        if (!wasRestored) {
            mPlayer.loadVideo(YouTubeKey);
        }
        else
        {
            mPlayer.play();
        }
    }
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        mPlayer = null;
        Toast.makeText(Youtubes.this, "Error Fetching Data" , Toast.LENGTH_SHORT).show();
        return;
    }

}


