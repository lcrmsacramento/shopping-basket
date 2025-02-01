import org.scalatest.funsuite.AnyFunSuite
import models.Discount
import models.Item
import services.Pricing
import utils.SpecialOffers
import utils.Constants
import utils.Constants.APPLES
import utils.Constants.APPLES_PRICE
import utils.Constants.BREAD
import utils.Constants.BREAD_PRICE
import utils.Constants.MILK
import utils.Constants.MILK_PRICE
import utils.Constants.SOUP
import utils.Constants.SOUP_PRICE
import utils.Constants.NO_OFFERS_AVAILABLE
import utils.Constants.UNKNOWN
import utils.Constants.APPLES_DISCOUNT_DESCRIPTION
import utils.Constants.BREAD_DISCOUNT_DESCRIPTION

class PricingTest extends AnyFunSuite {

  test("Apply apple discount") {
    val items = List(Item(APPLES, APPLES_PRICE), Item(APPLES, APPLES_PRICE))
    val (totalPrice, discountValue, finalPrice, listOffers) =
      Pricing.calculatePrice(items)
    assert(totalPrice == 2.0)
    assert(discountValue == 0.2)
    assert(finalPrice == 1.80)
    assert(
      listOffers == List(
        Discount(Constants.APPLES_DISCOUNT_DESCRIPTION, "20p")
      )
    )
  }

  test("Apply bread discount") {
    val items = List(
      Item(SOUP, SOUP_PRICE),
      Item(SOUP, SOUP_PRICE),
      Item(BREAD, BREAD_PRICE)
    )

    val (totalPrice, discountValue, finalPrice, listOffers) =
      Pricing.calculatePrice(items)
    assert(totalPrice == 2.10)
    assert(discountValue == 0.40)
    assert(finalPrice == 1.70)
    assert(
      listOffers == List(
        Discount(Constants.BREAD_DISCOUNT_DESCRIPTION, "40p")
      )
    )
  }

  test("Apply both discounts") {
    val items = List(
      Item(APPLES, APPLES_PRICE),
      Item(APPLES, APPLES_PRICE),
      Item(SOUP, SOUP_PRICE),
      Item(SOUP, SOUP_PRICE),
      Item(BREAD, BREAD_PRICE)
    )
    val (totalPrice, discountValue, finalPrice, listOffers) =
      Pricing.calculatePrice(items)
    assert(totalPrice == 4.10)
    assert(discountValue == 0.6)
    assert(finalPrice == 3.50)
    assert(
      listOffers == List(
        Discount(APPLES_DISCOUNT_DESCRIPTION, "20p"),
        Discount(BREAD_DISCOUNT_DESCRIPTION, "40p")
      )
    )
  }

  test("Apply no discounts") {
    val items = List(Item(MILK, MILK_PRICE), Item(MILK, MILK_PRICE))
    val (totalPrice, discountValue, finalPrice, listOffers) =
      Pricing.calculatePrice(items)
    assert(totalPrice == 2.60)
    assert(discountValue == 0.0)
    assert(finalPrice == 2.60)
    assert(listOffers.isEmpty)
  }

}
