package me.liyua.sniperoverlay.gui

import java.awt.Color
import java.awt.Graphics
import javax.swing.JLabel
import javax.swing.JPanel

object Draw : JPanel() {

    init {
        this.background = Color(0f, 0f, 0f, 0.3f)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = Color.RED
        g.fillRect(0, 0, 100, 100)
    }
}
