/**
 * GUI Class
 * A class that builds the game GUI + the calculations for the game to run
 * SubClass of Resources --> use methods in resources to make Java Swing Components
 * Written by: Shorna Kundu
 * Last modified: June 19, 2024
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class GUI extends Resources {

    // coffee api + random class
    private final Coffee[] icedCoffee;
    private final Random rand = new Random();

    // creating fields for JFrame, JPanel, JLabel, JTable, JScrollPane, JButton, JTextArea,
    private JPanel startPanel, startButtonPanel, recipePanel, recipeButtonPanel, marketButtonPanel, marketOptionPanel,
            marketplacePanel, marketInventoryPanel, marketMoneyPanel, weatherPanel, weatherButtonPanel, marketADButtonPanel,
            marketADInventoryPanel, marketADOptionPanel, marketplaceADPanel, marketADMoneyPanel, gamePanel, cafePanel, ingredientsLeftPanel,
            ingLeftButtonPanel, cafeButtonPanel, marketInfoPanel, marketIButtonPanel, reportPanel, reportButtonPanel, reportInfoRight, reportInfoLeft,
            gameStatusPanel, gameOptionsButton, reportDayPanel;
    private JLabel marketMoneyLabel, coffeeInvLabel, creamInvLabel, iceInvLabel, sugarInvLabel, flyerInvLabel, posterInvLabel, onlineInvLabel, marketADMoneyLabel;
    private JTable newIngredientsTable;
    private JScrollPane scp, scpIngredients;
    private JButton startButton, quitButton, rulesButton, recipeButton, marketContinueButton, coffeeButton, creamButton,
            iceButton, sugarButton, flyerButton, posterButton, onlineAdButton, marketADContinueButton, weatherButton, ingLeftButton,
            cafeButton, marketIButtonCont, marketIButtonBack, marketBackButton, marketADBackButton, repContButton, repBackButton, gameRetryButton, gameQuitButton;

    private int coffeeNum, creamNum, sugarNum, iceNum, weatherValue;

    // fields to run for the game
    private int coffeeQuantity, creamQuantity, iceQuantity, sugarQuantity, flyerQuantity, posterQuantity, onlineAdQuantity;
    private int dayNum = 1, pageNum, num, posterNum, flyerNum, onlineAdNum;
    private final HashMap<String, Integer> weather = new HashMap<String, Integer>(); // hashmap for weather values
    private final HashMap<String, Double> cost = new HashMap<String, Double>(); // hashmap for cost of ingredients
    private double money = 20.00, income; // setting initial money + income field

    // number of coffee, cream, ice and sugar required and then price, and recipe number
    private final int[][] recipes = {{2, 1, 3, 4, 4, 1}, {4, 2, 3, 2, 3, 2}, {4, 2, 4, 3, 4, 3}, {4, 1, 2, 2, 6, 4}, {5, 2, 3, 1, 5, 5}, {6, 3, 4, 3, 5, 6}};
    private int[] rndRecipeIngredients; // used to take a row from 2d array above


    /**
     * constructor for the GUI class sets the JFrame + Container
     *
     * @param icedCoffee --> an object from the coffee class used to grab recipe name + description
     */
    public GUI(Coffee[] icedCoffee) {

        // creating new frame + grabbing instance of coffee
        JFrame frame = new JFrame();
        this.icedCoffee = icedCoffee;
        num = rand.nextInt(25); // random number generator for weather

        // adding cost of ingredients to hashmap
        cost.put("Coffee", 0.25);
        cost.put("Cream", 0.50);
        cost.put("Sugar", 0.20);
        cost.put("Ice", 0.15);
        cost.put("Poster", 0.50);
        cost.put("Flyer", 1.00);
        cost.put("OnlineAd", 2.0);

        // setting up JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // initializing close button of window
        frame.setLayout(null);      // no set layout
        frame.setSize(800, 600);    // size of window
        frame.setLocationRelativeTo(null); // disabling resizing
        frame.getContentPane().setBackground(Color.BLACK); // setting bg to black
        frame.setLayout(null); // custom layout
        frame.setTitle("Bora's Café"); //sets title of frame
        frame.setResizable(false); //stop frame from being resized

        // changing window icon
        ImageIcon image = new ImageIcon("coffee.png"); //create ImageIcon
        frame.setIconImage((image.getImage())); //change icon of window

        container = frame.getContentPane(); //add the frame to the container
        startScreen();  // calling that start screen
        frame.setVisible(true); // allowing the frame to be made
    }

    /**
     * start screen of the game
     */
    public void startScreen() {
        // setting up start panel to display contents of screen
        startPanel = panel(100, 100, 600, 150);
        JLabel startText = label(startPanel, "Bora's Café", 100);

        // panel to place buttons + with set layout
        startButtonPanel = panel(300, 300, 200, 200);
        startButtonPanel.setLayout(new GridLayout(4, 1));

        // for start, quit & rules button
        startButton = button(startButtonPanel, 30, "START", "Click to Start Game");
        quitButton = button(startButtonPanel, 30, "QUIT  ", "Click to Quit Game");
        rulesButton = button(startButtonPanel, 30, "RULES", "Click to See Rules");
    }

    /**
     * lets the user know which recipe they have been given (randomly selected from the API)
     */
    public void givenRecipeScreen() {
        int randomRecipe = rand.nextInt(0, 6); // random number to get random recipe
        rndRecipeIngredients = recipes[randomRecipe]; // grabbing ingredients for the recipe (for future use)

        // grabbing name + description for recipe
        String recipeGiven = icedCoffee[randomRecipe].getTitle();
        String recipeGivenDescription = icedCoffee[randomRecipe].getDescription();

        // creating continue button
        recipeButtonPanel = panel(325, 350, 150, 45);
        recipeButton = button(recipeButtonPanel, 25, "Continue", "Click to Continue");

        // displaying info to user by adding onto panel
        recipePanel = panel(100, 100, 600, 250);
        JLabel recipeTextTitle = label(recipePanel, "Welcome to Bora's Café", 40); // title of screen
        JTextArea recipeText = textArea(recipePanel, "Bora has given you the following " +
                        "recipe to get you started on your Café: " + recipeGiven + ". " + recipeGivenDescription,
                700, 150, 600, 25, 20);  // multi line text block
    }

    /**
     * displays the current weather for the user
     */
    public void currentWeather() {
        // adding weather info into hashmap --> weather + maximum # of drinks sold
        weather.put("Sunny", 18);
        weather.put("Rainy", 8);
        weather.put("Thunderstorm", 3);
        weather.put("Cloudy", 12);
        weather.put("Windy", 6);
        weather.put("Tornado", 1);

        // creating panel + continue button
        weatherPanel = panel(100, 100, 600, 200);
        weatherButtonPanel = panel(325, 350, 150, 45);
        weatherButton = button(weatherButtonPanel, 25, "Continue", "Click to Continue");

        // defining variables
        JLabel weatherIconLabel;
        ImageIcon weatherImage;

        // loop to set the chance of weather --> displays weather + an icon --> weatherValue grabs maximum customers for the day
        if (num < 5) {
            weatherIconLabel = label(weatherPanel, "The current weather is sunny", 30);
            weatherImage = image("sunny.png", weatherIconLabel);
            weatherValue = weather.get("Sunny");
        } else if (num < 10) {
            weatherIconLabel = label(weatherPanel, "The current weather is cloudy", 30);
            weatherImage = image("cloudy.png", weatherIconLabel);
            weatherValue = weather.get("Cloudy");
        } else if (num < 15) {
            weatherIconLabel = label(weatherPanel, "The current weather is rainy", 30);
            weatherImage = image("rainy.png", weatherIconLabel);
            weatherValue = weather.get("Rainy");
        } else if (num < 20) {
            weatherIconLabel = label(weatherPanel, "The current weather is windy", 30);
            weatherImage = image("windy.png", weatherIconLabel);
            weatherValue = weather.get("Windy");

        } else if (num < 23) {
            weatherIconLabel = label(weatherPanel, "The current weather is a thunderstorm", 30);
            weatherImage = image("thunderstorm.png", weatherIconLabel);
            weatherValue = weather.get("Rainy");
        } else {
            weatherIconLabel = label(weatherPanel, "The current weather is a tornado", 30);
            weatherImage = image("tornado.png", weatherIconLabel);
            weatherValue = weather.get("Tornado");
        }

        // only displays weather info on page 2
        if (pageNum != 2) {
            weatherButtonPanel.setVisible(false);
            weatherPanel.setVisible(false);
        }

    }

    /**
     * market info screen displays cost of ingredients and amount required to make one glass
     */
    public void marketInfo() {
        // setting column + row data for the table
        String[] columnNames = {"Ingredient", "Amount", "Price"};
        Object[][] data = {
                {"Coffee", rndRecipeIngredients[0], "$" + cost.get("Coffee")},
                {"Cream", rndRecipeIngredients[1], "$" + cost.get("Cream")},
                {"Sugar", rndRecipeIngredients[3], "$" + cost.get("Sugar")},
                {"Ice", rndRecipeIngredients[2], "$" + cost.get("Ice")}};

        // adding data to table
        JTable newTable = table(columnNames, data);
        scp = scp(newTable, 130, 240, 500, 87); // allowing for table to be scrollable

        // continue + back button to previous page
        marketIButtonPanel = panel(280, 375, 250, 35);
        marketIButtonPanel.setLayout(new GridLayout(1, 2));
        marketIButtonBack = button(marketIButtonPanel, 25, "Back", "Click to Go Back");
        marketIButtonCont = button(marketIButtonPanel, 25, "Continue", "Click to Continue");

        // main panel to show user info + JTextArea showing the multiline information
        marketInfoPanel = panel(100, 100, 600, 500);
        JTextArea marketILabel = textArea(marketInfoPanel, "The following table shows the list of ingredients you need to buy to make one glass of your recipe along with the price attached to buy them. You are selling each drink at the price $" + rndRecipeIngredients[4] + ".", 600, 150, 600, 25, 25);
    }

    /**
     * marketplace screen allows the user to buy how much of each ingredient they need/want
     */
    public void marketplaceScreen() {

        // continue button + back button
        marketOptionPanel = panel(280, 375, 250, 35);
        marketOptionPanel.setLayout(new GridLayout(1, 2));
        marketBackButton = button(marketOptionPanel, 25, "Back", "Click to Go Back");
        marketContinueButton = button(marketOptionPanel, 25, "Continue", "Click to Continue");

        // panel to add buttons
        marketButtonPanel = panel(475, 195, 200, 200);

        // buttons to buy more ingredients
        coffeeButton = button(marketButtonPanel, 20, "Buy More Coffee", "Click to buy Coffee");
        creamButton = button(marketButtonPanel, 20, "Buy More Cream", "Click to buy Cream");
        sugarButton = button(marketButtonPanel, 20, "Buy More Sugar ", "Click to buy Sugar");
        iceButton = button(marketButtonPanel, 20, "Buy More Ice     ", "Click to buy Ice");

        // inventory panel + labels (displaying how much of each ingredient you've bought)
        marketInventoryPanel = panel(150, 195, 125, 160);
        coffeeInvLabel = label(marketInventoryPanel, coffeeQuantity + " Coffee", 30);
        creamInvLabel = label(marketInventoryPanel, creamQuantity + " Cream", 30);
        sugarInvLabel = label(marketInventoryPanel, sugarQuantity + " Sugar ", 30);
        iceInvLabel = label(marketInventoryPanel, iceQuantity + " Ice      ", 30);

        // displaying the total amount of money you have
        marketMoneyPanel = panel(305, 150, 230, 100);
        marketMoneyLabel = label(marketMoneyPanel, "You have $" + Math.round(money * 100.00) / 100.00, 30);

        // marketplace panel + title(label)
        marketplacePanel = panel(100, 100, 600, 250);
        JLabel marketplaceLabel = label(marketplacePanel, "Inventory + Marketplace", 30);
    }

    /**
     * marketplace advertisement screen allows the user to buy advertisements to increase sales
     */
    public void marketplaceADScreen() {

        // continue button + panel to go to next page !
        marketADOptionPanel = panel(280, 375, 250, 35);
        marketADOptionPanel.setLayout(new GridLayout(1, 2));
        marketADBackButton = button(marketADOptionPanel, 25, "Back", "Click to Go Back");
        marketADContinueButton = button(marketADOptionPanel, 25, "Continue", "Click to Continue");

        // panel to add buttons on  + buttons to buy more ingredients
        marketADButtonPanel = panel(475, 195, 200, 200);
        posterButton = button(marketADButtonPanel, 20, "Buy Poster      ", "Click to buy Poster");
        flyerButton = button(marketADButtonPanel, 20, "Buy Flyer       ", "Click to buy flyer");
        onlineAdButton = button(marketADButtonPanel, 20, "Buy Online Ad", "Click to buy Online Advertisement");

        // inventory panel + labels
        marketADInventoryPanel = panel(150, 195, 150, 160);
        posterInvLabel = label(marketADInventoryPanel, posterQuantity + " Poster      ", 30);
        flyerInvLabel = label(marketADInventoryPanel, flyerQuantity + " Flyer       ", 30);
        onlineInvLabel = label(marketADInventoryPanel, onlineAdQuantity + " Online Ad", 30);

        // displaying the total amount of money you have
        marketADMoneyPanel = panel(305, 150, 230, 100);
        marketADMoneyLabel = label(marketADMoneyPanel, "You have $" + Math.round(money * 100.00) / 100.00, 30);

        // marketplace panel + title(label)
        marketplaceADPanel = panel(100, 100, 600, 250);
        JLabel marketplaceADLabel = label(marketplaceADPanel, "Advertisement Shop", 30);
    }

    /**
     * cafe screen --> image of cafe before you get your report
     */
    public void cafeScreen() {

        // creating panel + setting title of screen
        cafePanel = panel(100, 100, 600, 350);
        JLabel cafeLabel = label(cafePanel, "Welcome to Bora's Café", 30); //creating label to add image + text
        ImageIcon cafeImage = image("cafe.png", cafeLabel); // setting image

        // continue button + with its panel
        cafeButtonPanel = panel(325, 450, 150, 45);
        cafeButton = button(cafeButtonPanel, 25, "Continue", "Click to Continue");
    }

    /**
     * player stats and game data shows a game bar on certain screens showing the current day, weather and money
     */
    public void playerStatsAndGameData() {

        String weatherType = null; // initializing  weather type variable

        // tutorial for grabbing key given value of hashmap --> https://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value
        // for loop to find key from value
        for (Map.Entry<String, Integer> entry : weather.entrySet()) {
            if (entry.getValue().equals(weatherValue)) {
                weatherType = entry.getKey();
                break;  // Exit the loop once the key is found
            }
        }

        // creating game data panel + adding labels with info
        gamePanel = panel(110, 15, 600, 50);
        gamePanel.setLayout(new GridLayout(1, 3)); // day, money, weather
        JLabel dateLabel = label(gamePanel, "Day: " + dayNum, 20);
        JLabel weatherLabel = label(gamePanel, "  Weather: " + weatherType, 20);
        JLabel moneyLabel = label(gamePanel, "          Money: " + Math.round((money) * 100.00) / 100.00, 20);
    }

    /**
     * calculating the maximum amount of recipes the user can make with ingredients they've bought
     *
     * @return --> returns maximum amount of recipes you can make
     */
    public int getRecipeMax() {
        // initializing variables
        int recipeMax, coffeeN, creamN, iceN, sugarN;

        // calculating maximum
        coffeeN = coffeeQuantity / rndRecipeIngredients[0];
        creamN = creamQuantity / rndRecipeIngredients[1];
        iceN = iceQuantity / rndRecipeIngredients[2];
        sugarN = sugarQuantity / rndRecipeIngredients[3];

        // grabbing the lowest number from int array to find total amount of glasses that can be made
        int[] ingredients = {coffeeN, creamN, iceN, sugarN};
        Arrays.sort(ingredients);

        // setting variable to equal that + returning it for future use
        recipeMax = ingredients[0];
        return recipeMax;
    }

    /**
     * calculating the amount of recipes the user can make given weather
     * @return --> returning amount of glasses user can sell bc of weather
     */
    public int getRecipeLimit() {
        // grabbing max limit
        int recipeLimit = getRecipeMax();

        // Limit the amount of recipes possible based on weather
        if (weatherValue <= recipeLimit) {
            recipeLimit = weatherValue;
        }
        return recipeLimit; // returning the value
    }

    /**
     * ingredients left calculates amount of ingredients left after selling drinks
     */
    public void ingredientsLeft() {

        // creating panel to display info
        ingredientsLeftPanel = panel(100, 100, 600, 70);

        // continue button + panel to display button
        ingLeftButtonPanel = panel(325, 350, 150, 45);
        ingLeftButton = button(ingLeftButtonPanel, 25, "Continue", "Click to Continue");

        // setting variables to equal to total amount of ingredients bought by user --> (actual variables will be used in report and updated with these values later)
        coffeeNum = coffeeQuantity;
        creamNum = creamQuantity;
        sugarNum = sugarQuantity;
        iceNum = iceQuantity;
        posterNum = posterQuantity;
        flyerNum = flyerQuantity;
        onlineAdNum = onlineAdQuantity;

        // grabbing amount of recipes user can make
        int recipeLimit = getRecipeLimit();
        int n = rand.nextInt(10); // random int to set which ingredient to decrease

        // initializing variables
        int difference, increaseValue;
        int recipeMax = getRecipeMax();
        int posterSales = 1;
        int flyerSales = 2;
        int onlineAdSales = 3;

        // setting up variables for calculations
        increaseValue = posterNum * posterSales + flyerNum * flyerSales + onlineAdNum * onlineAdSales; //calculating extra sales --> given you've bought advertisements
        // checking if you have enough ingredients to make extra sales
        difference = recipeMax - recipeLimit;
        if (difference < increaseValue) {
            increaseValue = difference;
        }

        // amount of coffee leftover at the end of day never changes
        coffeeNum = coffeeNum - (rndRecipeIngredients[0] * recipeLimit) - (rndRecipeIngredients[0] * increaseValue);

        // calculating left over ingredients + finding which one spoils at the end of day
        JLabel ingLeftLabel;
        if (n < 5) {
            ingLeftLabel = label(ingredientsLeftPanel, "Unfortunately your remaining ice has melted", 30);
            iceNum = 0;
            creamNum = creamNum - (rndRecipeIngredients[1] * recipeLimit) - (rndRecipeIngredients[1] * increaseValue);
            sugarNum = sugarNum - (rndRecipeIngredients[3] * recipeLimit) - (rndRecipeIngredients[3] * increaseValue);

        } else if (n < 8) {
            ingLeftLabel = label(ingredientsLeftPanel, "Unfortunately your remaining cream has spoiled", 30);
            creamNum = 0;
            iceNum = iceNum - (rndRecipeIngredients[2] * recipeLimit) - (rndRecipeIngredients[2] * increaseValue);
            sugarNum = sugarNum - (rndRecipeIngredients[3] * recipeLimit) - (rndRecipeIngredients[3] * increaseValue);

        } else {
            ingLeftLabel = label(ingredientsLeftPanel, "Unfortunately your remaining sugar has gone bad", 30);
            sugarNum = 0;
            iceNum = iceNum - (rndRecipeIngredients[2] * recipeLimit) - (rndRecipeIngredients[2] * increaseValue);
            creamNum = creamNum - (rndRecipeIngredients[1] * recipeLimit) - (rndRecipeIngredients[1] * increaseValue);
        }


        // setting data for columns + rows for data table
        String[] columnNames = {"Ingredient", "Amount"};
        Object[][] data = {
                {"Coffee", coffeeNum},
                {"Cream", creamNum},
                {"Sugar", sugarNum},
                {"Ice", iceNum}};

        // creating data table to display leftover ingredients
        newIngredientsTable = table(columnNames, data);
        scpIngredients = scp(newIngredientsTable, 150, 170, 500, 87);

        // advertisements always go back to zero
        posterQuantity = 0;
        flyerQuantity = 0;
        onlineAdQuantity = 0;
    }

    /**
     * get report displays the daily financial report of the cafe
     */
    public void getReport() {

        // initializing variables
        int difference, increaseValue;
        int recipeLimit = getRecipeLimit();
        int recipeMax = getRecipeMax();
        int posterSales = 1;
        int flyerSales = 2;
        int onlineAdSales = 3;

        // setting up variables for calculations
        increaseValue = posterNum * posterSales + flyerNum * flyerSales + onlineAdNum * onlineAdSales; //calculating extra sales --> given you've bought advertisements
        // checking if you have enough ingredients to make extra sales
        difference = recipeMax - recipeLimit;
        if (difference < increaseValue) {
            increaseValue = difference;
        }


        // calculations for income, total expenses, # of drinks made, # of drinks sold
        income = (rndRecipeIngredients[4] * recipeLimit) + (rndRecipeIngredients[4] * increaseValue); // how much money made
        double totalExpenses = (coffeeQuantity * cost.get("Coffee") + creamQuantity * cost.get("Cream") + sugarQuantity * cost.get("Sugar")
                + iceQuantity * cost.get("Ice") + flyerNum * cost.get("Flyer") + posterNum * cost.get("Poster") + onlineAdNum * cost.get("OnlineAd")); // expenses

        // displaying info to the left of the title
        reportInfoLeft = panel(150, 195, 200, 120);
        JLabel drinksMade = label(reportInfoLeft, recipeMax + " Drink's Made ", 30);
        JLabel drinksSold = label(reportInfoLeft, (recipeLimit + increaseValue) + " Drink's Sold   ", 30);

        // displaying info to the right of the title
        reportInfoRight = panel(475, 195, 250, 170);
        JLabel reportIncome = label(reportInfoRight, "Income            " + Math.round(income * 100.00) / 100.00, 30);
        JLabel reportExpenses = label(reportInfoRight, "Expenses         " + Math.round(totalExpenses * 100.00) / 100.00, 30);
        JLabel reportAssets = label(reportInfoRight, "Asset's            " + Math.round((money + income) * 100.00) / 100.00, 30);

        // continue + back button
        reportButtonPanel = panel(280, 375, 260, 35);
        reportButtonPanel.setLayout(new GridLayout(1, 2));
        repBackButton = button(reportButtonPanel, 25, "Back", "Click to Go Back");
        repContButton = button(reportButtonPanel, 25, "Continue", "Click to Continue");

        // setting up title of screen + the current day
        reportPanel = panel(100, 100, 600, 50);
        JLabel reportLabel = label(reportPanel, "Bora's Café Daily Financial Report", 30);
        reportDayPanel = panel(305, 150, 190, 100);
        JLabel dayNumLabel = label(reportDayPanel, "     Day: " + dayNum, 30);

        money += income;
    }

    /**
     * game status method displays when you either win or lose the game
     * @param gameText --> title text changes weather you win or lose
     */
    public void gameStatus(String gameText) {

        // setting up main panel + title of screen
        gameStatusPanel = panel(100, 100, 600, 150);
        JLabel gameLabel = label(gameStatusPanel, gameText, 100);

        // panel to place button + setting on fixed layout
        gameOptionsButton = panel(300, 300, 200, 200);
        gameOptionsButton.setLayout(new GridLayout(4, 1));

        // for start, quit & rules button
        gameRetryButton = button(gameOptionsButton, 30, "RETRY", "Click to Retry Game");
        gameQuitButton = button(gameOptionsButton, 30, "QUIT", "Click to Quit Game");

    }

    // Used this video on how to set up button listeners for multiple buttons --> https://www.youtube.com/watch?v=OI-TFbHQhtA
    /**
     * this is the first action listener and starts the loop for the game
     */
    public void setUpButtonListeners() {
        ActionListener buttonListener = ae -> {
            Object o = ae.getSource();
            if (pageNum == 0) {
                if (o == startButton) {     // when start button is pressed it will move on to next screen
                    startPanel.setVisible(false);
                    startButtonPanel.setVisible(false);
                    pageNum = 1; // iterator for loop
                    givenRecipeScreen();
                    setUpGivenRecipeListeners();

                } else if (o == quitButton) {
                    System.exit(0);
                } else if (o == rulesButton) { //when rules button is pressed it will display a message
                    // used this link on how to make JOptionPane bigger --> https://stackoverflow.com/questions/43623278/joptionpane-bigger
                    // how to make multi line JOptionPane --> https://stackoverflow.com/questions/12118244/java-multiple-lines-of-text-using-joptionpane-showinputdialognull-text
                    JTextArea scrollPane = getTextArea();
                    UIManager.put("OptionPane.minimumSize",new Dimension(430,400));
                    JOptionPane.showMessageDialog(null, scrollPane);
                }
            }
        };
        // adding buttons to action listener
        startButton.addActionListener(buttonListener);
        quitButton.addActionListener(buttonListener);
        rulesButton.addActionListener(buttonListener);
    }

    /**
     * action listener for the recipe that Bora gives you
     */
    public void setUpGivenRecipeListeners() {
        ActionListener buttonListener = ae -> {
            Object o = ae.getSource();
            if (pageNum == 1) {
                if (o == recipeButton) { // move on to next screen
                    pageNum = 2;
                    recipePanel.setVisible(false);
                    recipeButtonPanel.setVisible(false);
                    currentWeather();
                    setUpWeatherListener();
                }
            }
        };
        recipeButton.addActionListener(buttonListener);
    }

    /**
     * action listener for the weather screen --> weather button to continue to next screen
     */
    public void setUpWeatherListener() {
        ActionListener buttonListener = ae -> {
            Object o = ae.getSource();
            if (pageNum == 2) {
                if (o == weatherButton) {
                    pageNum = 3;
                    weatherPanel.setVisible(false);
                    weatherButtonPanel.setVisible(false);
                    marketInfo(); // ingredients cost + amount needed + price per drink
                    playerStatsAndGameData(); // tells you current money + weather + day
                    setUpMarketInfoListener();
                }
            }
        };
        weatherButton.addActionListener(buttonListener);
    }

    /**
     * this method makes sets continue and back button for the market info method
     */
    public void setUpMarketInfoListener() {
        ActionListener buttonListener = ae -> {
            Object o = ae.getSource();
            if (pageNum == 3) {
                if (o == marketIButtonCont) {
                    pageNum = 4;
                    marketInfoPanel.setVisible(false);
                    marketIButtonPanel.setVisible(false);
                    gamePanel.setVisible(false);
                    scp.setVisible(false);
                    marketplaceScreen();
                    setUpMarketPlaceListeners();
                } else if (o == marketIButtonBack) {
                    pageNum = 2;
                    marketInfoPanel.setVisible(false);
                    marketIButtonPanel.setVisible(false);
                    gamePanel.setVisible(false);
                    scp.setVisible(false);
                    currentWeather();
                    setUpWeatherListener();
                }
            }
        };
        marketIButtonCont.addActionListener(buttonListener);
        marketIButtonBack.addActionListener(buttonListener);
    }

    /**
     * used this link to figure out how to update variables in Java Swing --> https://stackoverflow.com/questions/30016357/how-do-i-update-my-variables-in-a-java-gui
     * this method sets the buttons for ingredients marketplace and updates money + how much you've bought of each ingredient
     */
    public void setUpMarketPlaceListeners() {
        ActionListener buttonListener = ae -> {
            Object o = ae.getSource();
            if (pageNum == 4) {
                if (o == coffeeButton) { // the specific button the user has pressed
                    if (money <= 0) { // stops you from buying ingredients once your money is in the negatives
                        JOptionPane.showMessageDialog(null, "Sorry you don't have enough to buy more");
                    } else {
                        coffeeQuantity++; // increase amount of ingredient
                        money -= cost.get("Coffee"); //decrease money
                        marketMoneyLabel.setText("You have $" + (Math.round(money * 100.00)) / 100.00); //display money
                        coffeeInvLabel.setText(coffeeQuantity + " Coffee"); // display number of ingredients bought
                    }
                } else if (o == creamButton) {
                    if (money <= 0) {
                        JOptionPane.showMessageDialog(null, "Sorry you don't have enough to buy more");
                    } else {
                        creamQuantity++;
                        money -= cost.get("Cream");
                        marketMoneyLabel.setText("You have $" + (Math.round(money * 100.00)) / 100.00);
                        creamInvLabel.setText(creamQuantity + " Cream");
                    }
                } else if (o == sugarButton) {
                    if (money <= 0) {
                        JOptionPane.showMessageDialog(null, "Sorry you don't have enough to buy more");
                    } else {
                        sugarQuantity++;
                        money -= cost.get("Sugar");
                        marketMoneyLabel.setText("You have $" + (Math.round(money * 100.00)) / 100.00);
                        sugarInvLabel.setText(sugarQuantity + " Sugar ");
                    }
                } else if (o == iceButton) {
                    if (money <= 0) {
                        JOptionPane.showMessageDialog(null, "Sorry you don't have enough to buy more");
                    } else {
                        iceQuantity++;
                        money -= cost.get("Ice");
                        marketMoneyLabel.setText("You have $" + (Math.round(money * 100.00)) / 100.00);
                        iceInvLabel.setText(iceQuantity + " Ice      ");
                    }
                } else if (o == marketContinueButton) { // continue button --> undisplayed the current screen + calls next screen + its listener
                    marketplacePanel.setVisible(false);
                    marketButtonPanel.setVisible(false);
                    marketInventoryPanel.setVisible(false);
                    marketOptionPanel.setVisible(false);
                    marketMoneyPanel.setVisible(false);
                    pageNum = 5;
                    marketplaceADScreen();
                    setUpMarketPlaceADListeners();
                } else if (o == marketBackButton) { // back button --> --> undisplayed the current screen + calls previous screen and set screen num to one less
                    marketplacePanel.setVisible(false);
                    marketButtonPanel.setVisible(false);
                    marketInventoryPanel.setVisible(false);
                    marketOptionPanel.setVisible(false);
                    marketMoneyPanel.setVisible(false);
                    pageNum = 3;
                    playerStatsAndGameData();
                    marketInfo();
                    setUpMarketInfoListener();
                }
            }
        };
        coffeeButton.addActionListener(buttonListener);
        iceButton.addActionListener(buttonListener);
        sugarButton.addActionListener(buttonListener);
        creamButton.addActionListener(buttonListener);
        marketContinueButton.addActionListener(buttonListener);
        marketBackButton.addActionListener(buttonListener);
    }

    /**
     * method for the advertisement marketplace buttons
     */
    public void setUpMarketPlaceADListeners() {
        ActionListener buttonListener = ae -> {
            Object o = ae.getSource();
            if (pageNum == 5) {
                if (o == posterButton) {
                    if (money <= 0) {
                        JOptionPane.showMessageDialog(null, "Sorry you don't have enough to buy more");
                    } else {
                        posterQuantity++;
                        money -= cost.get("Poster");
                        marketADMoneyLabel.setText("You have $" + (Math.round(money * 100.00)) / 100.00);
                        posterInvLabel.setText(posterQuantity + " Poster      ");
                    }
                } else if (o == flyerButton) {
                    if (money <= 0) {
                        JOptionPane.showMessageDialog(null, "Sorry you don't have enough to buy more");
                    } else {
                        flyerQuantity++;
                        money -= cost.get("Flyer");
                        marketADMoneyLabel.setText("You have $" + (Math.round(money * 100.00)) / 100.00);
                        flyerInvLabel.setText(flyerQuantity + " Flyer       ");
                    }
                } else if (o == onlineAdButton) {
                    if (money <= 0) {
                        JOptionPane.showMessageDialog(null, "Sorry you don't have enough to buy more");
                    } else {
                        onlineAdQuantity++;
                        money -= cost.get("OnlineAd");
                        marketADMoneyLabel.setText("You have $" + (Math.round(money * 100.00)) / 100.00);
                        onlineInvLabel.setText(onlineAdQuantity + " Online Ad");
                    }
                } else if (o == marketADContinueButton) {
                    marketADButtonPanel.setVisible(false);
                    marketADInventoryPanel.setVisible(false);
                    marketADOptionPanel.setVisible(false);
                    marketplaceADPanel.setVisible(false);
                    marketADMoneyPanel.setVisible(false);
                    pageNum = 6;
                    cafeScreen();
                    playerStatsAndGameData();
                    setUpCafeListener();
                } else if (o == marketADBackButton) {
                    pageNum = 4;
                    marketADButtonPanel.setVisible(false);
                    marketADInventoryPanel.setVisible(false);
                    marketADOptionPanel.setVisible(false);
                    marketplaceADPanel.setVisible(false);
                    marketADMoneyPanel.setVisible(false);
                    marketplaceScreen();
                    setUpMarketPlaceListeners();

                }
            }
        };
        posterButton.addActionListener(buttonListener);
        flyerButton.addActionListener(buttonListener);
        onlineAdButton.addActionListener(buttonListener);
        marketADContinueButton.addActionListener(buttonListener);
        marketADBackButton.addActionListener(buttonListener);
    }

    /**
     * action listener for the cafe screen --> allows user to move to next page
     * no back button because user shouldn't be allowed to go back (buy more ingredients) while the cafe is running
     */
    public void setUpCafeListener() {
        ActionListener buttonListener = ae -> {
            Object o = ae.getSource();
            if (pageNum == 6) {
                if (o == cafeButton) {
                    pageNum = 7;
                    cafeButtonPanel.setVisible(false);
                    cafePanel.setVisible(false);
                    gamePanel.setVisible(false);
                    playerStatsAndGameData();
                    ingredientsLeft();
                    setUpIngredientsLeftListener();
                }
            }
        };
        cafeButton.addActionListener(buttonListener);
    }

    /**
     * action listener to make the continue button in the ingredients left screen work and move to the report page.
     */
    public void setUpIngredientsLeftListener() {
        ActionListener buttonListener = ae -> {
            Object o = ae.getSource();
            if (pageNum == 7) {
                if (o == ingLeftButton) {
                    pageNum = 8;
                    ingredientsLeftPanel.setVisible(false);
                    ingLeftButtonPanel.setVisible(false);
                    newIngredientsTable.setVisible(false);
                    scpIngredients.setVisible(false);
                    gamePanel.setVisible(false);
                    getReport();
                    setUpReportListener();
                }
            }
        };
        ingLeftButton.addActionListener(buttonListener);
    }

    /**
     * action listener for the report page checks back button to see ingredients left over + continue button to see if you win or not.
     */
    public void setUpReportListener() {
        ActionListener buttonListener = ae -> {
            Object o = ae.getSource();
            if (pageNum == 8) {
                coffeeQuantity = coffeeNum;
                creamQuantity = creamNum;
                sugarQuantity = sugarNum;
                iceQuantity = iceNum;
                if (o == repContButton) {
                    pageNum = 9;
                    int limit = getRecipeMax();
                    if (((money) <= 0) && (limit == 0)) {   // if you have no money and the leftover ingredients are not enough to make anymore drinks you loose the game
                        reportPanel.setVisible(false);
                        reportInfoLeft.setVisible(false);
                        reportInfoRight.setVisible(false);
                        reportButtonPanel.setVisible(false);
                        reportDayPanel.setVisible(false);
                        gameStatus("GAME OVER");
                        setUpGameStatusListener();

                    } else if ((money) >= 50) {  // once you earn more than or equal to $50 you win the game
                        reportPanel.setVisible(false);
                        reportInfoLeft.setVisible(false);
                        reportInfoRight.setVisible(false);
                        reportButtonPanel.setVisible(false);
                        reportDayPanel.setVisible(false);
                        gameStatus("YOU WIN");
                        setUpGameStatusListener();

                    } else {    // continues to next day if you have enough money however not enough to win the game
                        dayNum++;
                        reportPanel.setVisible(false);
                        reportInfoLeft.setVisible(false);
                        reportInfoRight.setVisible(false);
                        reportButtonPanel.setVisible(false);
                        reportDayPanel.setVisible(false);
                        pageNum = 1;
                        givenRecipeScreen();
                        setUpGivenRecipeListeners();
                    }
                } else if (o == repBackButton) { //back button to see how much ingredients you have left over after the day's sales
                    reportPanel.setVisible(false);
                    reportInfoLeft.setVisible(false);
                    reportInfoRight.setVisible(false);
                    reportButtonPanel.setVisible(false);
                    reportDayPanel.setVisible(false);
                    pageNum = 7;
                    ingredientsLeft();
                    setUpIngredientsLeftListener();
                }
            }
        };
        repContButton.addActionListener(buttonListener);
        repBackButton.addActionListener(buttonListener);
    }

    /**
     * action listener if the user win/looses the game they can either play again or quit
     */
    public void setUpGameStatusListener() {
        ActionListener buttonListener = ae -> {
            Object o = ae.getSource();
            if (pageNum == 9) {
                if (o == gameRetryButton) {
                    num = rand.nextInt(25);
                    coffeeQuantity = 0;
                    creamQuantity = 0;
                    sugarQuantity = 0;
                    iceQuantity = 0;
                    money = 20;
                    gameStatusPanel.setVisible(false);
                    gameOptionsButton.setVisible(false);
                    pageNum = 0;
                    startScreen();
                    setUpButtonListeners();
                } else if (o == gameQuitButton) {
                    System.exit(0);
                }
            }
        };
        gameRetryButton.addActionListener(buttonListener);
        gameQuitButton.addActionListener(buttonListener);
    }
}




