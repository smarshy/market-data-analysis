package market.dataanalyser.ejb;

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import market.dataanalyser.jpa.Nasdaq;
import market.dataanalyser.jpa.StockMarkets;
import market.dataanalyser.jpa.VolumePriceTrend;
import market.dataanalyser.jpa.CompareStocks;
import market.dataanalyser.jpa.Forex;
import market.dataanalyser.jpa.Liffe;
import market.dataanalyser.jpa.MovingAverageTrend;

@Remote
public interface MarketDataAnalyserBeanRemote {

	public void compose_message(String userName);

	public String get_message();

	public StockMarkets listAllStocks();

	public Nasdaq fetchNasdaqDetails(String tickerName);

	public Liffe fetchLiffeDetails(String tickerName);

	public Forex fetchForexDetails(String tickerName);

	public List<Nasdaq> fetchNasdaqVariation(String ticker);
	
	public List<Liffe> fetchLiffeVariation(String ticker);
	
	public List<Forex> fetchForexVariation(String ticker);

	public List<Liffe> fetchLiffeVariation(String ticker, int fromDate, int toDate);

	public List<Forex> fetchForexVariation(String ticker, int fromDate, int toDate);

	public List<Nasdaq> fetchStockVariation(String ticker, int fromDate, int toDate);
	

	public BigDecimal isArrowUp(String ticker);

	public CompareStocks compareTwoStocks(String ticker1, String ticker2, int fromDate, int toDate);

	public List<String> listAllStocksByRegion(String filterRegion);

	// public List<String> listAllStocksByFilter(String filterSegment,String
	// filterRegion);
	public List<String> listAllStocksByFilter(String filterSegment, String filterRegion, String exchangeMarket);

	public List<VolumePriceTrend> calculateVolumePriceTrend(String ticker);

	public List<MovingAverageTrend> calculateMovingAverageTrend(String ticker);

}
