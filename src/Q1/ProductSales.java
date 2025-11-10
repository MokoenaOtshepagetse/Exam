package Q1;

public class ProductSales implements IProductSales {

    // Calculates the total sales by summing up all elements in the 2D array.
    @Override
    public int totalSales(int[][] productSales) {
        int total = 0;
        // Iterate over each year/row
        for (int[] yearSales : productSales) {
            // Iterate over each quarter's sale in that year
            for (int sale : yearSales) {
                total += sale;
            }
        }
        return total;
    }

    //Calculates the average sales, rounding the result down to the nearest whole number.
    @Override
    public double averageSales(int[][] productSales) {
        int total = totalSales(productSales);
        int count = 0;

        // Calculate the total number of quarters/sales figures
        for (int[] yearSales : productSales) {
            count += yearSales.length;
        }

        // Calculate the average and use Math.floor() to round down
        double rawAverage = (double) total / count;
        return Math.floor(rawAverage);
    }

    // Finds the maximum sale amount by comparing each element to the current maximum.
    @Override
    public int maxSale(int[][] productSales) {
        // Initialize max with the first element of the array
        int max = productSales[0][0]; 

        for (int[] yearSales : productSales) {
            for (int sale : yearSales) {
                if (sale > max) {
                    max = sale;
                }
            }
        }
        return max;
    }

    // Finds the minimum sale amount by comparing each element to the current minimum.
    @Override
    public int minSale(int[][] productSales) {
        // Initialize min with the first element of the array
        int min = productSales[0][0]; 

        for (int[] yearSales : productSales) {
            for (int sale : yearSales) {
                if (sale < min) {
                    min = sale;
                }
            }
        }
        return min;
    }
}

