package me.liyua.sniperoverlay.log

abstract class RegexListener(val regex: Regex) : LogListener {
    override fun accept(message: String) = this.regex.matches(message)
}
