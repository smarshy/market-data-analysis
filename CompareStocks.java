package market.dataanalyser.jpa;

import java.io.Serializable;
import java.util.List;

import market.dataanalyser.jpa.Nasdaq;

public class CompareStocks implements Serializable {


	private static final long serialVersionUID = 1L;
	Nasdaq stock1;
	Nasdaq stock2;
	List<Nasdaq> listStock1;
	List<Nasdaq> listStock2;
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
	public List<Nasdaq> getListStock1() {
		return listStock1;
	}
	public void setListStock1(List<Nasdaq> listStock1) {
		this.listStock1 = listStock1;
	}
	public List<Nasdaq> getListStock2() {
		return listStock2;
	}
	public void setListStock2(List<Nasdaq> listStock2) {
		this.listStock2 = listStock2;
	}

}
