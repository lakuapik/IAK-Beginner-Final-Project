package net.jagung.dansiak3fproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    CardView cardPengertian, cardPenyebab, cardPengelompokan, cardPembuktian, cardPengobatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardPengertian = (CardView) findViewById(R.id.cardPengertian);
        cardPengertian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article(getStringFromR(R.string.titlePengertian), getStringFromR(R.string.bodyPengertian));
            }
        });

        cardPenyebab = (CardView) findViewById(R.id.cardPenyebab);
        cardPenyebab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article(getStringFromR(R.string.titlePenyebab), getStringFromR(R.string.bodyPenyebab));
            }
        });

        cardPengelompokan = (CardView) findViewById(R.id.cardPengelompokan);
        cardPengelompokan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article(getStringFromR(R.string.titlePengelompokan), getStringFromR(R.string.bodyPengelompokan));
            }
        });

        cardPembuktian = (CardView) findViewById(R.id.cardPembuktian);
        cardPembuktian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article(getStringFromR(R.string.titlePembuktian), getStringFromR(R.string.bodyPembuktian));
            }
        });

        cardPengobatan = (CardView) findViewById(R.id.cardPengobatan);
        cardPengobatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article(getStringFromR(R.string.titlePengobatan), getStringFromR(R.string.bodyPengobatan));
            }
        });

    }

    public void onClickTes(View view){
        Intent tesIntent = new Intent(this, TestActivity.class);
        startActivity(tesIntent);
    }

    public void article(String title, String body){
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("ARTICLE_TITLE", title);
        intent.putExtra("ARTICLE_BODY", body);
        startActivity(intent);
    }

    public String getStringFromR(int id){
        return getResources().getString(id);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuBarAbout:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;
            case R.id.menuBarExit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getStringFromR(R.string.exitDialog));
                builder.setPositiveButton(getStringFromR(R.string.exitYes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton(getStringFromR(R.string.exitNo), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog quit = builder.create();
                quit.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce){
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getStringFromR(R.string.exitToast), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run(){
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }

}
