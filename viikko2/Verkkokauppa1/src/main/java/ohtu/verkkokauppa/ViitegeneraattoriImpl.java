package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViitegeneraattoriImpl implements Viitegeneraattori {
    
    private int seuraava;
    
    @Autowired
    public ViitegeneraattoriImpl(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
