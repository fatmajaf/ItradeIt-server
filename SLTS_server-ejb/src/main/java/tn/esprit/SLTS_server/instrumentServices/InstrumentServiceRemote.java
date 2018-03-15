package tn.esprit.SLTS_server.instrumentServices;




import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.Bond;
import tn.esprit.SLTS_server.persistence.Instrument;
import tn.esprit.SLTS_server.persistence.User;

@Remote
public interface InstrumentServiceRemote {
	public void create(Instrument instrument);
	List<Instrument> findAll();
	public void delete(Integer id);
	public void updatee(Integer id,float changee);
	public Instrument find(Integer id);
	void InstrumentUser(int instrumentId, int userId);
	List<String> findAllCustomers() ;
	public List<Bond> findAllBonds();
	
	

}