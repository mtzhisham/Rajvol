package com.moataz.rajvr;

import org.rajawali3d.materials.shaders.VertexShader;

/**
 * Created by moataz on 3/17/2017.
 */
public class Vshader extends VertexShader {
   private String sString;


    public Vshader(String shaderString) {

        super();
        sString = shaderString;
        mNeedsBuild= false;
        initialize();
    }

    @Override
    public void initialize() {
       mShaderString = sString;

    }

    @Override
    public void main() {
        super.main();
    }


    @Override
    public void setLocations(int programHandle) {
        super.setLocations(programHandle);
    }

    @Override
    public void applyParams() {
        super.applyParams();
    }
}

