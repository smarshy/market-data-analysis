package market.dataanalyser.ejb;

import javax.ejb.Remote;

@Remote
public interface GreetingRemote {
	
	public void compose_message(String userName);
	public String get_message();

}
