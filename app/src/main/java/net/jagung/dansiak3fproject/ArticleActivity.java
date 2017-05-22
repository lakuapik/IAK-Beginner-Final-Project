package net.jagung.dansiak3fproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ArticleActivity extends AppCompatActivity {

    //String title;
    //String body;

    TextView titleText, bodyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        titleText = (TextView) findViewById(R.id.mTitleText);
        bodyText = (TextView) findViewById(R.id.mBodyText);

        Intent intentIn= getIntent();
        Bundle intentData = intentIn.getExtras();

        if(intentData != null){
            String title = (String) intentData.get("ARTICLE_TITLE");
            String body = (String) intentData.get("ARTICLE_BODY");

            setTitle(title);
            titleText.setText(title +" "+ getResources().getString(R.string.appText));
            bodyText.setText(body);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
