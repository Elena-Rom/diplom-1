package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTest {
    @Parameterized.Parameter
    public IngredientType type;
    @Parameterized.Parameter(1)
    public String name;
    @Parameterized.Parameter(2)
    public float price;

    Ingredient ingredient;


    @Parameterized.Parameters
    public static Object[][] getIngredientTestData() {
        return new Object[][]{
                {SAUCE, "ingredientName1", 1000F},
                {FILLING, "ingredientName2", 0F},
        };
    }
    @Test
    public void getPriceTest(){
        ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(),0);
    }

    @Test
    public void getNameTest(){
        ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void IngredientTypeTest(){
        ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }

}
