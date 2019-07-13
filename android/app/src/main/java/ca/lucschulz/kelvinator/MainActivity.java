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
import android.widget.TextView;
import android.widget.Toast;

import java.nio.Buffer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RadioButton btnCelsius = findViewById(R.id.radCelsius);
        btnCelsius.setChecked(true);

        configureInputValueHandling();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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


    private void configureInputValueHandling() {
        EditText inputValue = findViewById(R.id.txtInputTemp);
        inputValue.setText("0", TextView.BufferType.EDITABLE);

        final Activity act = this;

        inputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                double inputValue = Double.parseDouble(s.toString());
                UnitConverter uc = new UnitConverter(Units.K, inputValue);

                TextView tvC = findViewById(R.id.txtResult_C);
                TextView tvF = findViewById(R.id.txtResult_F);
                TextView tvK = findViewById(R.id.txtResult_K);

                tvC.setText(uc.getOutputC());
                tvF.setText(uc.getOutputF());
                tvK.setText(uc.getOutputK());


                Toast.makeText(act, s.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
