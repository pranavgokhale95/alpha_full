package com.example.pranav.b3_calculator;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etNum1;
    EditText etNum2;
    TextView tvAns;

    //declare MS variable
    double msVar = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.btnSin)).setOnClickListener(this);
        (findViewById(R.id.btnCos)).setOnClickListener(this);
        (findViewById(R.id.btnTan)).setOnClickListener(this);
        (findViewById(R.id.btnSqrt)).setOnClickListener(this);

        (findViewById(R.id.btnAdd)).setOnClickListener(this);
        (findViewById(R.id.btnSub)).setOnClickListener(this);
        (findViewById(R.id.btnMul)).setOnClickListener(this);
        (findViewById(R.id.btnDiv)).setOnClickListener(this);
        (findViewById(R.id.btnMS)).setOnClickListener(this);
        (findViewById(R.id.btnMR)).setOnClickListener(this);


        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        tvAns = (TextView) findViewById(R.id.tvAns);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);

        }

    }

    // dont call roundThree for b3
    // means - just to return Math.sin(......
    // return a-b etc
    // b3 requires some extended floating point arithmatic, we are not rounding doubles in b3
    // rest of the code remains same. skip file reading code in b3. take input from user.

    public static double getSin(double s) {
        return (Math.sin(Math.toRadians(s)));
    }

    public static double getCos(double s) {
        return (Math.cos(Math.toRadians(s)));

    }

    public static double getTan(double s) {
        return (Math.tan(Math.toRadians(s)));
    }

    public static double getSqrt(double s) {
        return (Math.sqrt(s));
    }

    public static double add(double a, double b) {
        return (a + b);
    }

    public static double sub(double a, double b) {
        return (a - b);
    }

    public static double mul(double a, double b) {
        return (a * b);
    }

    public static double div(double a, double b) {
        return (a / b);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;

        double num1;
        double num2;

        switch (btn.getText().toString()) {
            case "SIN":
                num1 = Double.parseDouble(etNum1.getText().toString());
                tvAns.setText(" " + getSin(num1));
                etNum1.setText("");
                etNum2.setText("");
                break;

            case "COS":
                num1 = Double.parseDouble(etNum1.getText().toString());
                tvAns.setText(" " + getCos(num1));
                etNum1.setText("");
                etNum2.setText("");
                break;


            case "TAN":
                num1 = Double.parseDouble(etNum1.getText().toString());
                tvAns.setText(" " + getTan(num1));
                etNum1.setText("");
                etNum2.setText("");
                break;

            case "SQRT":
                num1 = Double.parseDouble(etNum1.getText().toString());
                tvAns.setText(" " + getSqrt(num1));
                etNum1.setText("");
                etNum2.setText("");
                break;

            case "Add":
                num1 = Double.parseDouble(etNum1.getText().toString());
                num2 = Double.parseDouble(etNum2.getText().toString());
                tvAns.setText("" + add(num1, num2));
                etNum1.setText("");
                etNum2.setText("");
                break;

            case "Sub":
                num1 = Double.parseDouble(etNum1.getText().toString());
                num2 = Double.parseDouble(etNum2.getText().toString());
                tvAns.setText("" + sub(num1, num2));
                etNum1.setText("");
                etNum2.setText("");
                break;

            case "Mul":
                num1 = Double.parseDouble(etNum1.getText().toString());
                num2 = Double.parseDouble(etNum2.getText().toString());
                tvAns.setText("" + mul(num1, num2));
                etNum1.setText("");
                etNum2.setText("");
                break;

            case "Div":
                num1 = Double.parseDouble(etNum1.getText().toString());
                num2 = Double.parseDouble(etNum2.getText().toString());
                tvAns.setText("" + div(num1, num2));
                etNum1.setText("");
                etNum2.setText("");
                break;

            case "MS":
                if(tvAns.getText().length()!=0){
                    msVar = Double.parseDouble(tvAns.getText().toString());
                }
                break;
            case "MR":
                etNum1.setText("" + msVar);
                break;
        }

    }
}
