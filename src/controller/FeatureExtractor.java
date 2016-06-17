package controller;

import java.awt.image.BufferedImage;

import model.AppModel;
import model.ColorHistogram;
import model.IFeature;
import model.Statics.ExtractionMethod;

public class FeatureExtractor {

	ExtractionMethod m;

	public IFeature process(BufferedImage image) {
		AppModel model = AppModel.getInstance();
		switch (model.getSelectedExtractionMethod()) {
		case GLOBAL_COLOR_HISTOGRAM:
			// TODO: read a bin value from the ui 
			return new ColorHistogram(image,64, 1);
		case GLOBAL_EDGE_HISTOGRAM:
			return null;
		case LOCAL_COLOR_HISTOGRAM:
			// TODO: read a bin and a cell value from the ui 
			return new ColorHistogram(image,128,4);
		case TEXTURE_HARALICK_FEATURES:
			return null;
		default:
			return null;
		}
	}

}
