/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The number of coffee cups.
     */
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants whipped cream topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameField = (EditText) findViewById(R.id.name_field);

        String name = nameField.getText().toString();

        // Calculate the price
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        // Display the order summary on the screen
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        sendOrderEmail(name, message);
    }
    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        //Base price for one cup of coffee.
        int basePrice = 5;

        //Add $1 if customer wants whipped cream.
        if(addWhippedCream){
            basePrice = basePrice + 1;
        }

        //Add $2 if customer wants chocolate.
        if(addChocolate) {
            basePrice = basePrice + 2;
        }

        //Calculate the total price.
        return quantity * basePrice;
    }
    /**
     * Create summary of the order.
     *
     * @param theName the name.
     * @param price of the order.
     * @param addWhippedCream is whether or not to add whipped cream to the coffee.
     * @param addChocolate    is whether or not to add chocolate to the coffee.
     * @return text summary.
     */
    private String createOrderSummary(String theName, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + theName;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {

        if(quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the negative button is clicked.
     */
    public void decrement(View view) {
        if(quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
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
     * This method opens the user default email app and sends the order summary.
     */
    private void sendOrderEmail(String theName, String theMessage) {
        Intent orderEmail = new Intent(Intent.ACTION_SENDTO);
        orderEmail.setData(Uri.parse("mailto:")); // only email apps should handle this

        orderEmail.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + theName);
        orderEmail.putExtra(Intent.EXTRA_TEXT, theMessage);

        if (orderEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(orderEmail);
        }
    }
}