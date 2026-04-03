package com.example.table.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.domain.usecases.DeleteRecipeUseCase
import com.example.table.domain.usecases.GetAllRecipesUseCase
import com.example.table.presentation.RecipeVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase
) : ViewModel() {

    val recipes: StateFlow<List<RecipeVM>> = getAllRecipesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteRecipe(recipe: RecipeVM) {
        viewModelScope.launch {
            deleteRecipeUseCase(recipe.id)
        }
    }
}
