package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {


    int quantity = 1; // declaring the quantity of coffee


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


/*
Method called when the Order button is clicked
*/

    public void submitOrder(View view) {

        EditText NameEditText = (EditText) findViewById(R.id.name_edit_text);
        String SelectedName = NameEditText.getText().toString();

        CheckBox creamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean creamCheckBoxState = creamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean addChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(creamCheckBoxState, addChocolate);
        String priceMessage = createOrderSummary(price, creamCheckBoxState, addChocolate, SelectedName);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(getString(R.string.email))); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject) + SelectedName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    /**
     * Method creates a summary of the order information and returns the text summary
     *
     * @param SelectedName       of the customer
     * @param addChocolate       or not?
     * @param creamCheckBoxState with cream or not?
     * @param price              calculated total price
     * @return text summary of the order
     */

    private String createOrderSummary(int price, boolean creamCheckBoxState, boolean addChocolate, String SelectedName) {

        String priceMessage = getString(R.string.name) + SelectedName;
        priceMessage = priceMessage + getString(R.string.add_cream) + creamCheckBoxState;
        priceMessage += getString(R.string.add_chocolate) + addChocolate;
        priceMessage = priceMessage + getString(R.string.quantity) + quantity;
        priceMessage += getString(R.string.total) + NumberFormat.getCurrencyInstance().format(price);
        priceMessage = priceMessage + getString(R.string.thank_you);
        return priceMessage;

    }

    /**
     * Method calculates the price of the order based on quantity
     *
     * @param creamCheckBoxState with cream or not?
     * @param addChocolate       or not?
     **/


    private int calculatePrice(boolean creamCheckBoxState, boolean addChocolate) {

        int basePrice = 5;

        if (creamCheckBoxState) {

            basePrice = basePrice + 1;
        }

        if (addChocolate) {

            basePrice = basePrice + 2;
        }

        return quantity * basePrice;

    }


/*
Method called when the + button is clicked
*/

    public void increment(View view) {

        // checks if there is 100 coffee

        if (quantity == 100)

        // shows an error when you try to have more than 100 coffees

        {
            Toast.makeText(this,
                    getString(R.string.no_more100), Toast.LENGTH_SHORT).show();

            return;

        } else {

            quantity = quantity + 1;

            displayQuantity(quantity);

        }

    }

/*
Method called when the - button is clicked
*/

    public void decrement(View view) {
        // checks if there is 1 coffee
        if (quantity == 1)
        // shows an error when you try to have less than 1 coffee
        {
            Toast.makeText(this,
                    getString(R.string.no_less1), Toast.LENGTH_SHORT).show();

            return;

        } else {

            quantity = quantity - 1;

            displayQuantity(quantity);


        }


    }

/*
Method displays given quantity value on the screen
*/

    private void displayQuantity(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }


}