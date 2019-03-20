package com.example.exampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Jajajajajajajajjjajajajajajajajajaj
public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate (View v){
        EditText numOneText = findViewById(R.id.firstNum);
        EditText numTwoText = findViewById(R.id.secondNum);

        String numOneStr = numOneText.getText().toString();
        String numTwoStr = numTwoText.getText().toString();

        Log.d(TAG, "User input: " + numOneStr);
        Log.d(TAG, "User input: " + numTwoStr);

        if (numOneStr.isEmpty())
            numOneStr = "0";
        if (numTwoStr.isEmpty())
            numTwoStr = "0";

        Double numOne = Double.parseDouble(numOneStr);
        Double numTwo = Double.parseDouble(numTwoStr);
        Double ans = numOne + numTwo;

        TextView answerText = (TextView) findViewById(R.id.answerLabel);
        answerText.setText(ans.toString());

        Toast toast = Toast.makeText(getApplicationContext(),
                "Adding values: " + numOne + " and " + numTwo, Toast.LENGTH_LONG);
        toast.show();

        Intent intent = new  Intent(this, AnswerActivity.class);
        intent.putExtra("answer", ans);
        startActivity(intent);
    }
}