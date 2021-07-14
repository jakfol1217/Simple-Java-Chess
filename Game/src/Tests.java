import static org.junit.Assert.*;
public class Tests {
    @org.junit.Test
    public void translateFieldTest() throws Exception {
        int[] location = Game.translateField("c5");
        assertEquals(location[0], 4);
        assertEquals(location[1],2);
    }
}
