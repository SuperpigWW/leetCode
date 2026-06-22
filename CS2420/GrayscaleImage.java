package assign01;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Represents a grayscale (black and white) image as a 2D array of "pixel"
 * brightness values: 255 is "white", 127 is "gray", and 0 is "black" with
 * intermediate values in between.
 * 
 * @author CS 2420 course staff and  Haoquan Wang
 * @version 2026-01-15
 */
public class GrayscaleImage {
	private double[][] imageData; // array of pixel brightness values

	/**
	 * Initializes an image from a 2D array of doubles. This constructor creates a
	 * copy of the input array.
	 * 
	 * @param data initial pixel values
	 * @throws IllegalArgumentException if the input array is empty or "jagged"
	 *                                  meaning not all rows are the same length
	 */
	public GrayscaleImage(double[][] data) {
		if(data.length == 0 || data[0].length == 0) {
			throw new IllegalArgumentException("Image is empty");
		}

		imageData = new double[data.length][data[0].length];
		for(int row = 0; row < imageData.length; row++) {
			if(data[row].length != imageData[row].length) {
				throw new IllegalArgumentException("All rows must have the same length");
			}
			for(int col = 0; col < imageData[row].length; col++) {
				imageData[row][col] = data[row][col];
			}
		}
	}

	/**
	 * Fetches an image from the specified URL and converts it to grayscale. Uses
	 * the AWT Graphics2D class to do the conversion, so it may add an item to your
	 * dock/menu bar as if you are loading a GUI program.
	 * 
	 * @param url from which to download the image
	 * @throws IOException if the image cannot be downloaded for some reason
	 */
	public GrayscaleImage(URL url) throws IOException {
		BufferedImage inputImage = ImageIO.read(url);
		// Converts input image to grayscale based on information from
		// https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models
		BufferedImage grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(),
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g2d = grayImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, null);
		g2d.dispose();
		imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

		// Raster is basically a width x height x 1 3D array
		WritableRaster grayRaster = grayImage.getRaster();
		for(int row = 0; row < imageData.length; row++) {
			for(int col = 0; col < imageData[0].length; col++) {
				// getSample parameters are x (our column) and y (our row); i.e., "backwards"
				imageData[row][col] = grayRaster.getSampleDouble(col, row, 0);
			}
		}
	}

	/**
	 * Saves the image as a PNG file.
	 * 
	 * @param filename of the created image file
	 * @throws IOException if the file cannot be written
	 */
	public void savePNG(File filename) throws IOException {
		BufferedImage outputImage = new BufferedImage(imageData[0].length, imageData.length,
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = outputImage.getRaster();
		for(int row = 0; row < imageData.length; row++) {
			for(int col = 0; col < imageData[0].length; col++) {
				raster.setSample(col, row, 0, imageData[row][col]);
			}
		}
		ImageIO.write(outputImage, "png", filename);
	}
	
	/**
	 * Get the pixel corresponding to the specified position.
	 * 
	 * @param x x_coordinate value of the specified position
	 * @param y y_coordinate value of the specified position
	 * @return pixel on the specified position
	 * @throws IllegalArgumentException when the position is not on the image
	 */
	public double getPixel(int x, int y) {
		// Check if this position is on the image
		if (x < 0 || x >= imageData[0].length || y < 0 || y >= imageData.length)
			throw new IllegalArgumentException("The pixel is not within the image.");
		else
			return imageData[y][x];
	}
	
	/**
	 * Sets the pixel brightness value at the specified coordinates.
	 * 
	 * @param x x_coordinate value of the specified position
	 * @param y y_coordinate value of the specified position
	 * @param brightnessValue the new brightness value to set
	 * @throws IllegalArgumentException when the position is not on the image or brightness out of range
	 */
	public void setPixel(int x, int y, double brightnessValue) {
		// Check if this position is on the image
		if (x < 0 || x >= imageData[0].length || y < 0 || y >= imageData.length)
			throw new IllegalArgumentException("The pixel is not within the image.");
		// Check if the brightnessValue is in range
		else if(brightnessValue < 0 || brightnessValue > 255)
			throw new IllegalArgumentException("Brightness is out of range.");
		else
			imageData[y][x] = brightnessValue;
	}
	
	/**
	 * Compares this GrayscaleImage with another object for equality.
	 * Returns true if other is a GrayscaleImage, has the same size,
	 * and each corresponding pixel has exactly the same value as this.
	 *
	 * @param other the object to compare with
	 * @return true if the specified object is equal to this GrayscaleImage,
	 *         false otherwise
	 */
	public boolean equals(Object other) {
		if (other instanceof GrayscaleImage) {
			GrayscaleImage otherImage = (GrayscaleImage)other;
			// Check if this image is null
			if (imageData.length != otherImage.imageData.length || imageData[0].length != otherImage.imageData[0].length)
				return false;
			
			for(int row = 0; row < imageData.length; row++) {
				for(int col = 0; col < imageData[0].length; col++) {
					if (imageData[row][col] == otherImage.imageData[row][col])
						continue;
					else
						return false;
				}
			}
		}
		else
			return false;
		return true;
	}
	
	/**
	 * Computes and returns the average of all brightness values in the image.
	 *
	 * @return the average brightness value of all pixels in the image
	 * @throws IllegalArgumentException if the image does not contain any pixels
	 */
	public double averageBrightness() {
		double averageBrightness = 0;
		double count = 0;
		for(int row = 0; row < imageData.length; row++) {
			for(int col = 0; col < imageData[0].length; col++) {
				averageBrightness += imageData[row][col];
				count += 1;
			}
		}
		
		// Check if the image is null
		if (count == 0)
			throw new IllegalArgumentException("The image do not have any pixel.");
		
		return averageBrightness / count;
	}
	
	/**
	 * Creates and returns a new GrayscaleImage where the average brightness is 127.
	 * This is done by uniformly scaling each pixel (multiplying each imageData element
	 * by the same scaling value). Due to rounding errors, the new average brightness
	 * might not be 127 exactly, but it should be very close. The original image is
	 * not modified.
	 *
	 * @return a new GrayscaleImage with normalized brightness
	 */
	public GrayscaleImage normalized() {
		GrayscaleImage newSimilarImage = new GrayscaleImage(new double[imageData.length][imageData[0].length]);
		// Check if the averageBrightness value is 0
		if (this.averageBrightness() == 0) {
			for(int row = 0; row < imageData.length; row++) {
				for(int col = 0; col < imageData[0].length; col++) {
					newSimilarImage.imageData[row][col] = 127;
				}
			}
			return newSimilarImage;
		}
		
		double rate = 127 / this.averageBrightness();
		for(int row = 0; row < imageData.length; row++) {
			for(int col = 0; col < imageData[0].length; col++) {
				newSimilarImage.imageData[row][col] = imageData[row][col] * rate;
			}
		}
		return newSimilarImage;
	}
	
	/**
	 * Modifies this GrayscaleImage by inverting the brightness values.
	 * The brightness value of each pixel becomes (255 - value).
	 * This method does not create a new GrayscaleImage object and does not return anything.
	 */
	public void invert() {
		for(int row = 0; row < imageData.length; row++) {
			for(int col = 0; col < imageData[0].length; col++) {
				imageData[row][col] = 255 - imageData[row][col];
			}
		}
	}
	
	/**
	 * Updates the given GrayscaleImage such that it is "mirrored" across the y-axis.
	 * In other words, each row from the image is reversed.
	 *
	 * @param image the GrayscaleImage to be mirrored
	 */
	public static void mirrored(GrayscaleImage image) {
		if (image.imageData.length == 0 || image.imageData[0].length == 0) {
		    return; 
		}
		
		for(int row = 0; row < image.imageData.length; row++) {
			for(int col = 0; col < image.imageData[0].length / 2; col++) {
				// Calculate which column should to exchanged
				int mirroredCol = image.imageData[0].length - 1 - col;
				// Use a middle value to exchange
				double middleNum = image.imageData[row][col];
				image.imageData[row][col] = image.imageData[row][mirroredCol];
				image.imageData[row][mirroredCol] = middleNum;
			}
		}
	}

}