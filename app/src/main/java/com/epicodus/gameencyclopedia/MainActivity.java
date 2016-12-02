package com.epicodus.gameencyclopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.editTextQuery) EditText mEditTextQuery;
    @Bind(R.id.buttonQuery) Button mButtonQuery;
    @Bind(R.id.textAbout) TextView mAboutView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }
}
