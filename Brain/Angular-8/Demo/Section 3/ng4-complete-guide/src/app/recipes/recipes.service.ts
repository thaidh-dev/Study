import { Recipes } from './recipes.model'
import { EventEmitter, Injectable } from '@angular/core';
import { Ingredient } from '../shared/ingredient.model';
import { ShoppingListService } from '../shopping-list/shopping-list.service';
import { Subject } from 'rxjs';

@Injectable()
export class RecipeService {
    // recipeSelected = new Subject<Recipes>();

    private recipes: Recipes[] = [
        new Recipes('ko hiểu',
            'mô tả',
            'https://images.unsplash.com/photo-1571942676558-281b2f9b1f8d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60',
            [
                new Ingredient('Meat', 1),
                new Ingredient('French fries', 20),
            ]
        ),

        new Recipes('koo hiểu theo kiểu khác',
            'mô tả',
            'https://images.unsplash.com/photo-1571942676558-281b2f9b1f8d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60',
            [
                new Ingredient('Burns', 2),
                new Ingredient('Meat', 1)
            ]
        )
    ];

    constructor(private slService: ShoppingListService) {

    }

    getRecipes() {
        return this.recipes.slice();
    }

    getRecipe(index: number) {
        return this.recipes[index];
    }

    adddIngredientsTooShoppingList(ingredients: Ingredient[]) {
        this.slService.addIngredients(ingredients);
    }
}