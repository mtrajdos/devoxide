import { Recipe } from './recipe.model'
import { EventEmitter, Injectable } from '@angular/core';
import { Ingredient } from '../shared/ingredient.model';
import { ShoppingListService } from '../shopping-list/shopping-list.service';

@Injectable()
export class RecipeService {

    recipeSelected = new EventEmitter<Recipe>();

    private recipes: Recipe[] = [
        new Recipe('Tomato soup', 'Delicious and hearty soup from baked tomatoes', 'https://p0.pikrepo.com/preview/336/81/bowl-of-tomato-soup-with-basil-and-walnut-bread.jpg', [
            new Ingredient('Tomato', 10),
            new Ingredient('Basil', 1)
        ]),
        new Recipe('Absolute unit burger', 'Get your arteries clogged with style', 'https://c.pxhere.com/images/81/7f/ac5c059175a4957415613fa9f36c-1586557.jpg!d', [
            new Ingredient('Bun', 2),
            new Ingredient('Beef', 1)
        ])
      ];

      constructor (private shoppingListService: ShoppingListService) { }

      getRecipes() {
          return this.recipes.slice();
      }

      getRecipe(id: number) {
          return this.recipes[id];
      }

      addIngredientsToShoppingList(ingredients: Ingredient[]) {
        this.shoppingListService.addIngredients(ingredients);
      }

}