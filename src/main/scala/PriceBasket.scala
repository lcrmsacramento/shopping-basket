import models.Item
import services.Pricing
import utils.Constants.APPLES
import utils.Constants.BREAD
import utils.Constants.MILK
import utils.Constants.SOUP
import utils.Constants.NO_OFFERS_AVAILABLE
import utils.Constants.UNKNOWN
import utils.Discount
import utils.Constants.MILK_PRICE
import utils.Constants.SOUP_PRICE
import utils.Constants.UNKNOWN_PRICE
import utils.Constants.APPLES_PRICE
import utils.Constants.BREAD_PRICE

object PriceBasket {
  def main(args: Array[String]): Unit = {

    if (args.isEmpty) {
      println("Usage: PriceBasket item1 item2 item3 ...")
      System.exit(1)
    }

    // we will receive the items as arguments
    // those arguments are then converted to a list of Items using a pattern match
    val items = args.toList.map {
      case APPLES => Item(APPLES, APPLES_PRICE)
      case BREAD  => Item(BREAD, BREAD_PRICE)
      case MILK   => Item(MILK, MILK_PRICE)
      case SOUP   => Item(SOUP, SOUP_PRICE)
      case _      => Item(UNKNOWN, UNKNOWN_PRICE)
    }

    // calculate the total price, discount value and final price
    val (totalPrice, discountValue, finalPrice, listOffers) =
      Pricing.calculatePrice(items)

    def printReceipt(
        totalPrice: BigDecimal,
        discountValue: BigDecimal,
        finalPrice: BigDecimal,
        listOffers: List[Discount]
    ): Unit = {
      println(f"Subtotal: £$totalPrice%.2f")
      if (discountValue > 0)
        listOffers.foreach(offer => println(offer.toString()))
      else println(NO_OFFERS_AVAILABLE)
      println(f"Total price: £$finalPrice%.2f")
    }

    printReceipt(totalPrice, discountValue, finalPrice, listOffers)
  }
}
