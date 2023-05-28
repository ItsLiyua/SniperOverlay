package me.liyua.sniperoverlay.log

import java.io.InputStream

class LogReader(val reader: InputStream) : Thread() {

    val listeners = mutableListOf<LogListener>()

    var running = true
        private set

    fun shutdown() {
        this.running = false
    }

    override fun run() {
        // Clear everything that was logged to the file before the app was started.
        while (this.reader.available() > 0) this.reader.read()

        while (running) {
            if (this.reader.available() > 0) {
                var s = ""
                while (this.reader.available() > 0) s += this.reader.read().toChar().toString()
                s.split("\n").forEach { line ->
                    this.listeners
                        .filter { it.accept(line) }
                        .forEach { it.apply(line) }
                }
            } else sleep(100)
        }
        this.reader.close()
    }
}
