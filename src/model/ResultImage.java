/**
 * ResultImage.java  - 11.06.2016
 *
 * @author Johannes Haeussler - Johannes.3.Haeussler(at)uni-konstanz.de
 * @version 1.0
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;

public class ResultImage implements Comparable<ResultImage> {

	private BufferedImage image;
	private BufferedImage thumbnail;
	private int rank;
	private double similarity;
	private String category;
	private File file;

	public BufferedImage getImage() {
		return this.image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getRank() {
		return this.rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public double getSimilarity() {
		return this.similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BufferedImage getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(BufferedImage thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public int compareTo(ResultImage o) {
		if (this.similarity > o.getSimilarity()) {
			return -1;
		} else {
			return 1;
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
