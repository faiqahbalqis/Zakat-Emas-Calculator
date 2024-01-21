package com.example.zakatfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorPage extends AppCompatActivity {

    EditText goldWeightEditText, currentValueEditText;
    TextView q1TextView, q2TextView, q3TextView, q4TextView;
    RadioButton keepRadioButton, wearRadioButton;
    double goldWeight, currentValue, x, total, uruf, payable, totalZakat;
    String stringWeight, stringValue, loadWeight;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_page);

        sharedPref = this.getSharedPreferences("gWeight", Context.MODE_PRIVATE);
    }

    public void calculateButtonClick (View v) {
        try {

            goldWeightEditText = findViewById(R.id.goldWeightEditText);
            currentValueEditText = findViewById(R.id.currentValueEditText);
            keepRadioButton = findViewById(R.id.keepRadioButton);
            wearRadioButton = findViewById(R.id.wearRadioButton);
            q1TextView = findViewById(R.id.q1TextView);
            q2TextView = findViewById(R.id.q2TextView);
            q3TextView = findViewById(R.id.q3TextView);
            q4TextView = findViewById(R.id.q4TextView);
            goldWeight = Double.parseDouble(goldWeightEditText.getText().toString());
            currentValue = Double.parseDouble(currentValueEditText.getText().toString());

            if(keepRadioButton.isChecked()) {
                x = 85;
            }
            else if(wearRadioButton.isChecked()) {
                x = 200;
            }

            total = goldWeight * currentValue;
            uruf = goldWeight - x;

            q3TextView.setText("Uruf : " + uruf + "g");

            if (uruf <= 0) {
                uruf = 0;
            }

            payable = uruf * currentValue;
            totalZakat = payable * 0.025;

            q1TextView.setText("Total value of gold : RM" + String.format("%.2f", total));
            q2TextView.setText("Total gold value that is zakat payable : RM" + String.format("%.2f", payable));
            q4TextView.setText("Total zakat : RM" + String.format("%.2f", totalZakat));

            stringWeight = Double.toString(goldWeight);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("gWeight", stringWeight);
            editor.apply();

            loadWeight = sharedPref.getString("gWeight", "");
            goldWeight = Double.parseDouble(loadWeight);

        }
        catch (NumberFormatException nfe) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show();
        }
    }

    public void clearButtonClick (View v) {
        goldWeightEditText.setText("");
        currentValueEditText.setText("");
    }

}