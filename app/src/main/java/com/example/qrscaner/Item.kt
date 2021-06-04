package com.example.qrscaner

class Item {
    var sum: Int? = null
    var price: Int? = null
    var quantity: Int? = null
    var name: String = ""

    override fun toString(): String {
        return "Name=$name Sum=$sum Price=$price Quantity=$quantity"
    }
}