package com.example.museums;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Objects;

public class Price extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DecimalFormat df = new DecimalFormat("0.00");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Toast.makeText(getApplicationContext(), "Maximum of 5 tickets for each!", Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        final String value = intent.getStringExtra("museum");
        TextView adult = (TextView)findViewById(R.id.adultPrice);
        TextView senior = (TextView)findViewById(R.id.seniorPrice);
        TextView student = (TextView)findViewById(R.id.studentPrice);
        ImageButton img = (ImageButton)findViewById(R.id.imageButton);

        final double tax = 0.06625;
        double adult_price;
        double senior_price;
        double student_price;

        switch(value){
            case "Montclair Art Museum":
                adult.setText("$15");
                adult_price = 15;
                senior.setText("$12");
                senior_price = 12;
                student.setText("$12");
                student_price = 12;
                img.setImageResource(R.drawable.montclair);
                this.setTitle("Montclair Art Museum");
                break;
            case "Insectropolis":
                adult.setText("$10");
                adult_price = 10;
                senior.setText("$10");
                senior_price = 10;
                student.setText("$10");
                student_price = 10;
                img.setImageResource(R.drawable.insectropolis);
                this.setTitle("Insectropolis");
                break;
            case "Hunterdon Art Museum":
                adult.setText("$7");
                adult_price = 7;
                senior.setText("$5");
                senior_price = 5;
                student.setText("$5");
                student_price = 5;
                img.setImageResource(R.drawable.hunterdon);
                this.setTitle("Hunterdon Art Museum");
                break;
            default:
                adult.setText("$24.99");
                adult_price = 24.99;
                senior.setText("$21.99");
                senior_price = 21.99;
                student.setText("$19.99");
                student_price = 19.99;
                img.setImageResource(R.drawable.liberty);
                this.setTitle("Liberty Science Center");
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ticketNumber, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner adult_spin = (Spinner) findViewById(R.id.adultTicket);
        adult_spin.setAdapter(adapter);
        Spinner senior_spin = (Spinner) findViewById(R.id.seniorTicket);
        senior_spin.setAdapter(adapter);
        Spinner student_spin = (Spinner) findViewById(R.id.studentTicket);
        student_spin.setAdapter(adapter);

        img.setOnClickListener(v -> {
            String site;
            switch(value){
                case "Montclair Art Museum":
                    site = "https://www.montclairartmuseum.org/";
                    break;
                case "Insectropolis":
                    site = "http://insectropolis.com/";
                    break;
                case "Hunterdon Art Museum":
                    site = "https://hunterdonartmuseum.org/";
                    break;
                default:
                    site = "https://lsc.org/";
            }
            Intent intent1 = new Intent();
            intent1.setAction(Intent.ACTION_VIEW);
            intent1.addCategory(Intent.CATEGORY_BROWSABLE);
            intent1.setData(Uri.parse(site));
            startActivity(intent1);
        });

        Button calculate = (Button)findViewById(R.id.button);
        double finalAdult_price = adult_price;
        double finalSenior_price = senior_price;
        double finalStudent_price = student_price;
        calculate.setOnClickListener((View.OnClickListener) v -> {
            int adult_ticket;
            int senior_ticket;
            int student_ticket;
            adult_ticket = adult_spin.getSelectedItemPosition();
            senior_ticket = senior_spin.getSelectedItemPosition();
            student_ticket = student_spin.getSelectedItemPosition();
            double total = (finalAdult_price * adult_ticket) + (finalSenior_price * senior_ticket) + (finalStudent_price * student_ticket);
            TextView ticket = (TextView)findViewById(R.id.ticketprice);
            ticket.setText("$"+df.format(total));
            double salesTax = total * tax;
            TextView ticketSales = (TextView)findViewById(R.id.ticketprice3);
            ticketSales.setText("$"+df.format(salesTax));
            double totalPrice = total + salesTax;
            TextView ticketTotal = (TextView)findViewById(R.id.ticketprice2);
            ticketTotal.setText("$"+df.format(totalPrice));
        });

    }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId() == android.R.id.home) {
           finish();
           return true;
       }
       return super.onOptionsItemSelected(item);
   }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
