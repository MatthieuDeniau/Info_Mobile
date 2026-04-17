package com.example.table.domain.usecases

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ShowSaveNotificationUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    operator fun invoke() {
        Toast.makeText(
            context,
            "Les changements ont été sauvegardés",
            Toast.LENGTH_SHORT
        ).show()
    }
}