package com.example.myapplicationopencv;



import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraActivity;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.imgproc.Imgproc;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.Collections;
import java.util.List;

public class MainActivity extends CameraActivity implements CvCameraViewListener2 {
    private static final String    TAG = "OCVSample::Activity";

    private byte[] outarray;
    private byte[] tmparray;

    private int w;

    private int h;


    private CameraBridgeViewBase   mOpenCvCameraView;

    private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");

                    // Load native library after(!) OpenCV initialization

                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    public MainActivity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);


        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial2_activity_surface_view);
        mOpenCvCameraView.setVisibility(CameraBridgeViewBase.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.enableFpsMeter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "called onCreateOptionsMenu");
        return true;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected List<? extends CameraBridgeViewBase> getCameraViewList() {
        return Collections.singletonList(mOpenCvCameraView);
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
        //mRgba = new Mat(height, width, CvType.CV_8UC4);
        outarray = new byte[width*height];
        w=width;
        h=height;

    }

    public void onCameraViewStopped() {
        //mRgba.release();

    }

    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
        Mat gray =inputFrame.gray();
        MatToArray(gray);

        //Sutf to do here by the students....

        //Sobel
        int sobel_x[][] = {{-1,0,1},
                {-2,0,2},
                {-1,0,1}};
        int sobel_y[][] = {{-1,-2,-1},
                {0,0,0},
                {1,2,1}};

        int pixel_x;
        int pixel_y;

        for(int x=1;x<w-2;x++) {
            for (int y = 1; y < h - 2; y++) {
                pixel_x = (sobel_x[0][0] * outarray[(y-1)*w+(x-1)]) + (sobel_x[0][1] * outarray[(y-1)*w+(x)]) + (sobel_x[0][2] * outarray[(y+1)*w+(x+1)]) +
                        (sobel_x[1][0] * outarray[(y)*w+(x-1)])   + (sobel_x[1][1] * outarray[(y)*w+(x)])   + (sobel_x[1][2] * outarray[(y)*w+(x+1)]) +
                        (sobel_x[2][0] * outarray[(y+1)*w+(x-1)]) + (sobel_x[2][1] * outarray[(y+1)*w+(x)]) + (sobel_x[2][2] * outarray[(y+1)*w+(x+1)]);
                pixel_y = (sobel_y[0][0] * outarray[(y-1)*w+(x-1)]) + (sobel_y[0][1] * outarray[(y-1)*w+(x)]) + (sobel_y[0][2] * outarray[(y+1)*w+(x+1)]) +
                        (sobel_y[1][0] * outarray[(y)*w+(x-1)])   + (sobel_y[1][1] * outarray[(y)*w+(x)])   + (sobel_y[1][2] * outarray[(y)*w+(x+1)]) +
                        (sobel_y[2][0] * outarray[(y+1)*w+(x-1)]) + (sobel_y[2][1] * outarray[(y+1)*w+(x)]) + (sobel_x[2][2] * outarray[(y+1)*w+(x+1)]);

                int val = (int)Math.sqrt((pixel_x * pixel_x) + (pixel_y * pixel_y));

                if(val < 0)
                {
                    val = 0;
                }

                if(val > 255)
                {
                    val = 255;
                }

                outarray[y*w+x] = (byte) val;
            }

        }






        //Gradient

//        int GradH;
//        int GradV;
//        int val;
//        for(int x=1;x<w-1;x++)
//        {
//            for(int y=1;y<h-1;y++)
//            {
//                GradH =  Math.abs(outarray[y*w+(x-1)]-outarray[y*w+(x+1)]);
//                if(GradH < 0)
//                {
//                    GradH = 0;
//                }
//
//                if(GradH > 255)
//                {
//                    GradH = 255;
//                }
//                GradV =  Math.abs(outarray[(y-1)*w+x]-outarray[(y+1)*w+x]);
//                if(GradV < 0)
//                {
//                    GradV = 0;
//                }
//
//                if(GradV > 255)
//                {
//                    GradV = 255;
//                }
//
//                outarray[y*w+x] = (byte) (GradH + GradV);
//            }
//        }

        Mat out=ArrayToMat(gray,outarray);
        return out;
    }

    private Mat ArrayToMat(Mat gray, byte[] grayarray) {
        // TODO Auto-generated method stub
        Mat out = gray.clone();
        out.put(0,0,grayarray);
        return out;
    }

    private void MatToArray(Mat gray) {
        // TODO Auto-generated method stub
        gray.get(0, 0, outarray);

    }


}