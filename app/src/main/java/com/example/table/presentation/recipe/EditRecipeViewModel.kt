package com.example.table.presentation.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.data.repository.RecipeRepository
import com.example.table.presentation.IngredientVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditRecipeViewModel @Inject constructor(
    private val useCases: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var recipeId: Int = -1

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _ingredients = MutableStateFlow<List<IngredientVM>>(emptyList())
    val ingredients: StateFlow<List<IngredientVM>> = _ingredients.asStateFlow()

    private val _instructions = MutableStateFlow("")
    val instructions: StateFlow<String> = _instructions.asStateFlow()

    init {
        savedStateHandle.get<Int>("recipeId")?.let { id ->
            recipeId = id
            viewModelScope.launch {
                useCases.getRecipeById(id)?.let { recipeData ->
                    _name.value = recipeData.name
                    _ingredients.value = recipeData.ingredients
                    _instructions.value = recipeData.instructions
                }
            }
        }
    }

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onIngredientsChange(newIngredients: List<IngredientVM>) {
        _ingredients.value = newIngredients
    }

    fun onInstructionsChange(newInstructions: String) {
        _instructions.value = newInstructions
    }

    fun updateRecipe(onUpdated: () -> Unit) {
        if (recipeId == -1) return
        viewModelScope.launch {
            useCases.updateRecipe(
                id = recipeId,
                name = _name.value,
                ingredients = _ingredients.value,
                instructions = _instructions.value
            )
            onUpdated()
        }
    }
}
