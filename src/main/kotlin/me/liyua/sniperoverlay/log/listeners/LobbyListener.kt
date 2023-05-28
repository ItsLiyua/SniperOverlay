package me.liyua.sniperoverlay.log.listeners

import me.liyua.sniperoverlay.inGame
import me.liyua.sniperoverlay.log.LogListener

object LobbyListener : LogListener {

    val messages = arrayOfNulls<String>(5)
    private val regex = Regex("^\\[(\\d+:){2}\\d+\\] \\[Client thread\\/INFO]: \\[CHAT] *\$")

    override fun accept(message: String) = true

    override fun apply(message: String) {
        var wipe = false
        messages.copyOf().forEachIndexed { i, s -> if (i < messages.size - 1) messages[i + 1] = s }
        messages[0] = message
        messages.filterNotNull().forEachIndexed { i, s ->
            if (i != messages.size) {
                if (regex.matches(s)) {
                    val next = messages[i + 1] ?: return@forEachIndexed
                    if (regex.matches(next)) {
                        println("Clearing queue")
                        inGame.clear()
                        wipe = true
                        return@forEachIndexed
                    }
                }
            }
        }
        if (wipe) messages.indices.forEach { messages[it] = null }
    }
}
