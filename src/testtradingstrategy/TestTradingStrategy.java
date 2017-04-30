
package testtradingstrategy;

import java.io.IOException;

public class TestTradingStrategy {

  
    public static void main(String[] args) throws IOException {
       
       TestMethods tm = new TestMethods();
       ParsingExcel.readFromExcel();
tm.testUpThreeFractals(4);
}
    }
    

