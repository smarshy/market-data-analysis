package market.dataanalyser.jpa;

import java.io.Serializable;
import java.util.List;

import market.dataanalyser.jpa.Nasdaq;

public class CompareStocks implements Serializable {


	private static final long serialVersionUID = 1L;
	
	Nasdaq stock1;
	Nasdaq stock2;
	List<Nasdaq> stockList1;
	List<Nasdaq> stockList2;
	public Nasdaq getStock1() {
		return stock1;
	}
	public void setStock1(Nasdaq stock1) {
		this.stock1 = stock1;
	}
	public Nasdaq getStock2() {
		return stock2;
	}
	public void setStock2(Nasdaq stock2) {
		this.stock2 = stock2;
	}
	public List<Nasdaq> getStockList1() {
		return stockList1;
	}
	public void setStockList1(List<Nasdaq> stockList1) {
		this.stockList1 = stockList1;
	}
	public List<Nasdaq> getStockList2() {
		return stockList2;
	}
	public void setStockList2(List<Nasdaq> stockList2) {
		this.stockList2 = stockList2;
	}
	
	/*
	Liffe stock1;
	Liffe stock2;
	Forex stock3;
	Forex stock4;
	List<Liffe> listStock1;
	List<Liffe> listStock2;
	List<Forex> listStock3;
	List<Forex> listStock4;
	
	public Liffe getStock1() {
		return stock1;
	}
	public void setStock1(Liffe stock1) {
		this.stock1 = stock1;
	}
	public Liffe getStock2() {
		return stock2;
	}
	public void setStock2(Liffe stock2) {
		this.stock2 = stock2;
	}
	public Forex getStock3() {
		return stock3;
	}
	public void setStock3(Forex stock3) {
		this.stock3 = stock3;
	}
	public Forex getStock4() {
		return stock4;
	}
	public void setStock4(Forex stock4) {
		this.stock4 = stock4;
	}
	public List<Liffe> getListStock1() {
		return listStock1;
	}
	public void setListStock1(List<Liffe> listStock1) {
		this.listStock1 = listStock1;
	}
	public List<Liffe> getListStock2() {
		return listStock2;
	}
	public void setListStock2(List<Liffe> listStock2) {
		this.listStock2 = listStock2;
	}
	public List<Forex> getListStock3() {
		return listStock3;
	}
	public void setListStock3(List<Forex> listStock3) {
		this.listStock3 = listStock3;
	}
	public List<Forex> getListStock4() {
		return listStock4;
	}
	public void setListStock4(List<Forex> listStock4) {
		this.listStock4 = listStock4;
	}*/
	

}
