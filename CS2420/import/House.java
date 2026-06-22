package lab12;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A House that can be drawn. It has a position, size, and color.
 * 
 * @author CS 1420 course staff
 * @version 2025-11-11
 */
public class House {
	private int positionX;
	private int positionY;
	private int sizeX;
	private int sizeY;
	private Color color;
	
	/**
	 * Constructs a house with the given position, size, and color.
	 * 
	 * @param positionX - of the house
	 * @param positionY - of the house
	 * @param sizeX - horizontal size 
	 * @param sizeY - vertical size
	 * @param color - of the house
	 */
	public House(int positionX, int positionY, int sizeX, int sizeY, Color color) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.color = color;
	}
	
	/**
	 * Moves the position to the new coordinates.
	 * 
	 * @param newX - position
	 * @param newY - position
	 */
	public void move(int newX, int newY) {
		positionX = newX;
		positionY = newY;
	}
	
	/**
	 * Sets the color of the House.
	 * 
	 * @param newColor - for the house
	 */
	public void setColor(Color newColor) {
		color = newColor;
	}
	
	/**
	 * Draws the House to the given Graphics context.
	 * 
	 * @param g - Graphics context to draw to
	 */
	public void draw(Graphics g) {
		int eve = (int)(10 * sizeX / 50);
		int roof = (int)(25 * sizeY / 50);
		int doorX = positionX + (int)(20 * sizeX / 50);
		int doorY = positionY + (int)(45 * sizeY / 50);
		int doorWidth = (int)(15 * sizeX / 50);
		int doorHeight = (int)(30 * sizeY / 50);
		
		g.setColor(color);
		g.fillRect(positionX + eve, positionY + roof, sizeX, sizeY);
		g.setColor(Color.GRAY);
		g.fillPolygon(new int[]{positionX, positionX + sizeX + 2 * eve, positionX + eve + sizeX / 2}, 
					  new int[]{positionY + roof, positionY + roof, positionY}, 3);
		g.setColor(Color.BLACK);
		g.fillRect(doorX, doorY, doorWidth, doorHeight);
	}
}
