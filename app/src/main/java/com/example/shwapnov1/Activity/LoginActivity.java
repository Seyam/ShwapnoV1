package com.example.shwapnov1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shwapnov1.Model.User;
import com.example.shwapnov1.Model.UserResponse;
import com.example.shwapnov1.R;
import com.example.shwapnov1.API.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

     String deviceId;
     String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        final EditText name = findViewById(R.id.input_name);
        final EditText password = findViewById(R.id.input_password);

        //get the button
        Button signInButton = findViewById(R.id.sign_in_button);
        //set the button click handler
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Other way to pass the form data
//                deviceId = name.getText().toString();
//                temp =  password.getText().toString()

              //Dummy User if no input is taken
              //User user = new User("Megatron", "foo");
                User user = new User(
                        name.getText().toString(),
                        password.getText().toString()
                );


                sendNetworkRequest(user);
                openDataShowActivity();
            }
        });


    }

    public void openDataShowActivity(){
        Intent mIntent;
        mIntent = new Intent(this, DataShowActivity.class);
        startActivity(mIntent);
    }


    public void sendNetworkRequest(User user){
        //create a retrofit instance
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.104:3000/") //http://192.168.2.104:3000/edit
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //get a client interface and Call object for the request
        UserClient client = retrofit.create(UserClient.class); //notice that not declaring as new, retrofit is taking care of this
        //call the interface method implemented by the class with the help of retrofit.create
        //Call<User> call = client.sendPost(user);
//        Call<UserResponse> call = client.sendPostAsFormUrlEncoded("10111", "25.2");//Hardcoded
//        Call<UserResponse> call = client.sendPostAsFormUrlEncoded(user.getDevice_no(), user.getTemperature());//Pulled properties from user object
        String base = user.getDevice_no()+":"+user.getTemperature();
        String authHeader = "Basic "+ Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        Call<UserResponse> call = client.sendPostAsBasicAuthentication(authHeader);


        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {//Response has to be in proper Json format, or else "onFailure" function will be triggered
                Toast.makeText(LoginActivity.this, "Response: "+response.body().getResponse(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(LoginActivity.this, "Device No: "+response.body().getDevice_no()+"\nTemperature: "+response.body().getTemperature()+"\nId: "+response.body().getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
