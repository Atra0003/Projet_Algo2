import java.util.*;




class Ampoule {
    private int idx_interup_ligne, idx_interup_colone;
    private int[] etats_ampoule = new int[4]; 
    private int [][] combinsn_interups = {{0,0}, {0,1}, {1,0}, {1,1}}; // contient les quatre combinaisons possibles des positions des deux interrupteurs correspondant en ligne et en colonne


    public Ampoule(ArrayList<Integer> vect, int nbr_interup){
        idx_interup_ligne = vect.get(0)-1; // initialise l'attribut contenant l'indice de l'interupteur ligne dans le vecteur des interupteurs
        idx_interup_colone = vect.get(1) + ((nbr_interup/2) - 1); // initialise l'attribut contenant l'indice de l'interupteur colone dans le vecteur des interupteurs
        for(int i = 2; i<6; i+=1){etats_ampoule[i-2] = vect.get(i);} // initialise le vecteur contenant les etats allumeé/eteinte de l'ampoule en question
    }
    public Integer get_idx_interup_ligne(){return idx_interup_ligne;}
    public Integer get_idx_interup_colone(){return idx_interup_colone;}
    public boolean ampoule_est_allumee(Integer etat_interup_ligne, Integer etat_interup_colne){ // verifie si l'ampoule est allumeé selon les etats des deux interup auxquelles elle est associeé
        for(int i = 0; i<4; i+=1){ // parcour les quatres etats allumeé/eteinte de l'ampoule ainsi que les quatre combinaison possible des deux interup auxquelles l'ampoule est associeé
            if (etats_ampoule[i] == 1 && combinsn_interups[i][0] == etat_interup_ligne && combinsn_interups[i][1] == etat_interup_colne){ // si l'ampoule s'allume pour cette combinaison courante des deux interup auxquelles elle est associeé et que ces deux interup sont dans cette etas alors je renvoie true
                return true;
            }
        }
        return false; // si à la fin de la boucle y'a pas eu un return true c'est que l'ampoule ne s'allume pas 
    }
}





public class ProblemeNbrMaxAmpoulesAllumees {

    private ArrayList<Integer> vect_interupteurs = new ArrayList<Integer>();
    private ArrayList<Ampoule> vect_ampoules = new ArrayList<Ampoule>();
    private int nbr_interup;
    private int nbr_max_ampoules_allumees = 0;


    public ProblemeNbrMaxAmpoulesAllumees(ArrayList<ArrayList<Integer>> instnce_prblm){
        int nbr_interup_ligne = 0, nbr_interup_colone = 0;
        for(ArrayList<Integer> buff : instnce_prblm){ // determine le nombre d'interupteurs en lignes et en colone 
            if (buff.get(0) > nbr_interup_ligne) {nbr_interup_ligne = buff.get(0);}
            if (buff.get(1) > nbr_interup_colone) {nbr_interup_colone = buff.get(1);}
        }
        nbr_interup = nbr_interup_ligne + nbr_interup_colone;
        for(int i = 0; i<nbr_interup; i+=1){vect_interupteurs.add(0);} // initialise le vecteur des interupteurs 
        for(ArrayList<Integer> buff : instnce_prblm){vect_ampoules.add(new Ampoule(buff, nbr_interup));} // initialise le vecteur d'ampoules
    }
    int determiner_nbr_ampoules_allumees(){ // determine le nombre d'ampoule allumeé pour une configuration donneé des etats des interups
        int nbr_ampoule_allumee = 0;
        for(Ampoule ampoule_crte : vect_ampoules){ // parcourt toutes les ampoules et pour chaque ampoule verifie si elle est allumeé
            if(ampoule_crte.ampoule_est_allumee(vect_interupteurs.get(ampoule_crte.get_idx_interup_ligne()), vect_interupteurs.get(ampoule_crte.get_idx_interup_colone()))){
                nbr_ampoule_allumee += 1; // incremente le nombre d'ampoule allumeé si l'ampoule courante est allumeé 
            }
        }
        return nbr_ampoule_allumee;
    }
    void determiner_best_config_interups(int idx){ // determine la meilleur configuration des interupteurs (la configuration qui permet d'allumer le plus grand nombre d'ampoules allumeés)
        if(idx == nbr_interup){ // si l'indice actuelle est egal au nombre d'interups c'est ce qu'on une solution partielle
            if(determiner_nbr_ampoules_allumees() > nbr_max_ampoules_allumees){ //on verifie si cette solution partielle est meilleur que toutes celles qu'on eu depuis le debut 
                nbr_max_ampoules_allumees = determiner_nbr_ampoules_allumees(); // si oui on met a jour notre meilleur solution 
            }
        }
        else{ // si nous avons pas encore une solution partiielle
            vect_interupteurs.set(idx, 1); // on fermre/allume l'interupteur d'indice idx 
            determiner_best_config_interups(idx + 1);
            vect_interupteurs.set(idx, 0); // on ouvre/eteint l'interupteur d'indice idx 
            determiner_best_config_interups(idx + 1);
        }
    }

    int determiner_nbr_max_ampoules_allumees(int idx){ // determine le nbr maximum d'ampoules qui peuvent s'allumer de maniere simultaneé pour cet instance de probleme ci
        determiner_best_config_interups(idx); 
        return nbr_max_ampoules_allumees;
    }

}
