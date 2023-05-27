package me.liyua.sniperoverlay.log

interface LogListener {
    fun accept(message: String): Boolean

    fun apply(message: String)
}
