package com.rizz.customcamera;


import android.databinding.DataBindingUtil;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizz.customcamera.databinding.FragmentCamera2Binding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Camera2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Camera2Fragment extends Fragment implements IFilterClicked{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Camera mCamera;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentCamera2Binding camera2Binding;


    public Camera2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Camera2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Camera2Fragment newInstance(String param1, String param2) {
        Camera2Fragment fragment = new Camera2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCamera=getCameraInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        camera2Binding= DataBindingUtil.inflate(inflater,R.layout.fragment_camera2, container, false);
        setUp();
        return camera2Binding.getRoot();
    }

    private void setUp() {
        CameraPreview cameraPreview=new CameraPreview(getActivity(),mCamera);
        camera2Binding.cameraFrame.addView(cameraPreview);
        camera2Binding.filterImage.setOnClickListener(v->{
           // BSDFilterFragment.newInstance(null,null).show(getChildFragmentManager(),"BSDFilterFragment");
            BSDFilterFragment bsdFilterFragment=new BSDFilterFragment(this);
            bsdFilterFragment.show(getChildFragmentManager(),"BSDFilterFragment");
        });

        camera2Binding.cameraBtn.setOnClickListener(v->{
            mCamera.takePicture(null,null,mPicture);
        });
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void onClickFilter(int id) {
        try {
            Camera.Parameters parameters = mCamera.getParameters();
            switch (id) {
                case 1:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_NONE);
                    mCamera.setParameters(parameters);
                    break;
                case 2:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_AQUA);
                    mCamera.setParameters(parameters);
                    break;
                case 3:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_BLACKBOARD);
                    mCamera.setParameters(parameters);
                    break;
                case 4:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_MONO);
                    mCamera.setParameters(parameters);
                    break;
                case 5:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_NEGATIVE);
                    mCamera.setParameters(parameters);
                    break;
                case 6:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_POSTERIZE);
                    mCamera.setParameters(parameters);
                    break;
                case 7:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_SEPIA);
                    mCamera.setParameters(parameters);
                    break;
                case 8:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_SOLARIZE);
                    mCamera.setParameters(parameters);
                    break;
                case 9:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_WHITEBOARD);
                    mCamera.setParameters(parameters);
                    break;
            }
        }catch (Exception ex){
            Log.d(TAG,ex.getMessage());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile();
            if (pictureFile == null){
                Log.d(TAG, "Error creating media file, check storage permissions: ");
                return;
            }

            MediaScannerConnection.scanFile(getActivity(),
                    new String[] { pictureFile.toString() }, null,
                    (path, uri) -> mCamera.startPreview());
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ArshadPhotos");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                System.out.println("Directory not created");
                return null;
            }
        }

        SecureRandom random = new SecureRandom();
        int num = random.nextInt(1000000);
        return new File(mediaStorageDir.getAbsolutePath() + File.separator +
                "IMG_"+ num + ".jpg");
    }
}
