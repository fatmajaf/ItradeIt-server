package tn.esprit.SLTS_server.presentation.mbeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;



@ManagedBean
@ApplicationScoped
public class Data2 {
public Type[] getTypes(){
	return Type.values();
}

}
