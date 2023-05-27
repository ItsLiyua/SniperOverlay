package me.liyua.sniperoverlay.log.listeners

import me.liyua.sniperoverlay.log.LogListener

object PrintListener : LogListener {
    override fun accept(message: String) = true

    override fun apply(message: String) {
        println(message)
    }
}
