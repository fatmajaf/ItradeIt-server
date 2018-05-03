package tn.esprit.SLTS_server.presentation.mbeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.SLTS_server.persistence.Currency;


@ManagedBean
@ApplicationScoped
public class Currencies {
	public Currency[] getCurrencies(){
		return Currency.values();
	}
}
