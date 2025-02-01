package models

/** this will serve as our base data structure for the discounts
  * @param description
  *   description of the discount
  * @param value
  *   value of the discount
  */
case class Discount(description: String, value: String) {
  override def toString: String = s"$description: $value"
}
