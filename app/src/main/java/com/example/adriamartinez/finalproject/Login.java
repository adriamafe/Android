package com.example.adriamartinez.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;

/**
 * Created by adriamartinez on 27/01/2017.
 */

public class Login extends ActionBarActivity {
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Rg9vkUu68pz6xnVOz4LtfzuTe";
    private static final String TWITTER_SECRET = "tj4GO8L9gQr2iccxVRAkZlPCZ3EkmL3GxKVLXEuiI9Qxj6sjJr";
    private TwitterLoginButton loginButton;
    final Context context = this;
    UserHelper userHelper;
    SharedPreferences sp;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.login);


       ///////// ///TWITTER///// on succes and on failure
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                Call<User> user = TwitterCore.getInstance().getApiClient().getAccountService().verifyCredentials(false, false);
                user.enqueue(new Callback<User>() {
                    @Override
                    public void success(Result<User> userResult) {
                        String name = userResult.data.name;
                        String email = userResult.data.email;

                        // _normal (48x48px) | _bigger (73x73px) | _mini (24x24px)
                        String photoUrlNormalSize   = userResult.data.profileImageUrl;
                        String photoUrlBiggerSize   = userResult.data.profileImageUrl.replace("_normal", "_bigger");
                        String photoUrlMiniSize     = userResult.data.profileImageUrl.replace("_normal", "_mini");
                        String photoUrlOriginalSize = userResult.data.profileImageUrl.replace("_normal", "");
                       // final ImageView iv = (ImageView) findViewById(R.id.profile);
                       // new DownloadImageTask(iv).execute(photoUrlBiggerSize);
                    }

                    @Override
                    public void failure(TwitterException exc) {
                        Log.d("TwitterKit", "Verify Credentials Failure", exc);
                    }
                });
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                /////On succes si no està a la base de dades s'introdueix amb zero punts
                ///// si ja hi es només s'actualitzen les shared preferences per saber qui
                ///// es el current user
                userHelper = new UserHelper(getApplicationContext());
                userHelper.check_login(session.getUserName());
                sp = getSharedPreferences("Profile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor  = sp.edit();
                editor.putString("current_user",session.getUserName());
                editor.apply();
                ShowMainActivity();
            }
            @Override
            public void failure(TwitterException exception) {
                failure_image();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }



    private void ShowMessage(String s){
    }
    private void ShowMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void failure_image(){

    }

    /*
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.login_button:
                EditText Name = (EditText) findViewById(R.id.user);
                EditText Password = (EditText) findViewById(R.id.password);
                String name = Name.getText().toString();
                String password = Password.getText().toString();
                int i = userHelper.check_login(name, password);
                next(i);
                break;
        }
    }
    */

}
