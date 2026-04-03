package com.example.table.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.domain.usecases.SaveRecipeUseCase
import com.example.table.presentation.IngredientVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor(
    private val saveRecipeUseCase: SaveRecipeUseCase
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _ingredients = MutableStateFlow<List<IngredientVM>>(emptyList())
    val ingredients: StateFlow<List<IngredientVM>> = _ingredients.asStateFlow()

    private val _instructions = MutableStateFlow("")
    val instructions: StateFlow<String> = _instructions.asStateFlow()

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onIngredientsChange(newIngredients: List<IngredientVM>) {
        _ingredients.value = newIngredients
    }

    fun onInstructionsChange(newInstructions: String) {
        _instructions.value = newInstructions
    }

    fun saveRecipe(onSaved: () -> Unit) {
        viewModelScope.launch {
            saveRecipeUseCase(
                name = _name.value,
                ingredients = _ingredients.value,
                instructions = _instructions.value
            )
            onSaved()
        }
    }
}
