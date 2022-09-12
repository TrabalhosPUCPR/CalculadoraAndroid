package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    enum OPERATORS {
        PLUS("+"),
        MINUS("-"),
        MULTI("x"),
        DIV("÷"),
        MOD("%"),
        EQUAL("="),
        DOT(".");

        String value;

        OPERATORS(String s) {
            this.value = s;
        }
    }

    List<Button> calc_numberButtons;
    List<Button> calc_operatorButtons;
    List<Button> calc_mainButtons;

    TextView mainText;
    TextView secondText;

    Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainEq);
        secondText = findViewById(R.id.firstEq);
        calculator = new Calculator();

        calc_numberButtons = new ArrayList<>();
        calc_operatorButtons = new ArrayList<>();
        calc_mainButtons = new ArrayList<>();

        calc_numberButtons.add(findViewById(R.id.calc_zero));
        calc_numberButtons.add(findViewById(R.id.calc_1));
        calc_numberButtons.add(findViewById(R.id.calc_2));
        calc_numberButtons.add(findViewById(R.id.calc_3));
        calc_numberButtons.add(findViewById(R.id.calc_4));
        calc_numberButtons.add(findViewById(R.id.calc_5));
        calc_numberButtons.add(findViewById(R.id.calc_6));
        calc_numberButtons.add(findViewById(R.id.calc_7));
        calc_numberButtons.add(findViewById(R.id.calc_8));
        calc_numberButtons.add(findViewById(R.id.calc_9));
        
        calc_operatorButtons.add(findViewById(R.id.calc_plus));
        calc_operatorButtons.add(findViewById(R.id.calc_minus));
        calc_operatorButtons.add(findViewById(R.id.calc_multiplication));
        calc_operatorButtons.add(findViewById(R.id.calc_division));
        calc_operatorButtons.add(findViewById(R.id.calc_mod));

        calc_mainButtons.add(findViewById(R.id.calc_AC));
        calc_mainButtons.add((findViewById(R.id.calc_equals)));
        calc_mainButtons.add(findViewById(R.id.calc_dot));

        for(int i = 0; i < calc_numberButtons.size(); i++){
            int finalI = i;
            calc_numberButtons.get(i).setOnClickListener(view -> numberClicked(finalI));
        }
        for(int i = 0; i < calc_operatorButtons.size(); i++){
            int finalI = i;
            calc_operatorButtons.get(i).setOnClickListener(view -> operatorClicked(finalI));
        }

        calc_mainButtons.get(0).setOnClickListener(view -> {
            calculator.reset();
            resetView();
        });
        calc_mainButtons.get(1).setOnClickListener(view -> {
            if(calculator.operator == null){
                return;
            }
            resetView();
            secondText.setText(stringfy(calculator.getFirstNumber()) + " " + calculator.operator.value + " " + stringfy(calculator.getSecondNumber()));
            mainText.setText(OPERATORS.EQUAL.value + " " + stringfy(calculator.calculate()));
        });
        calc_mainButtons.get(2).setOnClickListener(view -> {
            calculator.doubleMode = true;
            mainText.setText(mainText.getText() + OPERATORS.DOT.value);
        });
    }

    void numberClicked(int number){
        calculator.addNumberDigit(number);
        updateView();
    }

    void operatorClicked(int op){
        calculator.setOperator(OPERATORS.values()[op]);
        updateView();
        mainText.setText("");
    }

    void updateView(){
        if (calculator.currentNumber){
            secondText.setText(stringfy(calculator.getFirstNumber()) + " " + calculator.operator.value);
            mainText.setText(stringfy(calculator.getSecondNumber()));
        }else{
            secondText.setText("");
            mainText.setText(stringfy(calculator.getFirstNumber()));
        }
    }

    private String stringfy(double number){
        if(number == (long)number) return String.valueOf((long)number);
        else return String.valueOf(number);
    }

    void resetView(){
        secondText.setText("");
        mainText.setText("");
    }

}











