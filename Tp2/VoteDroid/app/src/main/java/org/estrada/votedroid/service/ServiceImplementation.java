package org.estrada.votedroid.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.estrada.votedroid.Question;
import org.estrada.votedroid.bd.BD;
import org.estrada.votedroid.exceptions.MauvaisVote;
import org.estrada.votedroid.exceptions.MauvaiseQuestion;
import org.estrada.votedroid.modele.VDQuestion;
import org.estrada.votedroid.modele.VDVote;

public class ServiceImplementation {

    private static ServiceImplementation single_instance = null;
    private BD maBD;

    private ServiceImplementation (BD maBD){
        this.maBD = maBD;
    }

    public static ServiceImplementation getInstance(BD maBD)
    {
        if (single_instance == null)
            single_instance = new ServiceImplementation(maBD);

        return  single_instance;
    }


    public void creerQuestion(VDQuestion vdQuestion) throws MauvaiseQuestion {
        // Validation
        if (vdQuestion.texteQuestion == null || vdQuestion.texteQuestion.trim().length() == 0) throw new MauvaiseQuestion("Question vide");
        if (vdQuestion.texteQuestion.trim().length() < 5) throw new MauvaiseQuestion("Question trop courte");
        if (vdQuestion.texteQuestion.trim().length() > 255) throw new MauvaiseQuestion("Question trop longue");
        if (vdQuestion.idQuestion != null) throw new MauvaiseQuestion("Id non nul. La BD doit le gerer");

        //Doublon du texte de la question
        for (VDQuestion q : toutesLesQuestions()){
            if (q.texteQuestion.toLowerCase().equals(vdQuestion.texteQuestion.toUpperCase())){
                throw new MauvaiseQuestion("Question existante");
            }
        }

        //Ajout
        vdQuestion.idQuestion = maBD.monDao().creerQuestion(vdQuestion);
    }


    public  void  creerVote(VDVote vdVote) throws MauvaisVote {
        //  Validation
        if (vdVote.nomDuVotant == null || vdVote.nomDuVotant.trim().length() == 0) throw new MauvaisVote("Nom du votant vide");
        if (vdVote.nomDuVotant.trim().length() < 4 ) throw new MauvaisVote("Nom du votant trop court");
        if (vdVote.nomDuVotant.trim().length() > 255) throw new MauvaisVote("Nom du votant trop long");
        if (vdVote.idVote != null) throw new MauvaisVote("Id non nul. La BD doit le gerer");

        // doublon
        for (VDVote q : maBD.monDao().tousLesVotes()){
            if (q.nomDuVotant.toLowerCase().equals(q.nomDuVotant.toUpperCase())){
                throw new MauvaisVote("Vote deja existant");
            }
        }
        //Ajout
        vdVote.idVote = maBD.monDao().creerVote(vdVote);

    }

    public List<VDQuestion> toutesLesQuestions() {
        ArrayList<VDQuestion> arrayList = (ArrayList<VDQuestion>) maBD.monDao().tousLesQuestions();

        //TODO Trier la liste recue en BD par nombre de votes et la retourner
        // Collections.sort(arrayList.get(maBD.monDao().), Collections.reverseOrder());

        return arrayList;
    }


    public float moyenneVotes(VDQuestion question) {return 0;}

    public float ecartTypeVotes(VDQuestion question) {return 0;}


    public Map<Integer,Integer> distributionVotes(VDQuestion question){return null;}

    public void supprimerQuestions(){

    }

    public  void supprimerVotes(){

    }

}
