/**
 * Coffee Class
 * A class to grab the API information
 * Written by: Shorna Kundu
 * Last modified: June 19, 2024
 */
import java.util.List;

public class Coffee {

    // fields for the info from the API
    private final String title;
    private final String description;
    private final List<String> ingredients;
    private final String image;
    private final int id;

    /**
     * constructor for the coffee class
     * @param title --> name of recipe
     * @param description --> description of recipe
     * @param ingredients --> ingredients required for recipe
     * @param image --> image of recipe
     * @param id --> which number in API is it
     */
    public Coffee(String title, String description, List<String> ingredients, String image, int id) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.image = image;
        this.id = id;
    }

    /**
     * getter to get recipe name
     * @return --> recipe name
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter to get description of recipe
     * @return --> description
     */
    public String getDescription() {
        return description;
    }

    /**
     * a method to print the API information
     * @return --> returning the information
     */
    @Override
    public String toString() {
        return "Coffee{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", image='" + image + '\'' +
                ", id=" + id;
    }
}
