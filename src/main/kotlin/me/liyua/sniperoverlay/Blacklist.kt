package me.liyua.sniperoverlay

import java.io.File

class Blacklist(val file: File) {
    private val names = mutableMapOf<String, String>()

    init {
        if (this.file.exists()) this.load()
    }

    fun has(name: String) = this.names.containsKey(name.lowercase())

    fun reason(name: String) = this.names[name.lowercase()]!!

    fun add(name: String, reason: String) {
        this.names[name.lowercase()] = reason
        logger.info("Added $name to the blacklist for \"$reason\"")
    }

    fun load() {
        var i = 0
        this.file.readLines().forEach {
            val words = it.split(" ")
            val name = words[0].lowercase()
            val reason = words.subList(1, words.size).joinToString(separator = " ")
            this.names[name] = reason
            i++
        }

        logger.info("Loaded $i names from blacklist.")
    }

    fun save() {
        var content = ""
        for (pair in this.names) content += "${pair.key.lowercase()} ${pair.value}\n"
        this.file.writeText(content)
        logger.info("Saved ${this.names.size} names to list.")
    }
}
