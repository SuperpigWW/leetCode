package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * For testing the GrayscaleImage class.
 * 
 * @author CS 2420 course staff and ***STUDENT FILL YOUR NAME IN***
 * @version ***STUDENT FILL IN THE DATE***
 */
public class GrayscaleImageTest {
	private GrayscaleImage smallSquare;
	private GrayscaleImage smallWide;

	/**
	 * A helper method that checks each pixel against an expected array of values.
	 * This assumes that the getPixel method is working correctly.
	 * 
	 * @param expected array of values
	 * @param actual GrayscaleImage to compare to the expected values
	 */
	private void assertPixelValuesEqual(double[][] expected, GrayscaleImage actual) {
		for(int row = 0; row < expected.length; row++) {
			for(int col = 0; col < expected[0].length; col++) {
				assertEquals(expected[row][col], actual.getPixel(col, row), expected[row][col] * .0001,
						"pixel at row: " + row + " col: " + col + " incorrect");
			}
		}
	}

	@BeforeEach
	public void setUp() {
		smallSquare = new GrayscaleImage(new double[][] { { 1, 2 }, { 3, 4 } });
		smallWide = new GrayscaleImage(new double[][] { { 1, 2, 3 }, { 4, 5, 6 } });
	}

	@Test
	public void testGetPixel() {
		assertEquals(1, smallWide.getPixel(0, 0));
		assertEquals(2, smallWide.getPixel(1, 0));
		assertEquals(3, smallWide.getPixel(2, 0));
		assertEquals(4, smallWide.getPixel(0, 1));
		assertEquals(5, smallWide.getPixel(1, 1));
		assertEquals(6, smallWide.getPixel(2, 1));
	}

	@Test
	public void testEqualsSelf() {
		assertEquals(smallSquare, smallSquare, "Image was not equal to itself");
	}

	@Test
	public void testEqualsEquivalent() {
		GrayscaleImage equivalent = new GrayscaleImage(new double[][] { { 1, 2 }, { 3, 4 } });
		assertEquals(smallSquare, equivalent, "Image was not equal to a distinct but equivalent image");
	}

	@Test
	public void testNormalized() {
		GrayscaleImage smallNorm = smallSquare.normalized();
		double scale = 127 / 2.5;
		double[][] expected = new double[][] { { scale, 2 * scale }, { 3 * scale, 4 * scale } };
		assertPixelValuesEqual(expected, smallNorm);
	}

	@Test
	public void testInvert() {
		smallSquare.invert();
		double[][] expected = new double[][] { { 254, 253 }, { 252, 251 } };
		assertPixelValuesEqual(expected, smallSquare);
	}

	@Test
	public void testMirrored() {
		double[][] expected = new double[][] { { 2, 1 }, { 4, 3 } };
		GrayscaleImage.mirrored(smallSquare);
		assertPixelValuesEqual(expected, smallSquare);
	}
}