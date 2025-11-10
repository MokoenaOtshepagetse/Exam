import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductSalesTest {

    // 1. Define the shared data for testing
    private final int[][] TEST_SALES_DATA_1 = {
        {300, 150, 700}, // Year 1
        {250, 200, 600}  // Year 2
    }; 
    
    // Total: 2200, Count: 6, Average: 366.66..., Max: 700, Min: 150
    private final ProductSales calculator = new ProductSales();
    
    // Data for edge cases: empty, single element
    private final int[][] TEST_SALES_DATA_SINGLE = {{500}};
    private final int[][] TEST_SALES_DATA_NEGATIVE = {{-100, 50, -50}};


    // --- Tests for TotalSales function ---

    @Test
    void totalSales_standardData_returnsCorrectTotal() {
        // Expected: 300 + 150 + 700 + 250 + 200 + 600 = 2200
        int expectedTotal = 2200;
        int actualTotal = calculator.totalSales(TEST_SALES_DATA_1);
        
        // Assert that the calculated total matches the expected total
        assertEquals(expectedTotal, actualTotal, "Total sales should be 2200 for standard data.");
    }
    
    @Test
    void totalSales_singleElement_returnsElement() {
        // Expected: 500
        assertEquals(500, calculator.totalSales(TEST_SALES_DATA_SINGLE), "Total sales for single element should be 500.");
    }

    @Test
    void totalSales_withNegativeSales_returnsCorrectTotal() {
        // Expected: -100 + 50 + (-50) = -100
        assertEquals(-100, calculator.totalSales(TEST_SALES_DATA_NEGATIVE), "Total sales should correctly handle negative numbers.");
    }

    // --- Tests for AverageSales function ---

    @Test
    void averageSales_standardData_returnsFlooredAverage() {
        // Calculation: 2200 / 6 = 366.666...
        // Expected (Floored): 366.0
        double expectedAverage = 366.0;
        double actualAverage = calculator.averageSales(TEST_SALES_DATA_1);
        
        // Use a delta (e.g., 0.001) for floating-point comparisons to account for precision
        assertEquals(expectedAverage, actualAverage, 0.001, "Average sales should be floored to 366.0.");
    }
    
    @Test
    void averageSales_noRoundingNeeded_returnsExactAverage() {
        // Test data: {100, 200, 300} -> Total: 600, Count: 3. Average: 200.0
        int[][] testData = {{100, 200, 300}};
        double expectedAverage = 200.0;
        
        assertEquals(expectedAverage, calculator.averageSales(testData), 0.001, "Average sales should be 200.0 when it's a whole number.");
    }

    @Test
    void averageSales_singleElement_returnsElement() {
        // Calculation: 500 / 1 = 500.0
        assertEquals(500.0, calculator.averageSales(TEST_SALES_DATA_SINGLE), 0.001, "Average sales for single element should be 500.0.");
    }
}