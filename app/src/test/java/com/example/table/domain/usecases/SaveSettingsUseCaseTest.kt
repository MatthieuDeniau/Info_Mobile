package com.example.table.domain.usecases

import com.example.table.domain.model.SettingsEntity
import com.example.table.fakes.FakeDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SaveSettingsUseCaseTest {

    private lateinit var fakeDatabase: FakeDatabase
    private lateinit var useCase: SaveSettingsUseCase

    @Before
    fun setup() {
        fakeDatabase = FakeDatabase()
        useCase = SaveSettingsUseCase(fakeDatabase)
    }

    @Test
    fun `sauvegarder les reglages doit mettre a jour la base de donnees`() = runBlocking {
        val settings = SettingsEntity(
            id = 0,
            allNotificationsEnabled = true,
            morningEnabled = true,
            morningTime = "08:00",
            noonEnabled = true,
            noonTime = "12:00",
            snackEnabled = false,
            snackTime = "16:00",
            eveningEnabled = true,
            eveningTime = "20:00"
        )

        useCase(settings)

        val savedSettings = fakeDatabase.getSetting()
        assertEquals(settings, savedSettings)
    }
}
