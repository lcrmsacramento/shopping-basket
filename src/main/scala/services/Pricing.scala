package services

import models.Item
import utils.SpecialOffers
import utils.Discount

object Pricing {

  /** Calculate the total price, discount value and final price
    *
    * @param items
    *   List of shopping items
    * @return
    *   tuple with total price, discount value, final price and a List of
    *   special offers being applied
    */
  def calculatePrice(
      items: List[Item]
  ): (BigDecimal, BigDecimal, BigDecimal, List[Discount]) = {
    val (totalPrice, discountValue, listOffers) =
      SpecialOffers.applyOffers(items)
    val finalPrice = totalPrice - discountValue
    (totalPrice, discountValue, finalPrice, listOffers)
  }

}
