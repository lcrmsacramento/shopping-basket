package utils

import models.Item
import utils.Constants.APPLES
import utils.Constants.BREAD
import utils.Constants.MILK
import utils.Constants.SOUP
import utils.Constants.APPLES_DISCOUNT_DESCRIPTION
import utils.Constants.BREAD_DISCOUNT_DESCRIPTION
import utils.Constants.APPLES_DISCOUNT
import utils.Constants.BREAD_DISCOUNT

// this will serve as our base data structure for the discounts
case class Discount(description: String, value: String) {
  override def toString: String = s"$description: $value"
}

object SpecialOffers {

  /** Apply special offers to the shopping basket
    *
    * @param items
    *   List of shopping items
    * @return
    *   tuple with total price and discount value
    */

  def applyOffers(
      items: List[Item]
  ): (BigDecimal, BigDecimal, List[Discount]) = {

    // summ price of all Items in basket
    val totalPrice = items.map(_.price).sum

    // 10% discount on apples
    // this will calculate the total discount amount on all apples
    val discountOnApples =
      items.filter(_.name == APPLES).map(_.price * APPLES_DISCOUNT).sum
    val numberSoups = items.count(_.name == SOUP)
    // if we have at least 2 soups, we apply a discount of 50% on the bread
    // for every 2 soups, one loaf of bread will have a discount
    val discountOnBread =
      if (numberSoups >= 2)
        items
          .filter(_.name == BREAD)
          .take(
            numberSoups / 2
          ) // the discount is for every 2 soups, so we only apply it to the first numberSoups/2 breads
          .map(
            _.price * BREAD_DISCOUNT
          ) // apply the discount on those exact items
          .sum
      else BigDecimal(0.0)

    // format the discount value to be presented in the receipt
    /** if the value is smaller than one, we present it as pounds. e.g. 0.5 will
      * be presented as 50p, 1.5 will be presented as £1.50
      * @param value
      *   discount value to be formatted
      * @return
      *   formatted discount value
      */
    def formatDiscount(value: BigDecimal): String = {
      if (value < 1) f"${(value * 100).toInt}p"
      else f"£$value%.2f"
    }

    // if there are discounts, we create a list of offers, to later present in the receipt
    // note that it might be an empty list!
    val listOffers = List(
      if (discountOnApples > 0)
        Some(
          Discount(
            APPLES_DISCOUNT_DESCRIPTION,
            formatDiscount(discountOnApples)
          )
        )
      else None,
      if (discountOnBread > 0)
        Some(
          Discount(
            BREAD_DISCOUNT_DESCRIPTION,
            formatDiscount(discountOnBread)
          )
        )
      else None
    ).flatten

    // sum of all discounts
    val discountValue = discountOnApples + discountOnBread

    // return a tuple with the total price and the discount value
    (totalPrice, discountValue, listOffers)
  }

}
