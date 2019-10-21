package com.icloudwhale.cloudpos.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.icloudwhale.cloudpos.R;
import com.icloudwhale.cloudpos.databinding.ActivityCameraBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.CameraView;
import androidx.databinding.DataBindingUtil;
import timber.log.Timber;

/**
 * @author xuliangliang
 * @date 2019-09-20
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class CameraActivity extends AppCompatActivity {
    String TAG = "CameraActivity";
    ActivityCameraBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_camera);

        mBinding.camera.bindToLifecycle(this);

        mBinding.btnStart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                mBinding.camera.setCaptureMode(CameraView.CaptureMode.VIDEO);

                Timber.tag(TAG).d(mBinding.camera.getMaxVideoDuration() + "  getMaxVideoDuration");
            }
        });

        mBinding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.camera.stopRecording();
            }
        });
    }

    public void takePhoto(View view) {
        mBinding.camera.setCaptureMode(CameraView.CaptureMode.IMAGE);
/*
 mBinding.camera.takePicture(new ImageCapture.OnImageCapturedListener() {

            @Override
            public void onCaptureSuccess(ImageProxy image, int rotationDegrees) {
                super.onCaptureSuccess(image, rotationDegrees);
                Timber.i("onCaptureSuccess: ");
            }

            @Override
            public void onError(@NonNull ImageCapture.ImageCaptureError imageCaptureError, @NonNull String message, @Nullable Throwable cause) {
                super.onError(imageCaptureError, message, cause);
                Timber.i("onError: " + message);
            }
        });
*/

    }

}
