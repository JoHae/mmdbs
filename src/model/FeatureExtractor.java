package model;

import java.awt.image.BufferedImage;

import model.Statics.ExtractionMethod;

public class FeatureExtractor {

	ExtractionMethod m;

	public FeatureExtractor(ExtractionMethod m) {
		super();
		this.m = m;
	}

	public Feature process(BufferedImage image) {
		switch (m) {
		case GLOBAL_COLOR_HISTOGRAM:
			return new ColorHistogram(image,64, false);
		case GLOBAL_EDGE_HISTOGRAM:
			return null;
		case LOCAL_COLOR_HISTOGRAM:
			return new ColorHistogram(image,64,true);
		case TEXTURE_HARALICK_FEATURES:
			return null;
		default:
			return null;
		}
	}

}
