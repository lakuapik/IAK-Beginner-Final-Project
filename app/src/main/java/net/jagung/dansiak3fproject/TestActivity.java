package net.jagung.dansiak3fproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import junit.framework.Test;

import java.lang.reflect.Array;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    int[] ishiharaArr;
    int currentIshihara = 2;
    int currentAnswer = 2;

    ImageView ishiharaImg;
    EditText answerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setTitle(getStringFromR(R.string.tesButaWarna));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ishiharaArr = new int[] {2, 3, 5, 6, 7, 8, 12, 15, 16, 26, 29, 35, 42, 45, 57, 73, 74, 96, 97};
        ishiharaImg = (ImageView) findViewById(R.id.mImageTest);
        answerEditText = (EditText) findViewById(R.id.mAnswer);
    }

    /**
     * onClick Button 'Berikutnya'. Will Change image of Ishihara.
     * @param v -> View
     */
    public void onClickBtnNext(View v){
        btnNext();
    }

    private void btnNext(){
        currentIshihara = getRandomIshihara(ishiharaArr);
        currentAnswer = currentIshihara;
        int newImg = getIsharaDrawable(currentIshihara);
        ishiharaImg.setImageResource(newImg);
        answerEditText.setText("");
    }

    public void onClickAnswer(View v){
        String result;
        String strUserAnswer = answerEditText.getText().toString();
        int userAnswer = 0;
        try {
            userAnswer = Integer.parseInt(strUserAnswer);
        } catch (NumberFormatException e){
            //Toast.makeText(this, "Ada yang tidak beres.", Toast.LENGTH_SHORT).show();
            //throw e;
        }

        if(strUserAnswer.equals("")){
            Toast.makeText(this, getStringFromR(R.string.emptyAnswerToast), Toast.LENGTH_SHORT).show();
        } else {

            if(userAnswer == currentAnswer){
                result = getStringFromR(R.string.correctAnswer);
            } else {
                result = getStringFromR(R.string.wrongAnswer);
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getStringFromR(R.string.resultAnswer));
            builder.setMessage(getStringFromR(R.string.userAnswer)+" "+result);
            builder.setPositiveButton(getStringFromR(R.string.testNext), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                    btnNext();
                }
            });
            builder.setNegativeButton(getStringFromR(R.string.testClose), new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                    dialogInterface.dismiss();
                }
            });
            AlertDialog hasil = builder.create();
            hasil.show();
        }
    }

    /**
     * get Random number of ishihara test;
     * @param array -> ishiharaArr
     * @return random number from array
     */
    public int getRandomIshihara(int[] array){
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    /**
     * get Ishihara Drawable Image
     * @param ishihara -> ishihara number
     * @return R.id.DrawableImage
     */
    public int getIsharaDrawable(int ishihara){
        String name = "ishihara_" + String.valueOf(ishihara);
        int img = getResources().getIdentifier(name, "drawable", getPackageName());
        return img;
    }

    public String getStringFromR(int id){
        return getResources().getString(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
