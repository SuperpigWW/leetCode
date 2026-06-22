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
 * @author CS 2420 course staff and ***STUDENT FILL YOUR NAME IN***
 * @version ***STUDENT FILL IN THE DATE***
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

	// TODO: Add all specified methods below. Then delete this TODO comment.

}