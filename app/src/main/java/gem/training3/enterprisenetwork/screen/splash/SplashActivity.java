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
import gem.training3.enterprisenetwork.base.log.EventLogger;
import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.model.UserCredential;
import gem.training3.enterprisenetwork.screen.main.MainActivity;
import gem.training3.enterprisenetwork.screen.welcome.WelcomeActivity;

/**
 * Created by huylv on 17/02/2016.
 */
public class SplashActivity extends Activity {
    private boolean LOGGED_IN = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        EventLogger.error("ssssssssssss");
        //load share preferences
        SharedPreferences sp = getSharedPreferences(Constants.USER_INFO,MODE_PRIVATE);
        LOGGED_IN = sp.contains(Constants.SHARE_PREFERENCE_KEY_USER_JSON);
        if (LOGGED_IN) {
            //cast share preference to user  object
            Gson gson = new Gson();
            String s = sp.getString(Constants.SHARE_PREFERENCE_KEY_USER_JSON, "null");
            JsonReader jsonReader = new JsonReader(new StringReader(s));
            jsonReader.setLenient(true);
            UserCredential user = gson.fromJson(jsonReader, UserCredential.class);

            Session.setUser(user);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (LOGGED_IN) {
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
