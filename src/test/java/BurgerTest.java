import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private static final String INGREDIENT_NAME_CHILL_SAUCE = "chili sauce";
    private static final String INGREDIENT_NAME_SAUSAGE = "sausage";
    private static final float INGREDIENT_PRICE = 300F;


    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;
    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        burger.addIngredient(new Ingredient(SAUCE, INGREDIENT_NAME_CHILL_SAUCE, INGREDIENT_PRICE));
    }

    @Test
    public void checkSetBuns() {
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void checkAddIngredient() {
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void checkRemoveIngredient() {
        burger.removeIngredient(0);
        Assert.assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void checkMoveIngredient() {
        burger.addIngredient(new Ingredient(FILLING, INGREDIENT_NAME_SAUSAGE, INGREDIENT_PRICE));
        burger.moveIngredient(1, 0);
        Assert.assertEquals(INGREDIENT_NAME_SAUSAGE, burger.ingredients.get(0).name);
    }

    @Test
    public void checkGetPrice() {
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(300F);
        Assert.assertEquals(burger.getPrice(), 900F, 0);
    }

    @Test
    public void checkGetReceipt() {
        burger = new Burger();

        Mockito.when(bun.getName()).thenReturn("red bun");
        Mockito.when(bun.getPrice()).thenReturn(300F);
        burger.setBuns(bun);

        Mockito.when(ingredient.getName()).thenReturn(INGREDIENT_NAME_SAUSAGE);
        Mockito.when(ingredient.getType()).thenReturn(FILLING);
        Mockito.when(ingredient.getPrice()).thenReturn(INGREDIENT_PRICE);
        burger.addIngredient(ingredient);

        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("= %s %s =%n", burger.ingredients.get(0).getType().toString().toLowerCase(), burger.ingredients.get(0).getName()));
        receipt.append(String.format("(==== %s ====)%n", "red bun"));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));
        String expectedReceipt = receipt.toString();
        String actual = burger.getReceipt();
        Assert.assertEquals(expectedReceipt, actual);
    }
}

