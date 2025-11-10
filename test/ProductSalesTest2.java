import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductSalesTest2 {

    // The sales limit defined in the ProductSales class is 500.
    
    // Standard data: {300, 150, 700}, {250, 200, 600}
    // Sales >= 500 (Over Limit): 700, 600 (Count: 2)
    // Sales < 500 (Under Limit): 300, 150, 250, 200 (Count: 4)
    private final int[][] TEST_SALES_DATA_1 = {
        {300, 150, 700}, // Year 1
        {250, 200, 600}  // Year 2
    }; 
    
    // Test data for edge cases
    // All sales are over the limit (Count: 3 over, 0 under)
    private final int[][] TEST_SALES_ALL_OVER = {{600, 700, 500}};
    
    // All sales are under the limit (Count: 0 over, 3 under)
    private final int[][] TEST_SALES_ALL_UNDER = {{400, 100, 499}};
    
    // Single sale equal to limit (Count: 1 over, 0 under)
    private final int[][] TEST_SALES_AT_LIMIT = {{500}};

    // Instance of the class to test
    private final ProductSales calculator = new ProductSales(TEST_SALES_DATA_1);


    // --- Tests for Sales Limit Functions ---

    // Note: The constructor for ProductSales now takes the data, 
    // so we need to instantiate it with the specific test data for each case.

    // === Tests for getSalesOverLimit() (Sales >= 500) ===

    @Test
    void salesOverLimit_standardData_returnsTwo() {
        // Expected: 700 and 600 (Count: 2)
        int actualOver = calculator.getSalesOverLimit();
        assertEquals(2, actualOver, "Standard data should have 2 sales over or meeting the limit of 500.");
    }

    @Test
    void salesOverLimit_allSalesOver_returnsThree() {
        ProductSales testCalculator = new ProductSales(TEST_SALES_ALL_OVER);
        // Expected: 600, 700, 500 (Count: 3)
        assertEquals(3, testCalculator.getSalesOverLimit(), "Should return 3 when all sales meet or beat the limit.");
    }

    @Test
    void salesOverLimit_allSalesUnder_returnsZero() {
        ProductSales testCalculator = new ProductSales(TEST_SALES_ALL_UNDER);
        // Expected: (Count: 0)
        assertEquals(0, testCalculator.getSalesOverLimit(), "Should return 0 when all sales are under the limit.");
    }

    @Test
    void salesOverLimit_atLimit_returnsOne() {
        ProductSales testCalculator = new ProductSales(TEST_SALES_AT_LIMIT);
        // Expected: 500 (Count: 1)
        assertEquals(1, testCalculator.getSalesOverLimit(), "Sale exactly at the limit (500) should be counted as over.");
    }


    // === Tests for getSalesUnderLimit() (Sales < 500) ===

    @Test
    void salesUnderLimit_standardData_returnsFour() {
        // Expected: 300, 150, 250, 200 (Count: 4)
        int actualUnder = calculator.getSalesUnderLimit();
        assertEquals(4, actualUnder, "Standard data should have 4 sales under the limit of 500.");
    }

    @Test
    void salesUnderLimit_allSalesUnder_returnsThree() {
        ProductSales testCalculator = new ProductSales(TEST_SALES_ALL_UNDER);
        // Expected: 400, 100, 499 (Count: 3)
        assertEquals(3, testCalculator.getSalesUnderLimit(), "Should return 3 when all sales are under the limit.");
    }

    @Test
    void salesUnderLimit_allSalesOver_returnsZero() {
        ProductSales testCalculator = new ProductSales(TEST_SALES_ALL_OVER);
        // Expected: (Count: 0)
        assertEquals(0, testCalculator.getSalesUnderLimit(), "Should return 0 when all sales meet or beat the limit.");
    }

    @Test
    void salesUnderLimit_atLimit_returnsZero() {
        ProductSales testCalculator = new ProductSales(TEST_SALES_AT_LIMIT);
        // Expected: (Count: 0)
        assertEquals(0, testCalculator.getSalesUnderLimit(), "Sale exactly at the limit (500) should NOT be counted as under.");
    }

    
    // Existing tests (Modified to use the new constructor)
    
    @Test
    void totalSales_standardData_returnsCorrectTotal() {
        assertEquals(2200, calculator.totalSales(), "Total sales should be 2200.");
    }
    
    @Test
    void averageSales_standardData_returnsFlooredAverage() {
        // 2200 / 6 = 366.66... -> Floored to 366.0
        assertEquals(366.0, calculator.averageSales(), 0.001, "Average sales should be floored to 366.0.");
    }
}