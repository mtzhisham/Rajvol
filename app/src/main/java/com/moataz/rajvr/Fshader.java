package com.moataz.rajvr;

import android.opengl.GLES20;

import org.rajawali3d.materials.shaders.AShaderBase;
import org.rajawali3d.materials.shaders.FragmentShader;

/**
 * Created by moataz on 3/17/2017.
 */
public class Fshader extends FragmentShader{


    private String sString;
    private int camPosHandle;
    private int lightPHandle;
    private int lightCHandle;
    private int colorHandle;
    private int texDimHandle;
    private int TMKHandle;


    public Fshader(String shaderString){
        super();
        sString =shaderString;
        mNeedsBuild= false;
        initialize();

    }


    @Override
    public void initialize() {
        super.initialize();

        mShaderString = sString;
    }

    @Override
    public void main() {
        super.main();
    }

    @Override
    public void setLocations(int programHandle) {
        super.setLocations(programHandle);
        camPosHandle = getUniformLocation(programHandle,"uCamPos");
        lightPHandle = getUniformLocation(programHandle,"uLightP");
        lightCHandle = getUniformLocation(programHandle,"uLightC");
        colorHandle = getUniformLocation(programHandle,"uColor");
        texDimHandle = getUniformLocation(programHandle,"uTexDim");
        TMKHandle = getUniformLocation(programHandle,"uTMK");


    }

    @Override
    public void applyParams() {
        super.applyParams();


        GLES20.glUniform3f(camPosHandle,0,0,7);
        GLES20.glUniform3fv(lightPHandle,1,new float[]{255,255,255,255,255,255},0);
        GLES20.glUniform3fv(lightCHandle,1,new float[]{255,255,255,255,255,255},0);
        GLES20.glUniform3f(colorHandle,50,50,50);
        GLES20.glUniform3f(texDimHandle,20,20,20);
        GLES20.glUniform1f(TMKHandle,16.0f);


    }



}
