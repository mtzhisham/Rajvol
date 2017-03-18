package com.moataz.rajvr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;

import org.rajawali3d.Object3D;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.materials.textures.TextureManager;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Cube;
import org.rajawali3d.renderer.Renderer;
import org.rajawali3d.util.ObjectColorPicker;
import org.rajawali3d.util.OnObjectPickedListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by moataz on 3/17/2017.
 */
public class RjRenderer extends Renderer implements OnObjectPickedListener {

    public Context context;
    ObjectColorPicker mPicker;
    Vector3 pos;

    private DirectionalLight mLight;
    private Cube mCube;
    private float mTime;
    private Material mMaterial;
    TextureManager manager;

    public RjRenderer (Context context) {
        super(context);
        this.context = context;
        setFrameRate(60);
    }


    @Override
    protected void initScene() {

        mLight = new DirectionalLight(0, 5, 1);
        pos = new Vector3(0,0,0);
        getCurrentScene().addLight(mLight);

        Vshader vshader = new Vshader(readRawTextFile(context,"vertex.glsl"));
         Fshader fshader = new Fshader(readRawTextFile(context,"fragment.glsl"));



        mMaterial = new Material(vshader, fshader);
        mMaterial.enableTime(true);
        try {
            Log.d("texture","apeanding texture");
            Texture texture = new Texture("uTex", R.drawable.bunny_100x); //20x2000
            //Texture texture = new Texture("uTex", R.drawable.bunny_100x); //100x10000
            texture.setFilterType(ATexture.FilterType.LINEAR);
            texture.setCompressedTexture(texture.getCompressedTexture());



            Log.d("texture","before adding");
            mMaterial.addTexture(texture);
            Log.d("texture","after adding");
        } catch (ATexture.TextureException e) {
            e.printStackTrace();
        }

        mCube = new Cube(1f);
        mCube.setMaterial(mMaterial);
        mCube.setTransparent(true);
        mCube.setDoubleSided(true);
        getCurrentScene().addChild(mCube);

        getCurrentCamera().setPosition(0, 0, 7);

        mTime = 0;

        mPicker = new ObjectColorPicker(this);
        mPicker.setOnObjectPickedListener(this);
        mPicker.registerObject(mCube);
    }

    @Override
    protected void onRender(long ellapsedRealtime, double deltaTime) {
        super.onRender(ellapsedRealtime, deltaTime);
        mCube.rotate(Vector3.Axis.Y, 1.0);
        mCube.setZ(pos.z);


    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {


    }


    @Override
    public void onObjectPicked(@NonNull Object3D object) {

        pos.x = 0;
        pos.y = 0;
        pos.z = 3;

    }

    @Override
    public void onNoObjectPicked() {
        pos.x = 0;
        pos.y = 0;
        pos.z = -2;

    }

    public void getObjectAt(float x, float y) {
        mPicker.getObjectAt(x, y);
    }


    public static String readRawTextFile(Context ctx, String file)
    {
        StringBuilder text;
        Log.d("vertex",file);
        try {
            InputStream inputStream = ctx.getAssets().open(file);

            InputStreamReader inputreader = new InputStreamReader(inputStream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            String line;
            text = new StringBuilder();


            while (( line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return text.toString();

    }



}
