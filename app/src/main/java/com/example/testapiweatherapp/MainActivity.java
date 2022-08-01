package com.example.testapiweatherapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView test1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1 = (TextView) findViewById(R.id.test1);
        fetchData();
    }

    private void fetchData() {


        String url = "https://forecast9.p.rapidapi.com/";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "forecast9.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1aa2e5b4e6msh07f8221e880e070p1d5db4jsn14c10abe914c")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                Toast.makeText(MainActivity.this, "Some eror !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()) {
                    String resp = response.body().string();
//                    System.out.println("sdfsdfsdf " + response);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(resp);
                                // đoạn này là quan trọng muốn lấy dữ liệu về cái gì
                                // như api hiện tại thì có 3 thược tính

                                String val1 = jsonObject.getString("title");
//                                String val2 = jsonObject.getString("result");
                                test1.setText(val1 + " Thành công");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        });


    }
}