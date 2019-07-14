package ca.lucschulz.kelvinator;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    RadioButton radioC;
    RadioButton radioF;
    RadioButton radioK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        configureRadioGroup();
        configureInputValueHandling();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "info@tesscorp.ca", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void configureRadioGroup() {
        radioC = findViewById(R.id.radCelsius);
        radioF = findViewById(R.id.radFahrenheit);
        radioK = findViewById(R.id.radKelvin);

        radioC.setChecked(true);

        RadioGroup rg = findViewById(R.id.rGroup_Units);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                TextView currentUnitSymbol = findViewById(R.id.txt_CurrentUnitSymbol);

                if (radioC.isChecked()) {
                    currentUnitSymbol.setText(R.string.txtUnitNotation_C);
                }
                else if (radioF.isChecked()) {
                    currentUnitSymbol.setText(R.string.txtUnitNotation_F);
                }
                else if (radioK.isChecked()) {
                    currentUnitSymbol.setText(R.string.txtUnitNotation_K);
                }

                EditText editText = findViewById(R.id.txtInputTemp);
                String inputValueAsString = editText.getText().toString();

                if (!inputValueAsString.equals("") && !inputValueAsString.equals("-")) {
                    double inputValue = Double.parseDouble(inputValueAsString);
                    updateResults(inputValue);
                }
            }
        });
    }


    private void configureInputValueHandling() {
        EditText inputValue = findViewById(R.id.txtInputTemp);
        inputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") && !s.toString().equals("-")) {
                    double inputValue = Double.parseDouble(s.toString());
                    updateResults(inputValue);
                }
            }
        });
    }

    private void updateResults(double inputValue) {
        UnitConverter uc = null;

        if (radioC.isChecked()) {
            uc = new UnitConverter(Units.C, inputValue);
        }
        else if (radioF.isChecked()) {
            uc = new UnitConverter(Units.F, inputValue);
        }
        else if (radioK.isChecked()) {
            uc = new UnitConverter(Units.K, inputValue);
        }

        if (uc != null) {
            TextView tvC = findViewById(R.id.txtResult_C);
            TextView tvF = findViewById(R.id.txtResult_F);
            TextView tvK = findViewById(R.id.txtResult_K);

            tvC.setText(uc.getOutputC());
            tvF.setText(uc.getOutputF());
            tvK.setText(uc.getOutputK());
        }
    }
}
