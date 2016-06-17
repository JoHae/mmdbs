package model;

import controller.ISimilarityMeasure;

public interface IFeature {
	public double calculateSimilarity(IFeature f);
	public IFeature setMeasure(ISimilarityMeasure measure);
}
