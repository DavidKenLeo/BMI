package com.example.android.bmi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;


public class Bmi extends AppCompatActivity {

    Button button;
    EditText fieldHeight;
    EditText fieldWeight;
//    TextView result;
//    TextView suggest;
    String[] items;
    int[] iconRes;
    ImageView ivDark;
    LinearLayout description;
    TextView tipText2;
    TextView tipText3;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

//        Button button2 = (Button) findViewById(R.id.convertTemp);
//        button2.setOnClickListener(listener);
//        TextView tv = (TextView)findViewById(R.id.submit);
        findViews();
        setListeners();
        init();
        playViewAnimation();

//                    openOptionDialog();


    }

    void init() {
        items = getResources().getStringArray(R.array.items);
        iconRes = new int[]{R.drawable.angry ,R.drawable.angry_bird_icon1 ,R.drawable.angry_bird_icon100 ,R.drawable.angry ,R.drawable.angry_bird_icon100};
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

    }

    void findViews() {
        button = (Button) findViewById(R.id.submit);
        fieldHeight = (EditText) findViewById(R.id.height);
        fieldWeight = (EditText) findViewById(R.id.weight);
        ivDark = (ImageView)findViewById(R.id.imageView);
        description = (LinearLayout)findViewById(R.id.description);
        tipText2 =(TextView) findViewById(R.id.tipText2);
                tipText3=(TextView) findViewById(R.id.tipText3);

//        result = (TextView) findViewById(R.id.result);
//        suggest = (TextView) findViewById(R.id.suggest);
    }

    void setListeners() {
        button.setOnClickListener(listener);
        description.setOnClickListener(descListener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override


        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(Bmi.this,Report.class);
            Bundle bundle = new Bundle();
            bundle.putString("KEY_HEIGHT", fieldHeight.getText().toString());
            bundle.putString("KEY_WEIGHT", fieldWeight.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);

        }

//        void calcBMI() {
//            EditText fieldHeight = (EditText) findViewById(R.id.height);
//            EditText fieldWeight = (EditText) findViewById(R.id.weight);
//            String height = fieldHeight.getText().toString();
//            String weight = fieldWeight.getText().toString();
//            double h = Double.parseDouble(height) / 100;
//            double w = Double.parseDouble(weight);
//            double BMI = w / (h * h);
//            System.out.println("BMI=" + BMI);
//            TextView result = (TextView) findViewById(R.id.result);
//            TextView suggest = (TextView) findViewById(R.id.suggest);
//
//            DecimalFormat df = new DecimalFormat("0.00");
//            result.setText("您的BMI值" + df.format(BMI));
//            if (BMI > 25) {
//                suggest.setText(R.string.advice3);
//            } else if (BMI < 20) {
//                suggest.setText(R.string.advice2);
//
//            } else {
//                suggest.setText(R.string.advice1);
//            }
//            openOptionDialog();
//        }
//        void convertTemp() {
//
//        }

    };


    void openOptionDialog() {
//        findDialogViews();
//        AlertDialog.Builder ab = new AlertDialog.Builder(Bmi.this);
////                ab.setTitle(getString(R.string.about_title));
////                ab.setMessage(R.string.about_msg);
////        ab.setPositiveButton("確認",dialogListener);
//////        System.out.println(R.string.ok);
////        ab.setNegativeButton("首頁",dialogListener);
//////        ab.setNegativeButton == R.string.nook;
//        ab.setView(root);
//        dialog = ab.show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.about_title);
        builder.setMessage(R.string.about_msg);
        builder.setNegativeButton("首頁",Dialoglistener);
        builder.show();
    }
    DialogInterface.OnClickListener Dialoglistener = new DialogInterface.OnClickListener(){


        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

//            Uri uri = Uri.parse(getString(R.string.homepageurl));
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
//            String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
//            Intent intent = new Intent();
//            intent.setAction(android.content.Intent.ACTION_VIEW);
//            File file = new File(baseDir+"/music/bgm.mp3");
//            intent.setDataAndType(Uri.fromFile(file), "audio/*");
//            startActivity(intent);
//            Uri mapUri = Uri.parse("geo:38.899533,-77.036476");
//
//            Intent intent = new Intent(Intent.ACTION_VIEW, mapUri);
//            startActivity(intent);
            }

    };


    View root;
    EditText et;
    Button confirm;
    AlertDialog dialog; //???

    void findDialogViews() {
        root = getLayoutInflater().inflate(R.layout.dialog_lay, null);
        et = (EditText) root.findViewById(R.id.editText);
        confirm = (Button) root.findViewById(R.id.button5);
        confirm.setOnClickListener(dialoglistener);
    }

    View.OnClickListener dialoglistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.out.println("按下自訂對話框的按鈕");
            dialog.dismiss();
        }
    };

//    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener(){
//
//        @Override
//        public void onClick(DialogInterface dialogInterface, int i) {
//            if(which== DialogInterface.BUTTON_POSITIVE)
//                System.out.println(R.string.ok);
//            else if(which == DialogInterface.BUTTON_NEGATIVE)
//                System.out.println(R.string.nook);
//        }
//
//    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.menu,menu);
        for (int i = 0; i < items.length; i++)
            menu.add(0, (i + 1) * 100, 0, items[i]).setIcon(iconRes[i]).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

//        menu.add(0, 100, 0, items[0]);
//        menu.add(0, 200, 0, items[1]);
//        menu.add(0, 300, 0, items[2]);
//        menu.add(0, 400, 0, items[3]);
//        menu.add(0, 500, 0, items[4]);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case 100:
            case 200:
            case 300:
            case 400:
            case 500:
                Toast.makeText(Bmi.this, item.getTitle(), Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



//        public static void onClick(View s )
//        {
//            System.out.println("按錯了");
//        }

void playViewAnimation(){
    Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim);
    ivDark.startAnimation(animation);

}
private  View.OnClickListener descListener = new View.OnClickListener(){
    int clickcount = 1;
    @Override
    public void onClick(View view) {
        if(clickcount ==1){
            tipText2.setVisibility(view.VISIBLE);
            clickcount++;

        }else if(clickcount ==2){
            tipText3.setVisibility(view.VISIBLE);
            clickcount++;

        }else
            description.setVisibility(View.GONE);
    }


};


}