import { Component, OnInit, Input } from '@angular/core';
import { Recipes } from '../recipes.model';
import { RecipeService } from '../recipes.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-recipes-detail',
  templateUrl: './recipes-detail.component.html',
  styleUrls: ['./recipes-detail.component.css']
})
export class RecipesDetailComponent implements OnInit {
  // @Input() recipe: Recipes;
  recipe: Recipes;
  id: number;

  constructor(private recipeService: RecipeService, 
    private route: ActivatedRoute, 
    private router: Router) {

  }

  ngOnInit() {
    const id = this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.recipe = this.recipeService.getRecipe(this.id);
    });
  }

  onAddToShoppingList() {
    this.recipeService.adddIngredientsTooShoppingList(this.recipe.ingredients);
  }

  onEditRecipe() {
    this.router.navigate['edit'], {relativeTo: this.route};
    // this.router.navigate(['../', this.id, 'edit'], {relativeTo: this.route});
  }
}
