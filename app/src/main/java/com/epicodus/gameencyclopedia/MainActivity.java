package com.epicodus.gameencyclopedia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Bind(R.id.buttonQuery) Button mButtonQuery;
    @Bind(R.id.textAbout) TextView mAboutView;
    @Bind(R.id.savedGameButton) Button mSavedGameButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor=mSharedPreferences.edit();
        mSavedGameButton.setOnClickListener(this);
        mButtonQuery.setOnClickListener(this);
        mAboutView.setOnClickListener(this);
    }


    @Override
    public void onClick (View v){
        if (v == mAboutView) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        if (v == mButtonQuery) {
//            String query = mEditTextQuery.getText().toString();
//            if(!(query).equals("")) {
//                addToSharedPreferences(query);
//            }
            Intent intent = new Intent(MainActivity.this, GameListActivity.class);
//            intent.putExtra("query", query);
            startActivity(intent);
        }
        if(v == mSavedGameButton) {
            Intent intent = new Intent(MainActivity.this, SavedGameListActivity.class);
            startActivity(intent);
        }
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

//    private void addToSharedPreferences(String query) {
//      mEditor.putString(Constants.PREFERENCES_QUERY_KEY, query).apply();
//    }
}
