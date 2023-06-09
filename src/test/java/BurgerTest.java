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


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private static final String INGREDIENT_NAME_SAUSAGE = "sausage";
    private static final float INGREDIENT_PRICE = 300F;


    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient_filling;

    @Mock
    private Ingredient ingredient_sauce;

    @Mock
    private Ingredient ingredient;
    private Burger burger;


    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void checkSetBuns() {
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void checkAddIngredient() {
        burger.addIngredient(ingredient);
        int expectedIngredientCount = 1;
        Assert.assertEquals(expectedIngredientCount, burger.ingredients.size());
    }

    @Test
    public void checkRemoveIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        int expectedIngredientCount = 0;
        Assert.assertEquals(expectedIngredientCount, burger.ingredients.size());
    }

    @Test
    public void checkMoveIngredient() {
        burger.addIngredient(ingredient_filling);
        burger.addIngredient(ingredient_sauce);
        burger.moveIngredient(1, 0);
        Ingredient actual = burger.ingredients.get(0);
        Assert.assertEquals(ingredient_sauce, actual);
    }

    @Test
    public void checkGetPrice() {
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(300F);
        Assert.assertEquals(burger.getPrice(), 600F, 0);
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

