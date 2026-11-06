package org.example

import java.util.Map.entry
import kotlin.test.Test

class ConfigUpdaterTest {
    @Test
    fun `should update config`() {
        val iniFile = IniFile()
        val configUpdater = ConfigUpdater()

        configUpdater.update(iniFile, entry("key", "value"))

        val parameter = iniFile.getParameter("key")

        assert(parameter.containsValue("value")) { "Parameter was not set" }
    }
}