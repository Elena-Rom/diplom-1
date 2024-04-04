package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class BunTest {

    Bun bun = new Bun("name_bun", 100);

    @Test
    public void getNameTest(){
        Assert.assertEquals("name_bun", bun.getName());
    }
    @Test
    public void getPriceTest(){
        Assert.assertEquals(100, bun.getPrice(), 0);
    }
}
