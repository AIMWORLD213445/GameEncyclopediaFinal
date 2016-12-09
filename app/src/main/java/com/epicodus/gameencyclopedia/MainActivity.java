package com.epicodus.gameencyclopedia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.editTextQuery) EditText mEditTextQuery;
    @Bind(R.id.buttonQuery) Button mButtonQuery;
    @Bind(R.id.textAbout) TextView mAboutView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mSharedPreferences.edit();

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
            String query = mEditTextQuery.getText().toString();
            if(!(query).equals("")) {
                addToSharedPreferences(query);
            }
            Intent intent = new Intent(MainActivity.this, GameListActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String query) {
      mEditor.putString(Constants.PREFERENCES_QUERY_KEY, query).apply();
    }
}
