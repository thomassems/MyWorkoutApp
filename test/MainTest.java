import app.Main;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void mainTest() {
        Main.setTesting();
        Main.main(new String[0]);
        assertTrue(true);
    }
}
