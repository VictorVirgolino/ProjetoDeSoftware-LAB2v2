package ProjetoDeSoftwareLAB2.demo.tools;

import ProjetoDeSoftwareLAB2.demo.entities.Disciplina;

import java.util.Comparator;

public class ComparatorLike implements Comparator<Disciplina> {

    @Override
    public int compare(Disciplina d1, Disciplina d2){
        if(d1.getLikes() < d2.getLikes()){
            return -1;
        }
        else if(d1.getLikes() > d2.getLikes()) {
            return 1;
        }
        else{
            return 0;
        }
    }

}
