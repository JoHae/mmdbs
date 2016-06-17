package controller;

import java.awt.image.BufferedImage;

import model.AppModel;
import model.ColorHistogram;
import model.ConcolutionColorHistogram;
import model.IFeature;
import model.Statics.ExtractionMethod;

public class FeatureExtractor {

	ExtractionMethod m;

	public IFeature process(BufferedImage image) {		
		switch (AppModel.getInstance().getSelectedExtractionMethod()) {
		case GLOBAL_COLOR_HISTOGRAM:
			// TODO: read a bin value from the ui 
			return new ColorHistogram(image,64, 1);
		case GLOBAL_EDGE_HISTOGRAM:
			// TODO: read a bin value from the ui 
			return new ConcolutionColorHistogram(image,64, 1);
		case LOCAL_EDGE_HISTOGRAM:
			// TODO: read a bin value from the ui 
			return new ConcolutionColorHistogram(image,64, 4);
		case LOCAL_COLOR_HISTOGRAM:
			// TODO: read a bin and a cell value from the ui 
			return new ColorHistogram(image,64,4);
		case TEXTURE_HARALICK_FEATURES:
			return null;
		default:
			return null;
		}
		
	}

}
