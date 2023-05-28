package me.liyua.sniperoverlay

import me.liyua.sniperoverlay.log.LogReader
import me.liyua.sniperoverlay.log.listeners.JoinListener
import me.liyua.sniperoverlay.log.listeners.LobbyListener
import me.liyua.sniperoverlay.log.listeners.QuitListener
import me.liyua.sniperoverlay.log.listeners.WhoListener
import java.io.File
import java.io.FileInputStream
import java.util.logging.Logger
import kotlin.io.path.Path
import kotlin.system.exitProcess

lateinit var logger: Logger
lateinit var mcLogFile: File
lateinit var blackListFile: File
lateinit var blacklist: Blacklist
val inGame = mutableListOf<String>()

fun main(args: Array<String>) {
    logger = Logger.getLogger("main")

    if (args.size < 2) {
        logger.severe("Please enter a log path and a blacklist file in the launch arguments.")
        exitProcess(1)
    }

    mcLogFile = Path(args[0]).toFile()
    logger.info("Minecraft Log File: ${mcLogFile.absolutePath}.")
    blackListFile = Path(args[1]).toFile()
    logger.info("Blacklist File: ${blackListFile.absolutePath}")

    blacklist = Blacklist(blackListFile)

    val logReader = LogReader(FileInputStream(mcLogFile))
    // logReader.listeners.add(PrintListener)
    logReader.listeners.add(JoinListener)
    logReader.listeners.add(QuitListener)
    logReader.listeners.add(LobbyListener)
    logReader.listeners.add(WhoListener)
    logReader.start()
}

fun add(name:String){
    inGame.add(name)
    if (blacklist.has(name)) {
        logger.warning("$name is on the blacklist! Reason: ${blacklist.reason(name)}")
    }
}
