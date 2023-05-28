package me.liyua.sniperoverlay.log.listeners

import me.liyua.sniperoverlay.add
import me.liyua.sniperoverlay.log.RegexListener

object WhoListener : RegexListener(
    Regex(
        "^\\[(\\d+:){2}\\d+\\] \\[Client thread\\/INFO]: \\[CHAT] ONLINE: ((\\S+)+((, )|\$))+"
    )
) {
    override fun apply(message: String) {
        message.replace(Regex("^\\[(\\d+:){2}\\d+\\] \\[Client thread\\/INFO]: \\[CHAT] ONLINE: "), "")
            .split(", ")
            .forEach { add(it) }
    }
}
