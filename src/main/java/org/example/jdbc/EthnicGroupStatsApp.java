package org.example.jdbc;

import javafx.application.Application; // Importing the Application class from JavaFX.
import javafx.collections.FXCollections; // Importing the FXCollections utility class.
import javafx.collections.ObservableList; // Importing the ObservableList interface.
import javafx.scene.Scene; // Importing the Scene class.
import javafx.scene.chart.LineChart; // Importing the LineChart class.
import javafx.scene.chart.NumberAxis; // Importing the NumberAxis class.
import javafx.scene.chart.PieChart; // Importing the PieChart class.
import javafx.scene.chart.XYChart; // Importing the XYChart class.
import javafx.scene.control.TableColumn; // Importing the TableColumn class.
import javafx.scene.control.TableView; // Importing the TableView class.
import javafx.scene.control.cell.PropertyValueFactory; // Importing the PropertyValueFactory class.
import javafx.scene.layout.BorderPane; // Importing the BorderPane class.
import javafx.scene.layout.HBox; // Importing the HBox class.
import javafx.stage.Stage; // Importing the Stage class.

import java.sql.Connection; // Importing the Connection class from JDBC.
import java.sql.ResultSet; // Importing the ResultSet class from JDBC.
import java.sql.SQLException; // Importing the SQLException class.
import java.sql.Statement; // Importing the Statement class from JDBC.

public class EthnicGroupStatsApp extends Application { // Defining the main class which extends Application.
    private ObservableList<EthnicGroup> ethnicGroupData; // Declaring an ObservableList to hold EthnicGroup data.

    @Override
    public void start(Stage primaryStage) { // Overriding the start method of Application.
        try {
            ethnicGroupData = getEthnicGroups(); // Fetching ethnic group data from the database.

            // Pie Chart
            PieChart pieChart = new PieChart(); // Creating a new PieChart.
            for (EthnicGroup group : ethnicGroupData) { // Looping through the ethnic group data.
                PieChart.Data slice = new PieChart.Data(group.getName(), group.getPopulation()); // Creating a new data slice for each group.
                pieChart.getData().add(slice); // Adding the data slice to the PieChart.
            }

            // Line Chart
            NumberAxis xAxis = new NumberAxis(); // Creating a new X-axis.
            NumberAxis yAxis = new NumberAxis(); // Creating a new Y-axis.
            LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis); // Creating a new LineChart with the axes.
            xAxis.setLabel("Ethnic Groups"); // Setting the label for the X-axis.
            yAxis.setLabel("Population"); // Setting the label for the Y-axis.

            XYChart.Series<Number, Number> series = new XYChart.Series<>(); // Creating a new series for the LineChart.
            series.setName("Population by Ethnic Group"); // Setting the name of the series.
            int index = 1; // Initializing the index for the X-axis.
            for (EthnicGroup group : ethnicGroupData) { // Looping through the ethnic group data.
                series.getData().add(new XYChart.Data<>(index++, group.getPopulation())); // Adding data points to the series.
            }
            lineChart.getData().add(series); // Adding the series to the LineChart.

            // Table View
            TableView<EthnicGroup> tableView = new TableView<>(); // Creating a new TableView.
            TableColumn<EthnicGroup, String> nameColumn = new TableColumn<>("Name"); // Creating a new TableColumn for name.
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // Setting the cell value factory for the name column.

            TableColumn<EthnicGroup, Integer> populationColumn = new TableColumn<>("Population"); // Creating a new TableColumn for population.
            populationColumn.setCellValueFactory(new PropertyValueFactory<>("population")); // Setting the cell value factory for the population column.

            TableColumn<EthnicGroup, String> regionColumn = new TableColumn<>("Region"); // Creating a new TableColumn for region.
            regionColumn.setCellValueFactory(new PropertyValueFactory<>("region")); // Setting the cell value factory for the region column.

            TableColumn<EthnicGroup, String> languageColumn = new TableColumn<>("Language"); // Creating a new TableColumn for language.
            languageColumn.setCellValueFactory(new PropertyValueFactory<>("language")); // Setting the cell value factory for the language column.

            TableColumn<EthnicGroup, String> festivalsColumn = new TableColumn<>("Major Festivals"); // Creating a new TableColumn for major festivals.
            festivalsColumn.setCellValueFactory(new PropertyValueFactory<>("majorFestivals")); // Setting the cell value factory for the major festivals column.

            TableColumn<EthnicGroup, String> activitiesColumn = new TableColumn<>("Economic Activities"); // Creating a new TableColumn for economic activities.
            activitiesColumn.setCellValueFactory(new PropertyValueFactory<>("economicActivities")); // Setting the cell value factory for the economic activities column.

            tableView.getColumns().addAll(nameColumn, populationColumn, regionColumn, languageColumn, festivalsColumn, activitiesColumn); // Adding all columns to the TableView.
            tableView.setItems(ethnicGroupData); // Setting the items for the TableView.

            // Layout
            HBox chartsBox = new HBox(10, pieChart, lineChart); // Creating an HBox to hold the charts.
            BorderPane root = new BorderPane(); // Creating a BorderPane layout.
            root.setTop(chartsBox); // Placing the chartsBox at the top of the layout.
            root.setCenter(tableView); // Placing the TableView at the center of the layout.

            Scene scene = new Scene(root, 1200, 800); // Creating a new Scene with the BorderPane layout.
            primaryStage.setTitle("Ethnic Group Statistics"); // Setting the title of the primary stage.
            primaryStage.setScene(scene); // Setting the scene for the primary stage.
            primaryStage.show(); // Displaying the primary stage.

        } catch (SQLException e) { // Handling SQLException.
            e.printStackTrace(); // Printing the stack trace of the exception.
        }
    }

    private ObservableList<EthnicGroup> getEthnicGroups() throws SQLException { // Method to fetch ethnic group data from the database.
        ObservableList<EthnicGroup> ethnicGroups = FXCollections.observableArrayList(); // Creating an ObservableList to hold the data.
        String query = "SELECT name, population, region, language, major_festivals, economic_activities FROM ethnic_groups"; // SQL query to fetch data.

        try (Connection connection = DBUtility.getConnection(); // Getting a connection to the database.
             Statement statement = connection.createStatement(); // Creating a Statement object.
             ResultSet resultSet = statement.executeQuery(query)) { // Executing the query and getting the ResultSet.

            while (resultSet.next()) { // Looping through the ResultSet.
                String name = resultSet.getString("name"); // Getting the name.
                int population = resultSet.getInt("population"); // Getting the population.
                String region = resultSet.getString("region"); // Getting the region.
                String language = resultSet.getString("language"); // Getting the language.
                String majorFestivals = resultSet.getString("major_festivals"); // Getting the major festivals.
                String economicActivities = resultSet.getString("economic_activities"); // Getting the economic activities.

                EthnicGroup group = new EthnicGroup(name, population, region, language, majorFestivals, economicActivities); // Creating an EthnicGroup object.
                ethnicGroups.add(group); // Adding the EthnicGroup object to the list.
            }
        }
        return ethnicGroups; // Returning the list of ethnic groups.
    }

    public static void main(String[] args) { // Main method to launch the application.
        launch(args); // Launching the JavaFX application.
    }
}
