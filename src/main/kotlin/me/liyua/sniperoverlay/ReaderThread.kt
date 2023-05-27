package me.liyua.sniperoverlay

import java.io.InputStream

class ReaderThread(val reader: InputStream, val onRead: (String) -> Unit) : Thread() {

    var running = true
        private set

    fun shutdown() {
        this.running = false
    }

    override fun run() {
        while (running) {
            if (this.reader.available() > 0) {
                var s = ""
                while (this.reader.available() > 0) s += this.reader.read().toChar().toString()
                this.onRead(s)
            } else sleep(100)
        }
        this.reader.close()
    }
}
