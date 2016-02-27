package gem.training3.enterprisenetwork.screen.splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.model.ResponseUserInfoDTO;
import gem.training3.enterprisenetwork.screen.main.MainActivity;
import gem.training3.enterprisenetwork.screen.welcome.WelcomeActivity;

/**
 * Created by huylv on 17/02/2016.
 */
public class SplashActivity extends Activity {
    private boolean LOGGEDIN = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //check login
        SharedPreferences sp = getSharedPreferences(Constants.USER_INFO,MODE_PRIVATE);
        LOGGEDIN = sp.contains(Constants.SPKEY_USERJSON);
        if(LOGGEDIN){
            Gson gson = new Gson();
            String s = sp.getString(Constants.SPKEY_USERJSON, "null");
            JsonReader jsonReader = new JsonReader(new StringReader(s));
            jsonReader.setLenient(true);
            ResponseUserInfoDTO responseUserInfo = gson.fromJson(jsonReader, ResponseUserInfoDTO.class);

            Session.setUser(responseUserInfo);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(LOGGEDIN){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);
    }
}
