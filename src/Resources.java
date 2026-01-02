/**
 * Resources Class
 * A class you with a methods to easily make Java Swing Components (SuperClass of GUI)
 * Written by: Shorna Kundu
 * Last modified: June 19, 2024
 */
import javax.swing.*;
import java.awt.*;

public class Resources {
    Container container;    // creating a container --> a component that you can add and hold other components

    /**
     * A method to create JButtons
     * @param panel -> adds button to panel
     * @param sizeNum --> sets size of text in button
     * @param buttonText --> sets text of button
     * @param buttonSubtitle --> sets text when you hover button
     * @return --> returns the JButton
     */
    public JButton button(JPanel panel, int sizeNum, String buttonText, String buttonSubtitle) {
        JButton button = new JButton(buttonText);
        button.setBackground(Color.BLACK); // colour of button
        button.setForeground(Color.WHITE); // colour of text
        button.setFont(new Font("Times New Roman", Font.PLAIN, sizeNum)); //text font + size
        button.setToolTipText(buttonSubtitle); //text when you hover button
        panel.add(button); //adding button to panel
        return button;
    }

    /**
     * A method to create panels
     * @param x --> setting the x coordinates
     * @param y --> setting the y coordinates
     * @param width --> setting the width of the panel
     * @param height --> setting the height of the panel
     * @return --> returning the JPanel
     */
    public JPanel panel(int x, int y, int width, int height) {
        JPanel panel = new JPanel(); //new panel
        panel.setBounds(x, y, width, height); //size of panel
        panel.setBackground(Color.BLACK); //setting bg colour
        container.add(panel); //adding component to method
        return panel;
    }

    /**
     * A method to create a label
     * @param panel --> adding label to panel
     * @param labelText --> text for the label
     * @param sizeNum --> text size for the label
     * @return the JLabel
     */
    public JLabel label(JPanel panel, String labelText, int sizeNum) {
        JLabel label = new JLabel(labelText);
        label.setBackground(Color.BLACK); //bg colour
        label.setForeground(Color.WHITE); // text colour
        label.setFont(new Font("Times New Roman", Font.PLAIN, sizeNum)); //text size + font type
        panel.add(label);
        return label;
    }

    /*
    https://www.youtube.com/watch?v=ccUdvsj4L0U
    used the video above on how to make a JTable + JScrollPane
     */
    /**
     * A method to create a table
     * @param columnNames --> setting the columns
     * @param data --> setting the row data
     * @return --> returning JTable
     */
    public JTable table (String [] columnNames,Object[][] data){
        JTable table = new JTable(data,columnNames);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        return table;
    }

    /**
     * A method to create the JScrollPane
     * @param table --> addi the JTable to the JScrollPane
     * @param x --> setting x coordinates
     * @param y --> setting y coordinates
     * @param width --> setting width
     * @param height --> setting height
     * @return --> return the JScrollPane
     */
    public JScrollPane scp (JTable table, int x, int y, int width, int height){
       JScrollPane scp = new JScrollPane(table);
        scp.setBounds(x,y,width,height);
        container.add(scp);
        return scp;
    }

    /**
     * method to make instructions page
     * @return --> returning JTextArea to display information for specific the rules page
     */
    public JTextArea getTextArea(){
        JTextArea msg = new JTextArea("Welcome to Bora's Café!\n" +
                "\n" +
                "You have an unlimited amount of days to make $50, to help Bora upgrade her Café. Don't run out of money! You'll have complete control over inventory, supplies and advertisements. \n" +
                "\n" +
                "To mange your Café you need to make decisions everyday on how much Ingredients you are going to make and how much advertising you are going to do. Each day Bora's gives you a recipe which you can make as long as you buy enough ingredients. The weather in this game plays a role in how many customers you get everyday. That is why reading the weather report is important. When the temperature is bad don't expect to sell much, so buy as much as you think you will sell. There are 3 tiers for advertising, poster, flyer and an online advertisement that increase sales if you made enough drinks for the day. The price for each drink is set by Bora, who's asking you to run her business while she visits her Abuela. \n" +
                "\n" +
                "You will begin with $20 cash as Bora felt generous. At the end of every day you will see your expenses, assets, profit, drinks sold and made. Good luck helping Bora! \n");
        msg.setLineWrap(true);
        msg.setEditable(false); // doesn't allow user to edit
        msg.setWrapStyleWord(true);
        return msg;
    }

    /*
    Used the websites bellow on how to make JTextArea
    https://community.spiceworks.com/t/how-to-wrap-text-string-in-jlabel/905237/3
    https://www.geeksforgeeks.org/java-swing-jtextarea/
    */
    /**
     * A method to make a text area to show multi line info
     * @param panel --> adding JTextArea to panel
     * @param textAreaText --> setting text for text are
     * @param x --> x coordinates
     * @param y --> y coordinates
     * @param width --> width of text area
     * @param height --> height of text area
     * @param sizeNum --> size of text
     * @return --> returning the JTextArea
     */
    public JTextArea textArea(JPanel panel, String textAreaText, int x, int y, int width, int height, int sizeNum) {
        JTextArea textArea = new JTextArea(textAreaText);
        textArea.setBounds(x, y, width, height);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, sizeNum));
        textArea.setLineWrap(true); // moves to next line automatically
        textArea.setEditable(false); // doesn't allow user to edit
        textArea.setWrapStyleWord(true);
        panel.add(textArea);
        return textArea;
    }

    /*
    watched this video on how to add images to a label:
    https://www.youtube.com/watch?v=Kmgo00avvEw --> 12:09
    got the weather assets from: https://spoadr.itch.io/meteo-icons
    got the cafe image asset from: https://pixelpossumstudio.itch.io/cafe-and-tea-shop-2d
     */

    /**
     * a method for a ImageIcon --> to display image
     * @param imageLink --> image name
     * @param label --> adding to the label
     * @return --> returning ImageIcon
     */
    public ImageIcon image (String imageLink, JLabel label){
        ImageIcon image = new ImageIcon(imageLink);
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER); //setting position
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(10);
        return image;
    }
}
