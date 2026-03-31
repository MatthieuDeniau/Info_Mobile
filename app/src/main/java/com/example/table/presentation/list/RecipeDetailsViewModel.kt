package com.example.table.presentation.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.domain.usecases.RecipeUseCases
import com.example.table.presentation.RecipeVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val useCases: RecipeUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recipe = MutableStateFlow<RecipeVM?>(null)
    val recipe: StateFlow<RecipeVM?> = _recipe.asStateFlow()

    init {
        val recipeId = savedStateHandle.get<Int>("recipeId") ?: -1
        if (recipeId != -1) {
            loadRecipe(recipeId)
        }
    }

    private fun loadRecipe(id: Int) {
        viewModelScope.launch {
            _recipe.value = useCases.getRecipeById(id)
        }
    }
}