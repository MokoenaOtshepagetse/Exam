package Q2;

import java.util.Arrays;

public class ProductSales {
    // New Sales Limit requirement
    private static final int SALES_LIMIT = 500;
    
    // Data storage (Year, Product)
    private int[][] productSales;

    public ProductSales(int[][] salesData) {
        this.productSales = salesData;
    }

    // Calculates the total sales by summing up all elements in the 2D array.
    public int totalSales() {
        int total = 0;
        for (int[] yearSales : productSales) {
            for (int sale : yearSales) {
                total += sale;
            }
        }
        return total;
    }

    // Calculates the average sales, rounding the result down.
    public double averageSales() {
        int total = totalSales();
        int count = 0;
        
        // Calculate the total number of sales figures
        for (int[] yearSales : productSales) {
            count += yearSales.length;
        }

        // Handle division by zero for safety (though unlikely with hardcoded data)
        if (count == 0) {
            return 0.0;
        }
        
        // Calculate the average and use Math.floor() to round down
        double rawAverage = (double) total / count;
        return Math.floor(rawAverage); 
    }

    /* Calculates the number of years processed.
      @return The number of rows in the sales data array.
     */
    public int getYearsProcessed() {
        return productSales.length;
    }

    // Calculates how many sales met or beat the Sales limit.
    public int getSalesOverLimit() {
        int overLimitCount = 0;
        for (int[] yearSales : productSales) {
            for (int sale : yearSales) {
                if (sale >= SALES_LIMIT) {
                    overLimitCount++;
                }
            }
        }
        return overLimitCount;
    }

    // Calculates how many sales were under the Sales limit.
    
    public int getSalesUnderLimit() {
        int underLimitCount = 0;
        for (int[] yearSales : productSales) {
            for (int sale : yearSales) {
                if (sale < SALES_LIMIT) {
                    underLimitCount++;
                }
            }
        }
        return underLimitCount;
    }
    
    // Creates a formatted string report for display.
    public String createReport() {
        StringBuilder report = new StringBuilder();
        report.append("--- Product Sales Report ---\n");
        report.append("Total Sales: ").append(totalSales()).append("\n");
        report.append("Average Sale (Floored): ").append(String.format("%.0f", averageSales())).append("\n");
        report.append("Sales Limit (Threshold): ").append(SALES_LIMIT).append("\n");
        report.append("Sales Meeting/Beating Limit (>= 500): ").append(getSalesOverLimit()).append("\n");
        report.append("Sales Under Limit (< 500): ").append(getSalesUnderLimit()).append("\n");
        report.append("Years Processed: ").append(getYearsProcessed()).append("\n\n");              
        return report.toString();
    }
}
