import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;


public class IngredientTest {

    private Ingredient ingredient;

    @Before
    public void setUp() {
        ingredient = new Ingredient(IngredientType.SAUCE, "Мазик", 50.0f);
    }

    @Test
    public void checkIngredientType() {
        IngredientType expected = IngredientType.SAUCE;
        IngredientType actual = ingredient.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkIngredientName() {
        String expected = "Мазик";
        String actual = ingredient.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkIngredientPrice() {
        float expected = 50.0f;
        float actual = ingredient.getPrice();
        Assert.assertEquals(expected, actual, 0);
    }

}