package market.dataanalyser.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

public class VolumePriceTrend implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int date;
	private BigDecimal vpt;
	
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public BigDecimal getVpt() {
		return vpt;
	}
	public void setVpt(BigDecimal vpt) {
		this.vpt = vpt;
	}
	
}
