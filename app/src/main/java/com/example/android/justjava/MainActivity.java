/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The number of coffee cups. */
    int quantity = 0;
    /**
     * The price per coffee cup. */
    int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);

        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        String priceMessage = createOrderSummary(price, hasWhippedCream);

        displayMessage(priceMessage);
        calculatePrice();
    }
    /**
     * Calculates the price of the order.
     *
     * @return the total price.
     */
    private int calculatePrice() {

        return quantity * price;
    }
    /**
     * This method displays the summary of the order.
     * */
    private String createOrderSummary(int price, boolean addWhippedCream) {

        String priceMessage = "Name: David Nuno\n";

        priceMessage += "add whipped cream?: " + addWhippedCream;
        priceMessage += "Quantity: " + quantity + "\nTotal: $" + price + "\nThank you!";

        return priceMessage;
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the negative button is clicked.
     */
    public void decrement(View view) {
        if(quantity == 0) {
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}