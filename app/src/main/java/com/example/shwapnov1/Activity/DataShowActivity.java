package com.example.shwapnov1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shwapnov1.API.UserClient;
import com.example.shwapnov1.Model.Post;
import com.example.shwapnov1.Model.SensorData;
import com.example.shwapnov1.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataShowActivity extends AppCompatActivity {

    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);

        mTextViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.89:2000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserClient mUserClient = retrofit.create(UserClient.class);

        Call<List<SensorData>> call = mUserClient.getSensorData();



        call.enqueue(new Callback<List<SensorData>>() {
            @Override
            public void onResponse(Call<List<SensorData>> call, Response<List<SensorData>> response) {

                if(!response.isSuccessful()){
                    mTextViewResult.setText("Code: "+ response.code());
                    return; //to leave this method from here
                }

                List<SensorData> allData = response.body();

                for(SensorData sdata : allData){
                    String content="";
                    content += "Device Id: "+sdata.getDevId()+"\n";
                    content += "ID: "+sdata.getId()+"\n";
                    content += "Power: "+sdata.getPower()+"\n";
                    content += "Temp: "+sdata.getTemperature()+"\n";
                    content += "DateTime: "+sdata.getDateTime()+"\n\n";

                    mTextViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<SensorData>> call, Throwable t) { //Throwable is super class of exceptions

                mTextViewResult.setText(t.getMessage());

            }
        });




    }
}
