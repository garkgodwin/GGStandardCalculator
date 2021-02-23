package com.example.ggstandardcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ggstandardcalculator.adapter.StandardHistoryAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn1, btnZero2, btnZero, btnDot,
            btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnClear;

    ImageButton btnPercent, btnBackSpace, btnDivide, btnTimes, btnPlus, btnMinus, btnEquals, btnPlusMinus,
            btnHistory;

    TextView txtInput;



    ArrayList<String> inputs = new ArrayList<>();
    ArrayList<String> totals = new ArrayList<>();

    private double fn = 0;
    public double getFirstNumber(){
        return fn;
    }
    public void setFirstNumber(double fn){
        this.fn = fn;
    }
    public double sn = 0;
    public double getSecondNumber(){
        return sn;
    }
    public void setSecondNumber(double sn){
        this.sn = sn;
    }
    public int op = 0;
    public int getOperation(){
        return op;
    }
    public void setOperation(int op){
        this.op = op;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setOnclick();
        reset();
    }
    private void bindViews() {
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnZero = findViewById(R.id.btn_0);
        btnZero2 = findViewById(R.id.btn_double_0);
        btnDot = findViewById(R.id.btn_dot);

        btnDivide = findViewById(R.id.btn_divide);
        btnTimes = findViewById(R.id.btn_times);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnEquals = findViewById(R.id.btn_equals);
        btnPlusMinus = findViewById(R.id.btn_plusMinus);

        btnClear = findViewById(R.id.btn_clear);
        btnPercent = findViewById(R.id.btn_percent);
        btnBackSpace = findViewById(R.id.btn_backspace);

        btnHistory = findViewById(R.id.btn_history);

        txtInput = findViewById(R.id.txt_standard_input);
    }
    public void setTextLength() {
        if (txtInput == null || txtInput.getText().length() <= 11) {
            txtInput.setTextSize(50);
        } else {
            int txtLen = txtInput.getText().length();
            if (txtLen >= 12 && txtLen <= 17) {
                txtInput.setTextSize(30);
            } else if (txtLen >= 18 && txtLen <= 30) {
                txtInput.setTextSize(20);
            }
        }
    }
    private void setOnclick() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked(1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked(3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked(4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked(5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked(6);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked(7);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked(8);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked(9);
            }
        });
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String values[] = txtInput.getText().toString().split("\\s");
                if(!TextUtils.isEmpty(txtInput.getText())) {
                    if (values.length == 1) {
                        //means first num only
                        if (Double.parseDouble(values[0]) != 0) {
                            txtInput.setText(txtInput.getText() + "0");
                        }
                        else if(values[0].contains(".")){
                            txtInput.setText(txtInput.getText() + "0");
                        }
                    } else if (values.length == 2) {
                        //means no value yet for second number
                        txtInput.setText(txtInput.getText() + "0");
                    } else if (values.length == 3) {
                        //means there is second number
                        if (Double.parseDouble(values[2]) != 0) {
                            // means it is not 0
                            txtInput.setText(txtInput.getText() + "0");
                        }
                        else if(values[2].contains(".")){
                            txtInput.setText(txtInput.getText() + "0");
                        }
                    }
                    setTextLength();
                }
                else{
                    setTextLength();
                    txtInput.setText(txtInput.getText() + "0");
                }
            }
        });
        btnZero2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String values[] = txtInput.getText().toString().split("\\s");
                if(!TextUtils.isEmpty(txtInput.getText())) {
                    if (values.length != 3 && values.length != 2) {
                        //means first num only
                        if (Double.parseDouble(values[0]) != 0) {
                            setTextLength();
                            txtInput.setText(txtInput.getText() + "00");
                        }
                        else if(values[0].contains(".")){
                            txtInput.setText(txtInput.getText() + "00");
                        }
                    }
                    else if (values.length == 3) {
                        //means there is second number
                        if (Double.parseDouble(values[2]) != 0) {
                            // means it is not 0
                            txtInput.setText(txtInput.getText() + "00");
                        }
                        else if(values[2].contains(".")){
                            txtInput.setText(txtInput.getText() + "00");
                        }
                    }
                    setTextLength();
                }
            }
        });

        btnPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtInput.getText())){
                    Toast.makeText(MainActivity.this, "Cannot change the symbol of nothingness.", Toast.LENGTH_SHORT).show();
                }
                else{
                    String values[] = txtInput.getText().toString().split("\\s");
                    if(values.length == 1){
                        if(Double.parseDouble(values[0]) == 0){
                            Toast.makeText(MainActivity.this, "Zero is your origin.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(values[0].contains("-")){
                            values[0] = values[0].substring(1);
                        }
                        else{
                            values[0] = "-" + values[0];
                        }
                        txtInput.setText(values[0]);
                    }
                    else if(values.length == 2){
                        //means values[2] is empty
                        Toast.makeText(MainActivity.this, "Cannot change the symbol of nothingness.", Toast.LENGTH_SHORT).show();
                    }
                    else if(values.length == 3){
                        if(Double.parseDouble(values[2]) == 0){
                            Toast.makeText(MainActivity.this, "Zero is your origin.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(values[2].contains("-")){
                            values[2] = values[2].substring(1);
                        }
                        else{
                            values[2] = "-" + values[2];
                        }
                        txtInput.setText(values[0] + " " + values[1] +" "+values[2]);
                    }
                }
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String values[] = txtInput.getText().toString().split("\\s");
                if(!TextUtils.isEmpty(txtInput.getText())) {
                    if (values.length != 3 && values.length != 2) {
                        //means first num only
                        if (!values[0].contains(".")) {
                            txtInput.setText(txtInput.getText() + ".");
                        }
                    }
                    else if(values.length == 2){
                        txtInput.setText(txtInput.getText().toString() + ".");
                    }
                    else if (values.length == 3) {
                        //means there is second number
                        if (!values[2].contains(".")) {
                            // means it is not 0
                            txtInput.setText(txtInput.getText() + ".");
                        }
                    }
                    setTextLength();
                }
                else{
                    txtInput.setText(".");
                }
            }
        });


        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String values[] = txtInput.getText().toString().split("\\s");
                if (values.length == 3) {
                    //set the second number to be the percentage of the first nubmer
                    double second = Double.parseDouble(values[2])/100;
                    second = fn * second;
                    txtInput.setText(values[0] +" "+ values[1] +" " + second);

                    setTextLength();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please make sure you have a number to percent.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationClicked(1);
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationClicked(2);
            }
        });
        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationClicked(3);
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationClicked(4);
            }
        });
        btnEquals.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtInput.getText())){
                    Toast.makeText(MainActivity.this, "Please make sure you have an input to equate.", Toast.LENGTH_SHORT).show();
                }
                else{
                    String values[] = txtInput.getText().toString().split("\\s");
                    if(values.length == 3){
                        sn = Double.parseDouble(values[2]);
                        //step 2: calculate total
                        double total = 0;
                        if(op == 1){
                            total = fn + sn;
                        }
                        if(op == 2){
                            total = fn - sn;
                        }
                        if(op == 3){
                            total = fn * sn;
                        }
                        if(op == 4){
                            if(sn == 0){
                                Toast.makeText(MainActivity.this,"CANNOT DIVIDE BY ZERO",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else{
                                total = fn / sn;
                            }
                        }
                        fn = total;
                        //step 3: send to history
                        inputs.add(txtInput.getText().toString()); //add to array for history
                        totals.add(total+"");
                        //step 4: change text to total and operation click
                        if(fn%1== 0) { //this condition will check if the number is whole or not
                            DecimalFormat df = new DecimalFormat("0.#");
                            txtInput.setText(df.format(total));
                        }
                        else {
                            txtInput.setText(total + "");
                        }
                        //new operation
                        op = 0;
                    }
                    else if(values.length == 2){
                        Toast.makeText(MainActivity.this, "You have no second number to operate in.", Toast.LENGTH_SHORT).show();
                    }
                    else if(values.length == 1){
                        Toast.makeText(MainActivity.this, "You have no operator and second number.", Toast.LENGTH_SHORT).show();
                    }
                    setTextLength();
                }
            }
        });



        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtInput.getText())){
                    Toast.makeText(MainActivity.this, "You cannot clear nothingness.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    reset();
                }
            }
        });
        btnBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtInput.getText())){
                    Toast.makeText(MainActivity.this, "You cannot erase nothingness.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String values[] = txtInput.getText().toString().split("\\s");
                if(values.length == 3){
                    txtInput.setText(txtInput.getText().toString().substring(0, txtInput.getText().toString().length()-1));
                }
                else if(values.length == 2){
                    txtInput.setText(txtInput.getText().toString().substring(0, txtInput.getText().toString().length()-3));
                    op = 0;
                }
                else if(values.length == 1){
                    txtInput.setText(txtInput.getText().toString().substring(0, txtInput.getText().toString().length()-1));
                }
                setTextLength();
            }
        });


        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputs.size() == 0){
                    Toast.makeText(MainActivity.this, "You have no history.", Toast.LENGTH_SHORT).show();
                }
                else {
                    LayoutInflater inflater = (LayoutInflater)
                            MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.pop_up_standard_calculator, null);
                    PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                    popupWindow.setOutsideTouchable(false);
                    RecyclerView rvHistory = popupView.findViewById(R.id.rvStandardHistory);
                    Button btnCancel = popupView.findViewById(R.id.btn_cancel_pop);
                    StandardHistoryAdapter adapter = new StandardHistoryAdapter(inputs, totals,
                            MainActivity.this, popupWindow);
                    rvHistory.setAdapter(adapter);
                    rvHistory.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });
                }
            }
        });
    }

    private void numberClicked(int num){
        if(!TextUtils.isEmpty(txtInput.getText())) {
            if (!replaceZero(num)) {
                txtInput.setText(txtInput.getText().toString() + num);
            }
        }
        else{
            txtInput.setText(txtInput.getText().toString() + num);
        }
        setTextLength();
    }
    private boolean replaceZero(int num) {
        String values[] = txtInput.getText().toString().split("\\s");
        if (values.length != 3 && values.length != 2) {
            //means first num only
            if(values[0].contains(".")){
                txtInput.setText(txtInput.getText().toString());
                return false;
            }
            else{
                if (Double.parseDouble(values[0]) == 0) {
                    txtInput.setText(num+"");
                    return true;
                }
            }
        } else if (values.length == 2) {
            return false;
        } else if (values.length == 3) {
            //means there is second number
            if(values[2].contains(".")){
                txtInput.setText(txtInput.getText().toString());
                return false;
            }
            else {
                if (Double.parseDouble(values[2]) == 0) {
                    txtInput.setText(fn + " " +
                            (op == 1 ? "+ " :
                                    op == 2 ? "- " :
                                            op == 3 ? "x " :
                                                    op == 4 ? "÷ " : "ERROR OP ") + num);
                    return true;
                }
            }
        }
        return false;
    }

    private void operationClicked(int op){
        String emptyStringMessage = "Please make sure there is any number to ";
        String operation = " ";
        switch (op) {
            case 1:
                emptyStringMessage += "add(+).";
                operation += "+ ";
                break;
            case 2:
                emptyStringMessage += "deduct(-).";
                operation += "- ";
                break;
            case 3:
                emptyStringMessage += "multiply(x).";
                operation += "x ";
                break;
            case 4:
                emptyStringMessage += "divide(÷).";
                operation += "÷ ";
                break;
        }
        if (TextUtils.isEmpty(txtInput.getText())) {
            Toast.makeText(MainActivity.this, emptyStringMessage, Toast.LENGTH_SHORT).show();
        }
        else {
            String input = txtInput.getText().toString();
            if (input.endsWith(" + ") || input.endsWith(" - ") || input.endsWith(" x ") || input.endsWith(" ÷ ")) {
                this.op = op;
                if(fn%1== 0){ //this condition will check if the number is whole or not
                    DecimalFormat df = new DecimalFormat("0.#");
                    txtInput.setText(df.format(fn) + operation);
                }
                else{
                    txtInput.setText(fn + operation);
                }
            } else {
                if (this.op == 0) {
                    //no second number exist yet
                    setTextLength();
                    this.op = op; //means add
                    fn = Double.parseDouble(txtInput.getText().toString());
                    if(fn%1== 0) { //this condition will check if the number is whole or not
                        DecimalFormat df = new DecimalFormat("0.#");
                        txtInput.setText(df.format(fn) + operation);
                    }
                    else {
                        txtInput.setText(fn + operation);
                    }
                }
                else {
                    String values[] = txtInput.getText().toString().split("\\s");
                    if(values.length == 3){
                        sn = Double.parseDouble(values[2]);
                        double total = 0;
                        if(this.op == 1){
                            total = fn + sn;
                        }
                        if(this.op == 2){
                            total = fn - sn;
                        }
                        if(this.op == 3){
                            total = fn * sn;
                        }
                        if(this.op == 4){
                            if(sn == 0){
                                Toast.makeText(MainActivity.this,"CANNOT DIVIDE BY ZERO",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else{
                                total = fn / sn;
                            }
                        }
                        fn = total;
                        //step 3: send to history
                        inputs.add(txtInput.getText().toString()); //add to array for history
                        totals.add(total+"");
                        //step 4: change text to total and operation click
                        if(fn%1== 0) { //this condition will check if the number is whole or not
                            DecimalFormat df = new DecimalFormat("0.#");
                            txtInput.setText(df.format(total) + operation);
                        }
                        else {
                            txtInput.setText(total + operation);
                        }
                        //new operation
                        this.op = op;
                    }
                }
            }
        }

    }
    private void reset(){
        op = 0;
        fn = 0;
        sn = 0;
        txtInput.setText("");
        setTextLength();
    }
}