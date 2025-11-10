package Q1;

public class Main {

    public static void main(String[] args) {
        
        // 1. Define the Product Sales Data (Year, Quarter)
        // Row 0: Year 1 sales (Exam=300, Q2=150, Q3=700)
        // Row 1: Year 2 sales (Exam=250, Q2=200, Q3=600)
        int[][] productSalesData = {
            {300, 150, 700},
            {250, 200, 600}
        };

        // 2. Create an instance of the ProductSales class
        // We can use the interface type to hold the object (Polymorphism)
        IProductSales salesCalculator = new ProductSales();
        
        // 3. Calculate the required values
        int total = salesCalculator.totalSales(productSalesData);
        // Use String.format to round the average to 2 decimal places for better reporting
        double average = salesCalculator.averageSales(productSalesData);
        int max = salesCalculator.maxSale(productSalesData);
        int min = salesCalculator.minSale(productSalesData);

        // 4. Output the results as the final report for 2025
        // Only the calculated values are outputted as requested.
        System.out.println("Product Sales Report Values (2025)");
        System.out.println("------------------------------------");
        System.out.println("Total sales: \t"+total);
        System.out.println("Average sales: \t"+String.format("%.0f", average));
        System.out.println("Maximum sales: \t"+max);
        System.out.println("Minimum sales: \t"+min);
    }
}