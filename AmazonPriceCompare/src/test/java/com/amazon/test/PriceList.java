package com.amazon.test;


public class PriceList {
	
	private String ranking;
	private String amazonUrl;
	private String productTitle;
	private String ebayUrl;
	private String walmartUrl;
	private String websiteName;
	
	private double ebayPrice;
	private double walmartPrice;
	private double amazonPrice;
	private double amazonReducedPrice;
	private String comments;
	private String cheapest;
	private double profitPercent;
	
	
	public String getRanking() {
		return ranking;
	}


	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	
	public String getAmazonUrl() {
		return amazonUrl;
	}
	
	
	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getEbayUrl() {
		return ebayUrl;
	}


	public String getProductTitle() {
		return productTitle;
	}


	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}


	public void setEbayUrl(String ebayUrl) {
		this.ebayUrl = ebayUrl;
	}


	public String getWalmartUrl() {
		return walmartUrl;
	}


	public double getProfitPercent() {
		return profitPercent;
	}


	public void setProfitPercent(double profitPercent) {
		this.profitPercent = profitPercent;
	}


	public String getCheapest() {
		return cheapest;
	}


	public void setCheapest(String cheapest) {
		this.cheapest = cheapest;
	}


	public void setWalmartUrl(String walmartUrl) {
		this.walmartUrl = walmartUrl;
	}


	public void setAmazonUrl(String amazonUrl) {
		this.amazonUrl = amazonUrl;
	}



	public double getAmazonReducedPrice() {
		return amazonReducedPrice;
	}


	public void setAmazonReducedPrice(double amazonReducedPrice) {
		this.amazonReducedPrice = amazonReducedPrice;
	}


	public String getWebsiteName() {
		return websiteName;
	}
	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}
	public double getEbayPrice() {
		return ebayPrice;
	}
	public void setEbayPrice(double ebayPrice) {
		this.ebayPrice = ebayPrice;
	}
	public double getWalmartPrice() {
		return walmartPrice;
	}
	public void setWalmartPrice(double walmartPrice) {
		this.walmartPrice = walmartPrice;
	}
	public double getAmazonPrice() {
		return amazonPrice;
	}
	public void setAmazonPrice(double amazonPrice) {
		this.amazonPrice = amazonPrice;
	}
	
	public PriceList() {
	super();
	this.productTitle="title blank";
	this.amazonUrl = "No amazonUrl";
	this.ebayUrl = "No ebayUrl";
	this.walmartUrl = "No walmartUrl";
	this.websiteName = "websiteName";
	this.ebayPrice = -1;
	this.walmartPrice = -1;
	this.amazonPrice = -1;
	this.amazonReducedPrice = -1;
	this.comments = "";
	this.cheapest = "cheapest NA";
	this.profitPercent = 0;
	this.ranking = "No rank";
	}


	public PriceList(String ranking, String amazonUrl, String productTitle, String ebayUrl, String walmartUrl,
			String websiteName, double ebayPrice, double walmartPrice, double amazonPrice, double amazonReducedPrice,
			String comments, String cheapest, double profitPercent) {
		super();
		this.ranking = ranking;
		this.amazonUrl = amazonUrl;
		this.productTitle = productTitle;
		this.ebayUrl = ebayUrl;
		this.walmartUrl = walmartUrl;
		this.websiteName = websiteName;
		this.ebayPrice = ebayPrice;
		this.walmartPrice = walmartPrice;
		this.amazonPrice = amazonPrice;
		this.amazonReducedPrice = amazonReducedPrice;
		this.comments = comments;
		this.cheapest = cheapest;
		this.profitPercent = profitPercent;
	}


}