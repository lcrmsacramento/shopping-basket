package models

// this will serve as our base data structure for the items in the shopping basket
final case class Item(name: String, price: BigDecimal)
