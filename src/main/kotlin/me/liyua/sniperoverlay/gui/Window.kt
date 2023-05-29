package me.liyua.sniperoverlay.gui

import java.awt.Color
import java.awt.Dimension
import javax.swing.JFrame

object Window {

    private val jf = JFrame("Sniper Overlay")

    init {
        this.jf.size = Dimension(300, 300)
        this.jf.isUndecorated = true
        this.jf.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        this.jf.background = Color(0f, 0f, 0f, 0f)
        this.jf.isResizable = false
        this.jf.isAlwaysOnTop = true
        this.jf.contentPane.add(Draw)
    }

    fun show() {
        this.jf.isVisible = true
    }

    fun hide() {
        this.jf.isVisible = false
    }

    fun update() {
        this.jf.repaint()
    }
}
