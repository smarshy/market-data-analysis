package market.dataanalyser.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

public class MovAvgTrend implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int date;
	private BigDecimal movingAverage;
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public BigDecimal getMa() {
		return movingAverage;
	}
	public void setMa(BigDecimal movingAverage) {
		this.movingAverage = movingAverage;
	}

}