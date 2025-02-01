import org.scalatest.funsuite.AnyFunSuite
import models.Item
import services.Pricing
import utils.Discount
import utils.SpecialOffers
import utils.Constants.APPLES
import utils.Constants.BREAD
import utils.Constants.MILK
import utils.Constants.SOUP
import utils.Constants.NO_OFFERS_AVAILABLE
import utils.Constants.UNKNOWN
import utils.Constants.APPLES_DISCOUNT_DESCRIPTION
import utils.Constants.BREAD_DISCOUNT_DESCRIPTION
import utils.Constants.MILK_PRICE
import utils.Constants.APPLES_PRICE
import utils.Constants.SOUP_PRICE
import utils.Constants.BREAD_PRICE

class SpecialOffersTest extends AnyFunSuite {

  test("Apply no discounts") {
    val items = List(Item(MILK, MILK_PRICE), Item(MILK, MILK_PRICE))
    val (totalPrice, discountValue, listOffers) =
      SpecialOffers.applyOffers(items)

    assert(totalPrice == 2.60)
    assert(discountValue == 0.0)
    assert(listOffers.isEmpty)
  }

  test("Apply apple discount") {
    val items = List(Item(APPLES, APPLES_PRICE), Item(APPLES, APPLES_PRICE))
    val (totalPrice, discountValue, listOffers) =
      SpecialOffers.applyOffers(items)

    assert(totalPrice == 2.00)
    assert(discountValue == 0.20)
    assert(
      listOffers == List(
        Discount(APPLES_DISCOUNT_DESCRIPTION, "20p")
      )
    )
  }

  test("Apply bread discount") {
    val items = List(
      Item(SOUP, SOUP_PRICE),
      Item(SOUP, SOUP_PRICE),
      Item(BREAD, BREAD_PRICE)
    )

    val (totalPrice, discountValue, listOffers) =
      SpecialOffers.applyOffers(items)

    assert(totalPrice == 2.10)
    assert(discountValue == 0.40)
    assert(
      listOffers == List(
        Discount(BREAD_DISCOUNT_DESCRIPTION, "40p")
      )
    )

  }

}
