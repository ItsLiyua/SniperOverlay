package me.liyua.sniperoverlay.gui

import me.liyua.sniperoverlay.blacklist
import me.liyua.sniperoverlay.inGame
import java.awt.*
import javax.swing.JPanel

object Draw : JPanel() {

    init {
        this.background = Color(0f, 0f, 0f, 0.3f)
        this.isOpaque = true
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (g is Graphics2D) g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.clearRect(0, 0, 300, 300)
        g.color = Color.RED
        g.font = Font("Arial", 0, 23)
        var y = 25
        println(1)
        inGame.filter(blacklist::has).forEach {
            g.drawString(it, 10, y)
            y += 25
        }
        g.color = Color.WHITE
        inGame.filterNot(blacklist::has).forEach {
            g.drawString(it, 10, y)
            y += 25
        }
    }
}
