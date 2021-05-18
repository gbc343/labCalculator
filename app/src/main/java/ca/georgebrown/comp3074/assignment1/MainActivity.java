package ca.georgebrown.comp3074.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText hoursWorked;
    private EditText hourlyWage;
    private TextView result;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hoursWorked = findViewById((R.id.hours_worked));
        hourlyWage = findViewById(R.id.hourly_wage);
        result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h = hoursWorked.getText().toString();
                String w = hourlyWage.getText().toString();
                double pay;
                double overtimePay;
                double totalPay;
                double tax;
                DecimalFormat df = new DecimalFormat("0.00");

                if((h == null || h.isEmpty())||(w == null || w.isEmpty())){
                    String report = "The inputs cannot be empty";
                    result.setText(report);
                }
                else {

                    if(isNumeric(h) == false || isNumeric(w) == false) {
                        String report = "The inputs must be number";
                        result.setText(report);
                    }


                    //  double hours = Integer.parseInt(hoursWorked.getText().toString());
                    //   double wage = Integer.parseInt(hourlyWage.getText().toString());

                    else {
                        double hours = Double.parseDouble(h);
                        double wage = Double.parseDouble(w);

                        if (hours <= 40) {
                            pay = hours * wage;
                            overtimePay = 0;
                            totalPay = pay;
                            tax = pay * 0.18;

                            String report = "Pay: $" +df.format( pay) + "\n" + "Overtime pay: $" + df.format(overtimePay) + "\n" + "Total Pay: $" +df.format( totalPay) + "\n" + "Total Tax: $" +df.format( tax);
                            result.setText(report);
                        } else {
                            pay = 40 * wage;
                            overtimePay = (hours - 40) * wage * 1.5;
                            totalPay = pay + overtimePay;
                            tax = totalPay * 0.18;
                            String report = "Pay: $" + df.format(pay) + "\n" + "Overtime pay: $" + df.format(overtimePay) + "\n" + "Total Pay: $" + df.format(totalPay) + "\n" + "Total Tax: $" +df.format( tax);
                            result.setText(report);
                        }
                    }

                }

            }
        });



    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    private void openActivity(){
        Intent start = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(start);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.second_activity:
                openActivity();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

}