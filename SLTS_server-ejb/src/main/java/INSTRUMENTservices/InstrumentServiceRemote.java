package INSTRUMENTservices;




import java.util.List;

import javax.ejb.Remote;

import tn.esprit.SLTS_server.persistence.Instrument;

@Remote
public interface InstrumentServiceRemote {
	public void create(Instrument instrument);
	List<Instrument> findAll();
	public void delete(Integer id);
	public void updatee(Integer id,float changee);
	public Instrument find(Integer id);
	void InstrumentUser(int instrumentId, int userId);	
	

}
