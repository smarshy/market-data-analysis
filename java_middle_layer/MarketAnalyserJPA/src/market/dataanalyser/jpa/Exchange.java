package market.dataanalyser.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the exchanges database table.
 * 
 */
@Entity
@Table(name="exchanges")
public class Exchange implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int marketID;
	
	private String marketName;

	//bi-directional many-to-one association to Forex
	@OneToMany(mappedBy="exchange")
	private List<Forex> forexs;

	//bi-directional many-to-one association to Liffe
	@OneToMany(mappedBy="exchange")
	private List<Liffe> liffes;

	//bi-directional many-to-one association to Nasdaq
	@OneToMany(mappedBy="exchange")
	private List<Nasdaq> nasdaqs;

	public Exchange() {
	}

	public int getMarketID() {
		return this.marketID;
	}

	public void setMarketID(int marketID) {
		this.marketID = marketID;
	}

	public String getMarketName() {
		return this.marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public List<Forex> getForexs() {
		return this.forexs;
	}

	public void setForexs(List<Forex> forexs) {
		this.forexs = forexs;
	}

	public Forex addForex(Forex forex) {
		getForexs().add(forex);
		forex.setExchange(this);

		return forex;
	}

	public Forex removeForex(Forex forex) {
		getForexs().remove(forex);
		forex.setExchange(null);

		return forex;
	}

	public List<Liffe> getLiffes() {
		return this.liffes;
	}

	public void setLiffes(List<Liffe> liffes) {
		this.liffes = liffes;
	}

	public Liffe addLiffe(Liffe liffe) {
		getLiffes().add(liffe);
		liffe.setExchange(this);

		return liffe;
	}

	public Liffe removeLiffe(Liffe liffe) {
		getLiffes().remove(liffe);
		liffe.setExchange(null);

		return liffe;
	}

	public List<Nasdaq> getNasdaqs() {
		return this.nasdaqs;
	}

	public void setNasdaqs(List<Nasdaq> nasdaqs) {
		this.nasdaqs = nasdaqs;
	}

	public Nasdaq addNasdaq(Nasdaq nasdaq) {
		getNasdaqs().add(nasdaq);
		nasdaq.setExchange(this);

		return nasdaq;
	}

	public Nasdaq removeNasdaq(Nasdaq nasdaq) {
		getNasdaqs().remove(nasdaq);
		nasdaq.setExchange(null);

		return nasdaq;
	}

}