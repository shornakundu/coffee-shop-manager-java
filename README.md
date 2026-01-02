# Bora’s Café

## Overview

Dora’s Café is a Java Swing–based simulation game where the player manages a small café over multiple days. Each day, the player buys ingredients, reacts to weather conditions, and decides whether to advertise in order to maximize profit. The goal is to grow the café’s money balance while avoiding running out of funds.

The game combines decision-making, resource management, and randomness to create different outcomes each playthrough.

---

## Gameplay Summary

- The player starts with $20.
- Each day follows a fixed flow:
  1. View the daily recipe.
  2. Check the weather forecast.
  3. Buy ingredients at the marketplace.
  4. Decide whether to advertise.
  5. Run the café for the day.
  6. Review the daily sales report.

The number of customers depends on the weather and whether advertising was purchased. More customers mean higher sales but also higher ingredient usage.

The player wins by reaching $50.  
The game ends if the player runs out of money.

---

## How to Play

1. **Start the Game**  
   Launch the application and click the Start button on the main menu.

2. **View the Recipe**  
   A random coffee recipe is selected for the day. The recipe determines which ingredients are required.

3. **Check the Weather**  
   Weather affects how many customers will visit the café. Better weather means more customers.

4. **Visit the Marketplace**  
   Buy ingredients needed for the recipe. You can only buy what you can afford.

5. **Advertise (Optional)**  
   Advertising increases the number of customers for the day at an additional cost.

6. **Run the Café**  
   The café serves as many customers as inventory allows. Ingredient usage is calculated automatically.

7. **View the Daily Report**  
   At the end of the day, a report shows:
   - Money earned
   - Money spent
   - Remaining inventory
   - Overall performance

8. **Continue or End**  
   Continue to the next day, win by reaching $50, or lose if funds reach zero.

---

## Project Structure

### Coffee.java
Data model representing a coffee recipe retrieved from an external API.

Stores:
- Title
- Description
- Ingredients
- Image reference
- ID

Includes constructors, getters, and a toString method for debugging.

---

### Resources.java
A UI helper superclass used by the GUI.

Provides reusable methods for creating Swing components such as:
- Buttons
- Panels
- Labels
- Tables
- Text areas
- Images

Ensures consistent styling across all screens.

---

### GUI.java
Handles the full graphical interface and core game logic.

Responsibilities include:
- Building all screens (start, recipe, weather, marketplace, ads, café, report, game over, win)
- Managing game state (money, inventory, day count, weather, recipes)
- Calculating sales limits and ingredient usage
- Preventing negative money or inventory values
- Handling win and loss conditions
- Registering button actions to drive gameplay

---

### Main.java
Application entry point.

Responsibilities:
- Fetches coffee recipe data from a public coffee API
- Uses Gson to parse the JSON response into Coffee objects
- Passes the recipe data into the GUI and starts the application

---

## External API Usage

This project uses a public coffee recipe API to retrieve real coffee data at runtime.

- Data is fetched using an HTTP GET request.
- Gson is used to convert the JSON response into Java objects.
- Each day, a random recipe from the fetched data is selected for gameplay.

The API provides:
- Recipe names
- Descriptions
- Ingredient lists
- Image references

---

## Notes

- Money and inventory values are clamped to prevent invalid states.
- Weather and recipes are randomized to increase replayability.
- The game logic and UI are fully integrated into a single playable application.

## References
API:
https://api.sampleapis.com/coffee/iced
