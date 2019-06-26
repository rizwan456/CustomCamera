package com.rizz.customcamera.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rizz.customcamera.R;
import com.rizz.customcamera.customcamera.Camera2Fragment;
import com.rizz.customcamera.databinding.ActivityCameraBinding;

public class CameraActivity extends AppCompatActivity {

    ActivityCameraBinding cameraBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraBinding= DataBindingUtil.setContentView(this, R.layout.activity_camera);
        setUp();
    }

    private void setUp() {
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, Camera2Fragment.newInstance(null,null)).commit();
    }
}
