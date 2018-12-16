package com.example.huni.walletmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.Date;
/** ez az osztaly felel a naptarban levo napok ra valo osszegek koltesiert*/
public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText editTextsum;
    private Button buttonsave;




    @Override
    /** ebben a fruggvenyben hozzom letre a view elemeket es kotom ossze azokat amelyeknek fontosak az adatai az az a calenderview a button es az edit tex mezo*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        this.calendarView = (CalendarView) findViewById(R.id.calendar_screen_calendarview);
        this.buttonsave = (Button) findViewById(R.id.calendar_screen_button_save_sum);
        this.editTextsum = (EditText) findViewById(R.id.calendar_screen_edittex_sum_input);
        // Az on click listenerben veszem le az adatokat a tovabbi mentest itt kell vegezni
        this.buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date(CalendarActivity.this.calendarView.getDate());
                System.out.println(date.toString()+"    "+CalendarActivity.this.editTextsum.getText().toString());
                CalendarActivity.this.finish();
            }
        });

    }

}
