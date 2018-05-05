package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

// import java.text.NumberFormat; <- unused, unused method below

public class MainActivity extends AppCompatActivity {


    int quantity = 1; // declaring the quantity of coffee


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//Method to check if this checkbox is checked!



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

        int price = calculatePrice(creamCheckBoxState,addChocolate);

        displayMessage(createOrderSummary(price, creamCheckBoxState, addChocolate, SelectedName));

    }

    /**
     *Method creates a summary of the order information and returns the text summary
     * @param SelectedName of he customer
     * @param addChocolate or not?
     * @param creamCheckBoxState with cream or not?
     * @param price calculated total price
     * @return text summary of the order
     *
     */

    private String createOrderSummary(int price, boolean creamCheckBoxState, boolean addChocolate, String SelectedName) {

        String priceMessage = "Name: " + SelectedName;
        priceMessage = priceMessage + "\nAdd whipped cream? " + creamCheckBoxState;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage = priceMessage + "\nThank you!";
        return priceMessage;

    }

    /**
     * Method calculates the price of the order based on quantity
     * @param creamCheckBoxState with cream or not?
     * @param addChocolate or not?
     **/


    private int calculatePrice(boolean creamCheckBoxState, boolean addChocolate) {

        int basePrice = 5;

        if(creamCheckBoxState) {

            basePrice = basePrice + 1;
        }

        if(addChocolate) {

            basePrice = basePrice + 2;
        }

        return quantity * basePrice;

    }


/*
Method called when the + button is clicked
*/

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);

    }

/*
Method called when the - button is clicked
*/

    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);

    }

/*
Method displays given quantity value on the screen
*/

    private void displayQuantity(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+ number);

    }

/*
Method displays given price on the screen
*/

/*
Unnecessary but for later reference currency? etc. :

private void displayPrice(int number) {

TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

}
*/

/*
Method displays given price on the screen
*/

    private void displayMessage(String message) {

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

    }







}