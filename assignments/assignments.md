# Assignments

# Assignment -1:

Typical product database consists of two types of product components — product categories and product items. A product category is generally contain product items and also other product categories as its subcategories. Example Product Categories:

Computers
Desktops
Laptops
Peripherals
Printers
Cables The Computers product category contains both the Desktops and the Laptops product categories as its subcategories. The Desktop category can contain a product item such as Compaq Presario 5050. Product items are usually individual, in the sense that they do not contain any product component within. Design and implement an PricingCalculator to list the dollar value of a product component or Product Category.

![Assignment - 1](Assignment-1.PNG)

## Assignment 2:
```
public class OnlineCart
{
    public void CheckOut(PaymentType paymentType)
    {
        switch(paymentType)
        {
            case PaymentType.CreditCard:
                    ProcessCreditCardPayment();
                    break;
            case PaymentType.Paypal:
                    ProcessPaypalPayment();
                    break;
            case PaymentType.GoogleCheckout:
                    ProcessGooglePayment();break;
            case PaymentType.AmazonPayments:P
                    ProcessAmazonPayment();
                    break;
        }
    }
    private void ProcessCreditCardPayment()
    {
        Print("Credit card payment chosen");
    }
    private void ProcessPaypalPayment(){
        Print("Paypal payment chosen");
    }
    private void ProcessGooglePayment()
    {
        Print("Google payment chosen");
    }
    private void ProcessAmazonPayment()
    {
        Print("Amazon payment chosen");
    }
}
```
## Violations:
- Single responsibility principal
- Open close principal -> say if we want to add new payment method

New design:

-----------

![alt](Assignment-2.PNG)

## Assignment 3: Vital monitor
- Given that you are desiging a vital monitor watch -> you need to send the vital information via mesaage to a care taker and also need to display in watch.
- Meditator pattern

![alt](./Assignment-3.PNG)

## Assignment 4: 
- Refactor the above design -> remove the multiple inheritance (Diamond problem)

Question:
![alt](./Assignment-4-question.PNG)

Answer:
- Diamond problem to split using composition/aggregation (HAS-A)
- This solved using Bridge Pattern (Don't use inheritance when the interface and class extending)
- **Resources:**
- https://www.codeproject.com/Articles/890/Bridge-Pattern-Bridging-the-gap-between-Interface
- Refactored solution
![alt](./Assignment-4-full-ans.PNG)