package me.harry.jpa.shop.infrastructure.cache

class Caches {
    companion object {
        const val DEFAULT_EXPIRE_TIME = 300L
        const val UNLESS_NULL = "#result == null"
    }

    interface ALL {
        interface Order {
            companion object {
                const val NAME = "!Order"
                const val TTL = 3600L
                const val KEY_ID = "#id"
            }
        }
    }
}