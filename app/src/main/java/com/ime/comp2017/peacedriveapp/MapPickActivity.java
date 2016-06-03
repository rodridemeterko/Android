package com.ime.comp2017.peacedriveapp;

import android.graphics.BitmapFactory;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Context;
import android.graphics.Bitmap;

import com.loopj.android.http.*;

import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;


public class MapPickActivity extends AppCompatActivity {

    LinearLayout layout;
    public static final String URL  = "http://172.16.36.171/myproject/ServletMapsApp";
    int user_id;
    String url_teste = "iVBORw0KGgoAAAANSUhEUgAAAlgAAAJYCAYAAAC+ZpjcAAAQ5UlEQVR4nO3WQW5s2Q1Ewdq5vTL30uxJG/BUvygnkzcCeNBUAsSb5/NvAABGfdK/AADANQILAGCYwAIAGCawAACGCSwAgGECCwBgmMACABgmsAAAhgksAIBhAgsAYJjAAgAYJrAAAIYJLACAYQILAGCYwAIAGCawAACGCSwAgGECCwBgmMACABgmsAAAhgksAIBhAgsAYJjAAgAYJrAAAIYJLACAYQILAGCYwAIAGCawAACGCSwAgGGxwPoAAP93qd1/jcACgIekdv81AgsAHpLa/dcILAB4SGr3XyOwAOAhqd1/jcACgIekdv81AgsAHpLa/dcILAB4SGr3XyOwAOAhqd1/jcACgIekdv81AgsAHpLa/dcILAB4SGr3XyOwAOAhqd1/jcACgIekdv81rYH1D5/P5/P5Hv7+WGr3X9MYWP/4fD5/fT6ff/p8Pp/P9+D33w38I6ndf01rYP3xPxYAlPtqB1O7/xqBBQBdBFYBgQUAXQRWAYEFAF0EVgGBBQBdBFYBgQUAXQRWAYEFAF0EVgGBBQBdBFYBgQUAXQRWAYEFAF0EVgGBBQBdBFYBgQUAXQRWAYEFAF0EVgGBBQBdBFaB5wIr9fcCwP+yg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiwg7cJLAAIsIO3CSwACLCDtwksAAiw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";
    public static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_pick);

        Bundle extras = getIntent().getExtras();
        if(extras==null){
            //TODO fazer voltar pro login se nao tiver user_ID
        } else{
            user_id = extras.getInt("user_ID");
        }

        findViewsById();
        InitImages();

    }


    private void findViewsById(){
        layout = (LinearLayout) findViewById(R.id.LayoutScroll);
    }

    public void InitImages(){

        RequestParams param = new RequestParams();
        param.add("user_id", user_id+"");


        client.get(URL, param, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resp = new String(responseBody, StandardCharsets.UTF_8);
                parseImagesJSON pj = new parseImagesJSON(resp);
                pj.parseJSON();
                initScrollLayout(pj.imagesId, pj.imagesURL);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });
    }



    public void initScrollLayout(String[] images_ID, String[] images_URL){
        int i;
        String base64Encoded;
        byte[] decodedBytes;
        Bitmap decodedBitmap;
        int map_id;

        for(i=0; i < images_ID.length; i++){
            map_id = Integer.parseInt(images_ID[i]);
            base64Encoded = images_URL[i].split("image/png;base64,")[1];
            decodedBytes = Base64.decode(base64Encoded, Base64.DEFAULT);
            decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            ClickableImageView img = new ClickableImageView(MapPickActivity.this);
            img.setId(map_id);
            img.setPadding(2,2,2,2);
            img.setImageBitmap(decodedBitmap);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            mapObject obj = new mapObject(images_URL[i].split("data:image/png;base64,")[1], map_id);
            img.setOnClickListener(new ClickableImageView(MapPickActivity.this, img.getId(), obj));
            layout.addView(img);
        }

    }


    private class ClickableImageView extends ImageView implements OnClickListener {

        mapObject map;

        public ClickableImageView(Context context){
            super(context);
        }

        public ClickableImageView(Context context, int image_id){
            super(context);
            this.setId(image_id);
            int id_teste = 13405;
            this.map = new mapObject(url_teste, id_teste);
        }

        public ClickableImageView(Context context, int image_id, mapObject o){
            super(context);
            this.setId(image_id);
            this.map = o;
        }

        public ClickableImageView(Context context, AttributeSet attrs){
            super(context, attrs);
        }

        public ClickableImageView(Context context, AttributeSet attrs, int defStyle){
            super(context,attrs,defStyle);
        }

        public String getMapURL(){
            return this.map.getURL();
        }

        public int getMapID(){
            return this.map.getId();
        }

        @Override
        public void onClick(View view){
            startWifiActivite(this.getId(), this.getMapURL());
        }

    }

    public void startWifiActivite(int mapID, String url){
        Intent i = new Intent(MapPickActivity.this, WifiMapActivity.class);
        i.putExtra("user_ID", user_id);
        i.putExtra("map_ID", mapID);
        i.putExtra("map_URL", url);
        startActivity(i);
    }

    private class mapObject {
        String url;
        int id;

        public mapObject(String url, int id){
            this.setURL(url);
            this.setID(id);
        }

        private void setURL(String url){
            this.url = url;
        }

        public String getURL(){
            return this.url;
        }

        private void setID(int id){
            this.id = id;
        }

        private int getId(){
            return this.id;
        }
    }

}
