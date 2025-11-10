package Q2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class ProductSalesGUI extends JFrame {

    // GUI Components
    private JTextField txtTotal;
    private JTextField txtAverage;
    private JTextField txtOverLimit;
    private JTextField txtUnderLimit;
    private JTextField txtYearsProcessed;
    private JTextArea displayArea;
    
    // Default/Hardcoded Data
    private final int[][] DEFAULT_SALES_DATA = {
        {300, 150, 700}, // Year 1: Microphone, Speakers, Mixing Desk
        {250, 200, 600}  // Year 2: Microphone, Speakers, Mixing Desk
    };
    
    // File name for saving/loading
    private static final String FILENAME = "data.txt";

    public ProductSalesGUI() {
        super("Product Sales Processor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Setup the Menu Bar
        setupMenuBar();
        
        // Setup the Control Panel (Text Fields)
        add(createControlPanel(), BorderLayout.NORTH);
        
        // Setup the Display Area
        displayArea = new JTextArea(15, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Setup the Button Panel
        add(createButtonPanel(), BorderLayout.SOUTH);

        // Initial state
        clearDisplay();
        
        pack(); // Adjusts the frame size to fit components
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    // --- GUI Component Setup Methods ---

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> exitProgram());
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        // Tools Menu
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem loadItem = new JMenuItem("Load Product Data");
        loadItem.addActionListener(e -> loadProductData());
        
        JMenuItem saveItem = new JMenuItem("Save Product Data");
        saveItem.addActionListener(e -> saveProductData());
        
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> clearDisplay());

        toolsMenu.add(loadItem);
        toolsMenu.add(saveItem);
        toolsMenu.addSeparator();
        toolsMenu.add(clearItem);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 4, 10, 10)); // 3 rows, 4 columns
        
        // Initialize Text Fields
        txtTotal = new JTextField(8);
        txtAverage = new JTextField(8);
        txtOverLimit = new JTextField(8);
        txtUnderLimit = new JTextField(8);
        txtYearsProcessed = new JTextField(8); // Read-only label

        // Set all result fields to read-only
        txtTotal.setEditable(false);
        txtAverage.setEditable(false);
        txtOverLimit.setEditable(false);
        txtUnderLimit.setEditable(false);
        txtYearsProcessed.setEditable(false); 

        // Add components: Label, Field, Label, Field...
        panel.add(new JLabel("Total Sales:"));
        panel.add(txtTotal);
        
        panel.add(new JLabel("Years Processed:"));
        panel.add(txtYearsProcessed);

        panel.add(new JLabel("Average Sale:"));
        panel.add(txtAverage);

        panel.add(new JLabel("Sales >= 500:"));
        panel.add(txtOverLimit);
        
        panel.add(new JLabel("Sales < 500:"));
        panel.add(txtUnderLimit);
        
        // Spacer for better layout alignment
        panel.add(new JLabel("")); 
        
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton btnLoad = new JButton("Load Product Data");
        btnLoad.addActionListener(this::loadProductData);
        
        JButton btnSave = new JButton("Save Product Data");
        btnSave.addActionListener(this::saveProductData);
        
        panel.add(btnLoad);
        panel.add(btnSave);
        
        return panel;
    }
    
    // --- Core Logic Methods ---

    // Calculates all values and updates the GUI fields and the display area.
    private void calculateAndDisplay(int[][] salesData) {
        // 1. Instantiate the backend class
        ProductSales salesProcessor = new ProductSales(salesData);
        
        // 2. Calculate values
        int total = salesProcessor.totalSales();
        double average = salesProcessor.averageSales();
        int overLimit = salesProcessor.getSalesOverLimit();
        int underLimit = salesProcessor.getSalesUnderLimit();
        int years = salesProcessor.getYearsProcessed();

        // 3. Update the GUI components
        txtTotal.setText(String.valueOf(total));
        txtAverage.setText(String.format("%.0f", average)); // Display as whole number
        txtOverLimit.setText(String.valueOf(overLimit)); // Should be 2 (700, 600)
        txtUnderLimit.setText(String.valueOf(underLimit)); // Should be 4 (300, 150, 250, 200)
        txtYearsProcessed.setText(String.valueOf(years));
        
        // 4. Update the main display area
        displayArea.setText(salesProcessor.createReport());
    }

    // Loads the hardcoded data, calculates all metrics, and displays them.
    private void loadProductData(ActionEvent e) {
        // We use the default data from memory as requested
        calculateAndDisplay(DEFAULT_SALES_DATA);
    }
    
    // Overloaded method for menu item (since it doesn't need ActionEvent)
    private void loadProductData() {
        loadProductData(null);
    }
    

    // Saves the current content of the displayArea to "data.txt".
    private void saveProductData(ActionEvent e) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            // Write the contents of the display area to the file
            writer.write(displayArea.getText());
            JOptionPane.showMessageDialog(this, "Report saved successfully to " + FILENAME, 
                                          "Save Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), 
                                          "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Overloaded method for menu item
    private void saveProductData() {
        saveProductData(null);
    }

    // Clears all output fields.
    private void clearDisplay() {
        txtTotal.setText("");
        txtAverage.setText("");
        txtOverLimit.setText("");
        txtUnderLimit.setText("");
        txtYearsProcessed.setText("");
        displayArea.setText("Click 'Load Product Data' to begin processing.");
    }
    
    // Exits the program.
    private void exitProgram() {
        System.exit(0);
    }

    // --- Main Method ---
    public static void main(String[] args) {
        // Ensure the GUI is created on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(ProductSalesGUI::new);
    }
}
