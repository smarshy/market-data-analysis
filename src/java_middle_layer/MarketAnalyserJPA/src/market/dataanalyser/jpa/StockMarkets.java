package market.dataanalyser.jpa;

import java.io.Serializable;
import java.util.List;

public class StockMarkets implements Serializable{
	List<String> nasdaqStockList;
	List<String> liffeStockList;
	List<String> forexStockList;
	Nasdaq defaultStock;
	public List<String> getNasdaqStockList() {
		return nasdaqStockList;
	}
	public void setNasdaqStockList(List<String> nasdaqStockList) {
		this.nasdaqStockList = nasdaqStockList;
	}
	public List<String> getLiffeStockList() {
		return liffeStockList;
	}
	public void setLiffeStockList(List<String> liffeStockList) {
		this.liffeStockList = liffeStockList;
	}
	public List<String> getForexStockList() {
		return forexStockList;
	}
	public void setForexStockList(List<String> forexStockList) {
		this.forexStockList = forexStockList;
	}
	public Nasdaq getDefaultStock() {
		return defaultStock;
	}
	public void setDefaultStock(Nasdaq defaultStock) {
		this.defaultStock = defaultStock;
	}
	
	
}
