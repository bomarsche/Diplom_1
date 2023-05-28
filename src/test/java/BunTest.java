import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import praktikum.Bun;

@RunWith(Parameterized.class)
public class BunTest {
    private final String name;
    private final float price;
    private Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters()
    public static Object[][] getData() {
        return new Object[][]{
                {"Корочка бородинского", 100},
                {"Очень прекрасный, свежайший, хрустящий багета кусок", 0},
                {"", 0},
                {"45689745 4589067 45-06984560", 852.687f},
                {"null", -99999f}
        };
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void checkBunGetName() {
        bun.getName();
        Assert.assertEquals(name, bun.getName());
    }

    @Test
    public void checkBunGetPrice() {
        bun.getName();
        Assert.assertEquals(price, bun.getPrice(), 0);
    }

}
