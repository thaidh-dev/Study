import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { RecipeService } from '../recipes/recipes.service';
import {Recipes} from '../recipes/recipes.model';

// @Injectable({providedIn: 'root'})
export class DataStorageService {
    constructor(private http: HttpClient, private recipeService: RecipeService) {

    }

    storeRecipes() {
        // const recipes = this.recipeService.getRecipes();
        // return this.http.put('dhadahdgf', recipes).subscribe(response => {
        //     console.log(response);
        // });
    }

    fetchRecipes() {
        // this.http.get<Recipes[]>('dsdsswkcfefef');
    }
}