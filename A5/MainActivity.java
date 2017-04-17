package com.example.pranav.a5_calculator;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etNum1;
    EditText etNum2;
    TextView tvAns;

    public static double getSin(double s) {
        return roundThree(Math.sin(Math.toRadians(s)));
    }

    public static double getCos(double s) {
        return roundThree(Math.cos(Math.toRadians(s)));

    }

    public static double getTan(double s) {
        return roundThree(Math.tan(Math.toRadians(s)));
    }

    public static double getSqrt(double s) {
        return roundThree(Math.sqrt(s));
    }

    public static double add(double a, double b) {
        return roundThree(a + b);

    }

    public static double sub(double a, double b) {
        return roundThree(a - b);

    }

    public static double mul(double a, double b) {
        return roundThree(a * b);

    }

    public static double div(double a, double b) {
        return roundThree(a / b);

    }

    public static double roundThree(double val) {
        int places = 3;

        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(val);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.btnSin)).setOnClickListener(this);
        (findViewById(R.id.btnCos)).setOnClickListener(this);
        (findViewById(R.id.btnTan)).setOnClickListener(this);
        (findViewById(R.id.btnSqrt)).setOnClickListener(this);
        (findViewById(R.id.btnRead)).setOnClickListener(this);

        (findViewById(R.id.btnAdd)).setOnClickListener(this);
        (findViewById(R.id.btnSub)).setOnClickListener(this);
        (findViewById(R.id.btnMul)).setOnClickListener(this);
        (findViewById(R.id.btnDiv)).setOnClickListener(this);


        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        tvAns = (TextView) findViewById(R.id.tvAns);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0);

        }
        
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;

        try {
            switch (btn.getText().toString()) {
                case "Read Input":
                    readInput();
                    break;


                case "SIN":
                    tvAns.setText(btn.getText().toString() + " " + getSin(Double.parseDouble(etNum1.getText().toString())));
                    etNum1.setText("");
                    etNum2.setText("");
                    break;

                case "COS":
                    tvAns.setText(btn.getText().toString() + " " + getCos(Double.parseDouble(etNum1.getText().toString())));
                    etNum1.setText("");
                    etNum2.setText("");
                    break;


                case "TAN":
                    tvAns.setText(btn.getText().toString() + " " + getTan(Double.parseDouble(etNum1.getText().toString())));
                    etNum1.setText("");
                    etNum2.setText("");
                    break;

                case "SQRT":
                    tvAns.setText(btn.getText().toString() + " " + getSqrt(Double.parseDouble(etNum1.getText().toString())));
                    etNum1.setText("");
                    etNum2.setText("");
                    break;

                case "Add":
                    tvAns.setText("" + add(Double.parseDouble(etNum1.getText().toString()), Double.parseDouble(etNum2.getText().toString())));
                    etNum1.setText("");
                    etNum2.setText("");
                    break;

                case "Sub":
                    tvAns.setText("" + sub(Double.parseDouble(etNum1.getText().toString()), Double.parseDouble(etNum2.getText().toString())));
                    etNum1.setText("");
                    etNum2.setText("");
                    break;

                case "Mul":
                    tvAns.setText("" + mul(Double.parseDouble(etNum1.getText().toString()), Double.parseDouble(etNum2.getText().toString())));
                    etNum1.setText("");
                    etNum2.setText("");
                    break;

                case "Div":
                    tvAns.setText("" + div(Double.parseDouble(etNum1.getText().toString()), Double.parseDouble(etNum2.getText().toString())));
                    etNum1.setText("");
                    etNum2.setText("");
                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }


    }

    private void readInput() {
        try {
            File file = new File("/sdcard/input.xml");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String s;

            while ((s = bufferedReader.readLine()) != null) {
                if (s.contains("items"))
                    continue;

                if (!s.contains("item"))
                    continue;

                if (s.contains("item")) {
                    String input = s.split(">")[1].split("<")[0];

                    if (etNum1.getText().length() == 0) {
                        etNum1.setText(input);
                    } else {
                        etNum2.setText(input);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}