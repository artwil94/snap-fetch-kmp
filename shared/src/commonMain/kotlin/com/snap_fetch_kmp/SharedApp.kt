package com.snap_fetch_kmp

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class SharedApp {
    companion object {
        fun initKMMDebugLogs() {
            Napier.base(DebugAntilog())
        }
    }
}