package me.liyua.sniperoverlay

import java.io.File
import java.io.FileInputStream
import java.util.logging.Logger
import kotlin.io.path.Path
import kotlin.system.exitProcess

lateinit var logger: Logger
lateinit var mcLogFile: File
lateinit var blackListFile: File

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

    ReaderThread(FileInputStream(mcLogFile)){ println(it) }.start()

    Thread.sleep(100000)
}
