package com.example.table.presentation.planning

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.table.domain.usecases.PlanningUseCases
import com.example.table.presentation.RecipeVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

sealed interface AddEditPlanningEvent {
    data class SelectedRecipe(val recipe: RecipeVM) : AddEditPlanningEvent
    data class SelectedSlot(val slot: Int) : AddEditPlanningEvent
    object SaveMeal : AddEditPlanningEvent
}

@HiltViewModel
class AddEditPlanningViewModel @Inject constructor(
    private val useCases: PlanningUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recipeList = MutableStateFlow<List<RecipeVM>>(emptyList())
    val recipeList: StateFlow<List<RecipeVM>> = _recipeList.asStateFlow()

    private val _selectedRecipe = mutableStateOf<RecipeVM?>(null)
    val selectedRecipe: State<RecipeVM?> = _selectedRecipe

    private val _slot = mutableStateOf(1)
    val slot: State<Int> = _slot

    private val _date = mutableStateOf<LocalDate>(LocalDate.now())
    val date: State<LocalDate> = _date

    init {
        val dateStr = savedStateHandle.get<String>("day")
        _date.value = dateStr?.let { LocalDate.parse(it) } ?: LocalDate.now()
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            useCases.getAllRecipes().collect { vms ->
                _recipeList.value = vms
            }
        }
    }

    fun onEvent(event: AddEditPlanningEvent) {
        when (event) {
            is AddEditPlanningEvent.SelectedRecipe -> {
                _selectedRecipe.value = event.recipe
            }
            is AddEditPlanningEvent.SelectedSlot -> {
                _slot.value = event.slot
            }
            is AddEditPlanningEvent.SaveMeal -> {
                saveMeal()
            }
        }
    }

    private fun saveMeal() {
        val recipe = _selectedRecipe.value ?: return
        viewModelScope.launch {
            useCases.saveMeal(
                recipeId = recipe.id,
                date = _date.value,
                slot = _slot.value
            )
        }
    }
}
