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

class SpecialOffersTest extends AnyFunSuite {

  test("Apply no discounts") {
    val items = List(Item(MILK, 1.30), Item(MILK, 1.30))
    val (totalPrice, discountValue, listOffers) =
      SpecialOffers.applyOffers(items)

    assert(totalPrice == 2.60)
    assert(discountValue == 0.0)
    assert(listOffers.isEmpty)
  }

  test("Apply apple discount") {
    val items = List(Item(APPLES, 0.60), Item(APPLES, 0.60))
    val (totalPrice, discountValue, listOffers) =
      SpecialOffers.applyOffers(items)

    assert(totalPrice == 1.20)
    assert(discountValue == 0.12)
    assert(
      listOffers == List(
        Discount(APPLES_DISCOUNT_DESCRIPTION, "12p")
      )
    )
  }

  test("Apply bread discount") {
    val items = List(
      Item(SOUP, 0.65),
      Item(SOUP, 0.65),
      Item(BREAD, 0.80)
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
