package me.liyua.sniperoverlay.log.listeners

import me.liyua.sniperoverlay.blacklist
import me.liyua.sniperoverlay.inGame
import me.liyua.sniperoverlay.log.RegexListener
import me.liyua.sniperoverlay.logger

object JoinListener :
    RegexListener(Regex("^\\[(\\d+:){2}\\d+\\] \\[Client thread\\/INFO]: \\[CHAT] \\S{1,16} has joined \\(\\d+\\/\\d+\\)!\$")) {
    override fun apply(message: String) {
        val prefix = Regex("^\\[(\\d+:){2}\\d+\\] \\[Client thread\\/INFO]: \\[CHAT] ")
        val suffix = Regex(" has joined \\(\\d+\\/\\d+\\)!\$")
        val name = message.replace(prefix, "").replace(suffix, "")
        inGame.add(name)
        if (blacklist.has(name)) {
            logger.warning("$name is on the blacklist! Reason: ${blacklist.reason(name)}")
        }
    }
}
