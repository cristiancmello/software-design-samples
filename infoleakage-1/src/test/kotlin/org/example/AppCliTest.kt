package org.example

import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.jupiter.api.Test

class AppCliTest {
    @Test
    fun `should read, update storage and save file`() {
        val iniFileReader = mockk<IniFileReader>(relaxUnitFun = true)
        val configUpdater = mockk<ConfigUpdater>(relaxUnitFun = true)
        val iniFileWriter = mockk<IniFileWriter>(relaxUnitFun = true)
        val appCli = AppCli(iniFileReader, iniFileWriter, configUpdater)

        appCli.run()

        verifySequence {
            iniFileReader.load()
            configUpdater.update()
            iniFileWriter.save()
        }
    }
}