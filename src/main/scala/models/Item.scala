package models

/** this will serve as our base data structure for the items in the shopping
  * basket
  * @param name
  *   name of the item
  * @param price
  *   price of the item
  */
final case class Item(name: String, price: BigDecimal)
