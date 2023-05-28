package me.liyua.sniperoverlay.log.listeners

import me.liyua.sniperoverlay.add
import me.liyua.sniperoverlay.blacklist
import me.liyua.sniperoverlay.inGame
import me.liyua.sniperoverlay.log.RegexListener
import me.liyua.sniperoverlay.logger

object JoinListener :
    RegexListener(Regex("^\\[(\\d+:){2}\\d+\\] \\[Client thread\\/INFO]: \\[CHAT] \\S{1,16} has joined \\(\\d+\\/\\d+\\)!\$")) {
    override fun apply(message: String) {
        val prefix = Regex("^\\[(\\d+:){2}\\d+\\] \\[Client thread\\/INFO]: \\[CHAT] ")
        val suffix = Regex(" has joined \\(\\d+\\/\\d+\\)!\$")
        add(message.replace(prefix, "").replace(suffix, ""))
    }
}
