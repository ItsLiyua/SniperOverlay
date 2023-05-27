package me.liyua.sniperoverlay.log.listeners

import me.liyua.sniperoverlay.inGame
import me.liyua.sniperoverlay.log.RegexListener

object QuitListener :
    RegexListener(Regex("^\\[(\\d+:){2}\\d+\\] \\[Client thread\\/INFO]: \\[CHAT] \\S{1,16} has quit!\$")) {
    override fun apply(message: String) {
        val prefix = Regex("^\\[(\\d+:){2}\\d+\\] \\[Client thread\\/INFO]: \\[CHAT] ")
        val suffix = Regex(" has quit!\$")
        val name = message.replace(prefix, "").replace(suffix, "")
        inGame.remove(name)
    }
}
