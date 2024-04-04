package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredientOne;
    @Mock
    private Ingredient ingredientTwo;

    Burger burger = new Burger();

    @Test
    public void addIngredientTest(){
        burger.addIngredient(ingredientOne);
        assertTrue("Ингредиент не добавлен", burger.ingredients.contains(ingredientOne));
    }
    @Test
    public void removeIngredientTest(){
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        burger.removeIngredient(1);
        assertEquals("Ингредиент не удален", 1, burger.ingredients.size());
    }
    @Test
    public void moveIngredientTest(){
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        burger.moveIngredient(0,1);
        assertEquals(ingredientOne, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest(){
        when(bun.getPrice()).thenReturn(100F);
        when(ingredientOne.getPrice()).thenReturn(250F);
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        MatcherAssert.assertThat(
                "Цена расчитана не верно", 450F, equalTo(burger.getPrice())
            );
    }

    @Test
    public void getReceiptTest(){
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);

        when(bun.getName()).thenReturn("testBun");
        when(ingredientOne.getName()).thenReturn("testIngredient");
        when(ingredientOne.getType()).thenReturn(SAUCE);
        when(ingredientTwo.getName()).thenReturn("testIngredient1");
        when(ingredientTwo.getType()).thenReturn(FILLING);
        when(burger.getPrice()).thenReturn(30F);

        String expected = "(==== testBun ====)\n" +
                "= sauce testIngredient =\n" +
                "= filling testIngredient1 =\n" +
                "(==== testBun ====)\n" +
                "\nPrice: 30,000000\n";

        MatcherAssert.assertThat(
                "Некорректная строка с рецептом",
                burger.getReceipt(),
                equalTo(expected)
        );
    }
}
