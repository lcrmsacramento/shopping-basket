# Shopping Basket

This is a Scala application that calculates the total price of a shopping basket, taking into account special offers that might exist. The application accepts a list of items as command-line arguments and outputs the subtotal, any applicable discounts, and the final price.
This application also features a set of unit tests that thoroughly tests each feature of the application, namely the discounts being applied, pricing a set of items and the general expected behaviour of the basket pricing, by inspecting the final receipt.

## Items and Prices

The goods that can be purchased are the following:
- Soup – £0.65 per tin
- Bread – £0.80 per loaf
- Milk – £1.30 per bottle
- Apples – £1.00 per bag

## Special Offers

At the current time, the following special offers are being applied:
- Apples have a 10% discount off their normal price this week.
- Buy 2 tins of soup and get a loaf of bread for half price.

## Usage

The application is written using Scala 2.13.6. Pleasure ensure you are using the same version, so that there are no issues during compilation and runtime. This can be done by using SDKMAN:

```
sdk install scala 2.13.6
sdk use scala 2.13.6
```

If SDKMAN is not available, you can also install it by executing the following:
```
# using Homebrew (for macOS or Linux)
brew install scala@2.13
brew link --force scala@2.13

# using Coursier (for ubuntu/debian)
sudo apt update && sudo apt install curl
curl -fL https://github.com/coursier/launchers/raw/master/cs-x86_64-pc-linux.gz | gzip -d > cs
chmod +x cs
./cs install scala:2.13.6

# using Coursier (for Windows)
cs install scala:2.13.6
```

You can ensure you are using the correct version:
```
scala -version
```


To run the application, we must first compile and generate the jar:

```
sbt clean compile assembly
```

Note that this will ensure that all unit tests pass. If not, the jar will not be created.


Now that we have everything ready, we simply need to run the following:

```
scala -cp target/scala-2.13/shopping-basket-assembly-0.1.jar PriceBasket Apples Milk Bread
```` 