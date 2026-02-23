package com.example.table.utils

import com.example.table.presentation.MealVM

val meals : MutableList<MealVM> = mutableListOf(
    MealVM(
        id = 1,
        name = "Pâtes carbo",
        description = "Pâtes avec sauce crémeuse au parmesan et bacon",
        ingredients = listOf("Pâtes", "Bacon", "Parmesan", "Crème"),
        lastMade = "2026-02-10",
        nextMade = "2026-02-12",
    ),
    MealVM(
        id = 2,
        name = "Salade César",
        description = "Salade avec poulet, croûtons et parmesan",
        ingredients = listOf("Laitue", "Poulet", "Croûtons", "Parmesan", "Sauce César"),
        lastMade = "2026-02-12",
        nextMade = "2026-02-19",
    ),
    MealVM(
        id = 3,
        name = "Pizza maison",
        description = "Pizza avec sauce tomate, fromage et pepperoni",
        ingredients = listOf("Pâte à pizza", "Sauce tomate", "Fromage", "Pepperoni"),
        lastMade = "2026-02-14",
        nextMade = "2026-02-22",
    ),
    MealVM(
        id = 4,
        name = "Omelette aux légumes",
        description = "Omelette avec poivrons, champignons et oignons",
        ingredients = listOf("Œufs", "Poivrons", "Champignons", "Oignons", "Sel", "Poivre"),
        lastMade = "2026-02-11",
        nextMade = "2026-02-25",
    ),
    MealVM(
        id = 5,
        name = "Soupe de légumes v2",
        description = "Soupe maison avec carottes, poireaux et pommes de terre",
        ingredients = listOf("Carottes", "Poireaux", "Pommes de terre", "Sel", "Poivre"),
        lastMade = "2026-02-13",
        nextMade = "2026-02-28",
    ),
    MealVM(
        id = 6,
        name = "Tacos au poulet v0",
        description = "Tacos avec poulet, salade, tomates et fromage",
        ingredients = listOf("Tortilla", "Poulet", "Salade", "Tomates", "Fromage"),
        lastMade = null,
        nextMade = "2026-02-29",
    ),
    MealVM(
        id = 7,
        name = "Tacos au poulet",
        description = "Tacos avec poulet, salade, tomates et fromage",
        ingredients = listOf("Tortilla", "Poulet", "Salade", "Tomates", "Fromage"),
        lastMade = null,
        nextMade = "2026-02-29",
    ),
    MealVM(
        id = 8,
        name = "Tacos au poulet v2",
        description = "Tacos avec poulet, salade, tomates et fromage",
        ingredients = listOf("Tortilla", "Poulet", "Salade", "Tomates", "Fromage"),
        lastMade = null,
        nextMade = "2026-02-29",
    )
)