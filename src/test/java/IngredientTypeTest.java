import org.junit.Assert;
import org.junit.Test;
import praktikum.IngredientType;


public class IngredientTypeTest {

    @Test
    public void checkOfIngredientTypes() {
        Assert.assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        Assert.assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}