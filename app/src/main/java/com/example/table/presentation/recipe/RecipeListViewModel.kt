package com.example.table.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.data.repository.RecipeRepository
import com.example.table.presentation.RecipeVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val useCases: RecipeRepository
) : ViewModel() {

    val recipes: StateFlow<List<RecipeVM>> = useCases.getAllRecipes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteRecipe(recipe: RecipeVM) {
        viewModelScope.launch {
            useCases.deleteRecipe(recipe.id)
        }
    }
}
