package com.example.huni.walletmanager.NavigationDrawerActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.huni.walletmanager.MainActivity;
import com.example.huni.walletmanager.R;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

public class CurrencyPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_picker);

        CountryCurrencyButton button = (CountryCurrencyButton) findViewById(R.id.button);
        button.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(CurrencyPickerActivity.this,
                            String.format("name: %s\ncode: %s", country.getName(), country.getCode())
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CurrencyPickerActivity.this,
                            String.format("Country: %s\nCurrency: %s", country.getName(), country.getCurrency().getSymbol())
                            , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSelectCurrency(Currency currency) {

            }
        });
    }
}
