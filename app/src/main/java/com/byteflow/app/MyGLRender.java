package com.byteflow.app;

import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.byteflow.app.MyNativeRender.SAMPLE_TYPE;
import static com.byteflow.app.MyNativeRender.SAMPLE_TYPE_KEY_BEATING_HEART;
import static com.byteflow.app.MyNativeRender.SAMPLE_TYPE_SET_GRAVITY_XY;
import static com.byteflow.app.MyNativeRender.SAMPLE_TYPE_SET_TOUCH_LOC;

public class MyGLRender implements GLSurfaceView.Renderer {
    private static final String TAG = "MyGLRender";
    private MyNativeRender mNativeRender;
    private int mSampleType = SAMPLE_TYPE_KEY_BEATING_HEART;

    MyGLRender() {
        mNativeRender = new MyNativeRender();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.i(TAG, "onSurfaceCreated() called with: GL_VERSION = [" + gl.glGetString(GL10.GL_VERSION) + "]");
        mNativeRender.native_OnSurfaceCreated();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.i(TAG, "onSurfaceChanged: width =" + width + ", height = " + height);
        mNativeRender.native_OnSurfaceChanged(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        if (mSampleType != SAMPLE_TYPE_KEY_BEATING_HEART) {
            Log.i(TAG, "onDrawFrame: gl = " + gl);
        }
        mNativeRender.native_OnDrawFrame();
    }

    public void init() {
        Log.i(TAG, "init");
        mNativeRender.native_Init();
    }

    public void unInit() {
        Log.i(TAG, "unInit");
        mNativeRender.native_UnInit();
    }

    public void setParamsInt(int paramType, int value0, int value1) {
        Log.i(TAG, "setParamsInt: paramType = " + paramType + ", value0 = " + value0 + ", value1 =" + value1);
        if (paramType == SAMPLE_TYPE) {
            mSampleType = value0;
        }
        mNativeRender.native_SetParamsInt(paramType, value0, value1);
    }

    public void setTouchLoc(float x, float y)
    {
        Log.i(TAG, "setTouchLoc: x = " + x + ", y = " + y);
        mNativeRender.native_SetParamsFloat(SAMPLE_TYPE_SET_TOUCH_LOC, x, y);
    }

    public void setGravityXY(float x, float y) {
        Log.i(TAG, "setGravityXY: x = " + x + ", y = " + y);
        mNativeRender.native_SetParamsFloat(SAMPLE_TYPE_SET_GRAVITY_XY, x, y);
    }

    public void setImageData(int format, int width, int height, byte[] bytes) {
        Log.i(TAG, "setImageData: format = " + format + ", width = " + width + ", height = " + height);
        mNativeRender.native_SetImageData(format, width, height, bytes);
    }

    public void setImageDataWithIndex(int index, int format, int width, int height, byte[] bytes) {
        Log.i(TAG, "setImageDataWithIndex: index = " + index + ", format = " + format + ", width = " + width + ", height = " + height);
        mNativeRender.native_SetImageDataWithIndex(index, format, width, height, bytes);
    }

    public void setAudioData(short[] audioData) {
        Log.i(TAG, "setAudioData: ");
        mNativeRender.native_SetAudioData(audioData);
    }

    public int getSampleType() {
        return mSampleType;
    }

    public void updateTransformMatrix(float rotateX, float rotateY, float scaleX, float scaleY)
    {
        Log.i(TAG, "updateTransformMatrix: rotateX = " + rotateX + ", rotateY = " + rotateY + ", scaleX = " + scaleX + ", scaleY = " + scaleY);
        mNativeRender.native_UpdateTransformMatrix(rotateX, rotateY, scaleX, scaleY);
    }

}
