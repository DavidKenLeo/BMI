package com.example.android.bmi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class Report extends AppCompatActivity implements View.OnClickListener {

    TextView result;
    TextView suggest;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report);
        openOptionsDialog();
        findViews();
        calcBMI();

    }


    void findViews() {

        result = (TextView) findViewById(R.id.result);
        suggest = (TextView) findViewById(R.id.suggest);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    void calcBMI() {

            Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

            String height = bundle.getString("KEY_HEIGHT");
            String weight = bundle.getString("KEY_WEIGHT");
            double h = Double.parseDouble(height) / 100;
            double w = Double.parseDouble(weight);
            double BMI = w / (h * h);

            System.out.println("BMI=" + BMI);
            TextView result = (TextView) findViewById(R.id.result);
            TextView suggest = (TextView) findViewById(R.id.suggest);

            DecimalFormat df = new DecimalFormat("0.00");
            result.setText("您的BMI值" + df.format(BMI));
            if (BMI > 25) {
                suggest.setText(R.string.advice3);
            } else if (BMI < 20) {
                suggest.setText(R.string.advice2);

            } else {
                suggest.setText(R.string.advice1);
            }
//            openOptionDialog();
//        }
//        void convertTemp() {
//
        }
    void openOptionsDialog(){
        Toast.makeText(this,"顯示Toast訊息",Toast.LENGTH_LONG).show();

        final ProgressDialog progressDialog =
                ProgressDialog.show(this, "處理中...", "請等一會，處理完畢會自動結束...");

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
//                    result.setTextColor(Color.BLUE);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        };
        thread.start();
    }

    @Override
    public void onClick(View view) {
        finish();
//        Intent intent = new Intent();
//        intent.setClass(Report.this,Bmi.class);
//        startActivity(intent);
    }


}
