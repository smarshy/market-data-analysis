package market.dataanalyser.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import market.dataanalyser.jpa.Testtable;


/**
 * Session Bean implementation class Greeting
 */
@Stateless
@Remote(GreetingRemote.class)
@Local(GreetingLocal.class)
public class Greeting implements GreetingRemote, GreetingLocal {

    /**
     * Default constructor. 
     */
	
	private String message;
	
	@PersistenceContext(name="MarketAnalyserJPA")
	private EntityManager em;
	
    public Greeting() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void compose_message(String userName) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT t.var2 FROM Testtable as t WHERE t.id=2";
        System.out.println(sql);
        Query query = em.createQuery(sql);
        
        System.out.println("hi");
        
        String dbString = (String) query.getSingleResult();
		message="Hello"+ userName + "\n"+ dbString;
		System.out.println(message);
       
	}

	@Override
	public String get_message() {
		return message;
	}

}
