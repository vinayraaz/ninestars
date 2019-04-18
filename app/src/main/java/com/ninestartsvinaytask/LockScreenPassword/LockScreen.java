package com.ninestartsvinaytask.LockScreenPassword;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ninestartsvinaytask.R;

public class LockScreen extends AppCompatActivity implements View.OnClickListener/*, TextWatcher*/ {
    EditText pin1_et, pin2_et, pin3_et, pin4_et,editText;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;
    LinearLayout textView11;
    String tvValue = "";
    String newValue ="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_pinconfirm);

        pin1_et = (EditText) findViewById(R.id.pin1_et);
        pin2_et = (EditText) findViewById(R.id.pin2_et);
        pin3_et = (EditText) findViewById(R.id.pin3_et);
        pin4_et = (EditText) findViewById(R.id.pin4_et);

        textView1 = (TextView) findViewById(R.id.tv1);
        textView2 = (TextView) findViewById(R.id.tv2);
        textView3 = (TextView) findViewById(R.id.tv3);
        textView4 = (TextView) findViewById(R.id.tv4);
        textView5 = (TextView) findViewById(R.id.tv5);
        textView6 = (TextView) findViewById(R.id.tv6);
        textView7 = (TextView) findViewById(R.id.tv7);
        textView8 = (TextView) findViewById(R.id.tv8);
        textView9 = (TextView) findViewById(R.id.tv9);
        textView10 = (TextView) findViewById(R.id.tv10);
        textView11 = (LinearLayout) findViewById(R.id.tv11);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView8.setOnClickListener(this);
        textView9.setOnClickListener(this);
        textView10.setOnClickListener(this);
        textView11.setOnClickListener(this);

        editText = (EditText)findViewById(R.id.ed);
/*
        pin1_et.addTextChangedListener(this);
        pin2_et.addTextChangedListener(this);
        pin3_et.addTextChangedListener(this);
        pin4_et.addTextChangedListener(this);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                tvValue = "1";
                break;
            case R.id.tv2:
                tvValue = "2";
                break;
            case R.id.tv3:
                tvValue = "3";
                break;
            case R.id.tv4:
                tvValue = "4";
                break;
            case R.id.tv5:
                tvValue = "5";
                break;
            case R.id.tv6:
                tvValue = "6";
                break;
            case R.id.tv7:
                tvValue = "7";
                break;
            case R.id.tv8:
                tvValue = "8";
                break;
            case R.id.tv9:
                tvValue = "9";
                break;
            case R.id.tv10:
                tvValue = "0";
                break;
            case R.id.tv11:
              //  newValue = pin1_et.getText().toString()+pin2_et.getText().toString()+pin3_et.getText().toString()+pin4_et.getText().toString();
               // Editable editable = newValue;
               // for(int i=4;i<=1;i--){
                    if(pin4_et.getText().toString().length()>0)
                        pin4_et.setText("");
                        else if(pin3_et.getText().toString().length()>0)
                        pin3_et.setText("");
                            else if(pin2_et.getText().toString().length()>0)
                        pin2_et.setText("");
                                else if(pin1_et.getText().toString().length()>0)
                        pin1_et.setText("");
              //  }
                tvValue="";
                /*String passwordStr = newValue;
                if (newValue.length() > 0) {
                    String newPasswordStr = new StringBuilder(passwordStr)
                            .deleteCharAt(passwordStr.length() - 1).toString();
                    //mEtPassword.setText(newPasswordStr);
                }*/
                break;
        }
        System.out.println("tvValue****" + tvValue);
        if (pin1_et.getText().toString().length() == 0)     //size as per your requirement
        {
            pin1_et.setText(tvValue);
        } else if (pin2_et.getText().toString().length() == 0)     //size as per your requirement
        {
            pin2_et.setText(tvValue);
        } else if (pin3_et.getText().toString().length() == 0)     //size as per your requirement
        {
            pin3_et.setText(tvValue);
        } else if (pin4_et.getText().toString().length() == 0)     //size as per your requirement
        {
            pin4_et.setText(tvValue);
        }
         newValue = pin1_et.getText().toString()+pin2_et.getText().toString()+pin3_et.getText().toString()+pin4_et.getText().toString();
        if (newValue.length()==4)
        {
            System.out.println("newVlaue***"+newValue);
        }

    }
/*
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.hashCode() == pin1_et.getText().hashCode()) {
            if (pin1_et.getText().toString().length() == 1)     //size as per your requirement
            {
                //pin1_et.setText(tvValue);
                pin2_et.requestFocus();
            }
        } else if (s.hashCode() == pin2_et.getText().hashCode()) {
            if (pin2_et.getText().toString().length() == 1)     //size as per your requirement
            {
                // pin2_et.setText(tvValue);
                pin3_et.requestFocus();
            }
        } else if (s.hashCode() == pin3_et.getText().hashCode()) {
            if (pin3_et.getText().toString().length() == 1)     //size as per your requirement
            {
                //  pin3_et.setText(tvValue);
                pin4_et.requestFocus();
            }
        } else if (s.hashCode() == pin4_et.getText().hashCode()) {
            if (pin4_et.getText().toString().length() == 1)     //size as per your requirement
            {
                //pin4_et.setText(tvValue);
                // pin2_et.requestFocus();
            }
        }
        //Do else something with input.


}*/
}
