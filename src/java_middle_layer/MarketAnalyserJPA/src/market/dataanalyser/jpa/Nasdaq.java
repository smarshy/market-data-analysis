package market.dataanalyser.jpa;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;

/**
 * The persistent class for the nasdaq database table.
 * 
 */
@Entity
@Table(name ="NASDAQ")
public class Nasdaq implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int indexkey;
	
	private BigDecimal closingPrice;
	private int exchangeDate;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal openingPrice;
	private String ticker;
	private int volume;
	private String sector;
	private String region;
	private BigDecimal upArrow; 
	//bi-directional many-to-one association to Exchange
	@ManyToOne
	@JoinColumn(name="market_ID")
	@JsonManagedReference
	private Exchange exchange;

	public Nasdaq() {
	}

	public int getIndexkey() {
		return this.indexkey;
	}

	public void setIndexkey(int indexkey) {
		this.indexkey = indexkey;
	}

	public BigDecimal getClosingPrice() {
		return this.closingPrice;
	}

	public void setClosingPrice(BigDecimal closingPrice) {
		this.closingPrice = closingPrice;
	}

	public int getExchangeDate() {
		return this.exchangeDate;
	}

	public void setExchangeDate(int exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public BigDecimal getHigh() {
		return this.high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return this.low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getOpeningPrice() {
		return this.openingPrice;
	}

	public void setOpeningPrice(BigDecimal openingPrice) {
		this.openingPrice = openingPrice;
	}

	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public int getVolume() {
		return this.volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public Exchange getExchange() {
		return this.exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public void setUpArrow(BigDecimal upArrow) {
		this.upArrow = upArrow;
	}
	public BigDecimal getUpArrow() {
		return upArrow;
	}
	public BigDecimal isUpArrow() {
		return upArrow;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	
}