package com.nordlex.paylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nordlex.paylist.data.CodesArray;

public class MainActivity extends AppCompatActivity {
    TextView explanationTextView;
    EditText numericCodesInputText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        explanationTextView = (TextView) findViewById(R.id.textViewExplanation);
        numericCodesInputText = (EditText) findViewById(R.id.editTextNumCodes);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numericCode = Integer.parseInt(numericCodesInputText.getText().toString());
                explanationTextView.setText(CodesArray.explanation(numericCode));
            }
        });

        numericCodesInputText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numericCode = Integer.parseInt(numericCodesInputText.getText().toString());
                explanationTextView.setText(CodesArray.explanation(numericCode));
            }
        });
    }
}