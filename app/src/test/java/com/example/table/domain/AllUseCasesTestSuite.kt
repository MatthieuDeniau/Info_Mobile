package com.example.table.domain

import com.example.table.domain.usecases.*
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    // Planning / Repas
    SaveMealUseCaseTest::class,
    DeleteMealUseCaseTest::class,
    GetLastMealDateUseCaseTest::class,
    GetMealsForSlotUseCaseTest::class,
    GetMealsForWeekUseCaseTest::class,

    // Recettes
    SaveRecipeUseCaseTest::class,
    DeleteRecipeUseCaseTest::class,
    UpdateRecipeUseCaseTest::class,
    GetAllRecipesUseCaseTest::class,
    GetRecipeByIdUseCaseTest::class,
    SortRecipesByLastUsedUseCaseTest::class,

    // Réglages
    SaveSettingsUseCaseTest::class,

    // Utilitaires temporels
    FormatPeriodLabelUseCaseTest::class,
    GetNextPeriodUseCaseTest::class,
    GetPreviousPeriodUseCaseTest::class,
    GetSlotLabelUseCaseTest::class
)
class AllUseCasesTestSuite