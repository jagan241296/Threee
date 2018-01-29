package com.isummit.om.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class GuestInfoDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guest_info_dialog);
    }
}
