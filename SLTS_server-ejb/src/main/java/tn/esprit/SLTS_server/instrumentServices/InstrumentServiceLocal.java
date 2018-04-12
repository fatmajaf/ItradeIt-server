package tn.esprit.SLTS_server.instrumentServices;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.SLTS_server.persistence.Instrument;



@Local
public interface InstrumentServiceLocal {
	
	public void create(Instrument instrument);
	List<Instrument> findAll();
	public void delete(Integer id);
	public void updatee(Integer id, float changee);
	

}
