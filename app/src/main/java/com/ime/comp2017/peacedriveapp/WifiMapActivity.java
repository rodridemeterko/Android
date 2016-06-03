package com.ime.comp2017.peacedriveapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.graphics.Bitmap;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class WifiMapActivity extends AppCompatActivity {

    int map_id;
    int user_id;
    String map_url;
    ImageView map_image;
    Button env_button;
    LinearLayout container;

    private WifiManager wifiManager;
    private List<WifiInfo> wifiInfoList;

    public static final String URL = "http://192.168.0.15/myproject/pontos"; //"http://172.16.36.171/myproject/pontos";
    private static AsyncHttpClient client = new AsyncHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_map);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            //TODO fazer voltar pro login se nao tiver user_ID
        } else {
            user_id = extras.getInt("user_ID");
            map_id = extras.getInt("map_ID");
            map_url = extras.getString("map_URL");
        }

        findViewsById();

        wifiInfoList = new ArrayList<>();
        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        setMapImage();

        onTouchStart();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja mesmo enviar as medições?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestParams params = new RequestParams();

                        for (WifiInfo wifi : wifiInfoList) {
                            params.add("id_map", map_id + "");
                            params.add("SSID", wifi.getSSID());
                            params.add("level", wifi.getLevel() + "");
                            params.add("x", wifi.getX() + "");
                            params.add("y", wifi.getY() + "");

                            client.get(URL, params, new TextHttpResponseHandler() {
                                @Override
                                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                    Toast toast = Toast.makeText(WifiMapActivity.this, "Erro no envio de dados", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String responseString) {

                                }
                            });
                        }
                        finish();
                    }
                })
                .setNegativeButton("Não",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        finish();
                    }
                });
        env_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                builder.show();
            }
        });

    }

    public void findViewsById() {
        map_image = (ImageView) findViewById(R.id.map_final);
        env_button = (Button) findViewById(R.id.env_button);
        container = (LinearLayout) findViewById(R.id.lay);
    }

    public void setMapImage() {
        byte[] decodedBytes;
        Bitmap decodedBitmap;

        decodedBytes = Base64.decode(map_url, Base64.DEFAULT);
        decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        map_image.setImageBitmap(decodedBitmap);
    }

    public void onTouchStart() {
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    View marker = LayoutInflater.from(WifiMapActivity.this).inflate(R.layout.marker, container, false);
                    marker.setTranslationX(event.getX());
                    marker.setTranslationY(event.getY());
                    container.addView(marker);
                    final int x = Math.round(event.getX());
                    final int y = Math.round(event.getY());
                    new AlertDialog.Builder(WifiMapActivity.this)
                            .setTitle("Fazer medição")
                            .setMessage("Confirmar Medição de Intensidade?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                    measureIntensity(x,y);

                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                }

                return false;
            }
        });


    }

    public void measureIntensity(int posx, int posy) {
        wifiManager.startScan();
        List<ScanResult> scanResultList = wifiManager.getScanResults();

        for (ScanResult result : scanResultList) {
            wifiInfoList.add(new WifiInfo(result.SSID, result.level, posx, posy));
        }
    }


}
