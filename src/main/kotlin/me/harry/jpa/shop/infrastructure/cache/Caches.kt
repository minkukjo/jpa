package me.harry.jpa.shop.infrastructure.cache

class Caches {
    companion object {
        val ALL = "!"
    }

    interface ALL {
        interface Order {
            companion object {
                val NAME = ALL + "Order"
                val TTL = 3600L
                val KEY_ID = "#id"
            }
        }
    }
}