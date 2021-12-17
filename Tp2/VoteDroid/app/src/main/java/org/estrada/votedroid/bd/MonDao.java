package org.estrada.votedroid.bd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import org.estrada.votedroid.modele.VDQuestion;
import org.estrada.votedroid.modele.VDVote;

import java.util.List;

@Dao
public abstract class MonDao {

    @Insert
    public abstract Long creerQuestion(VDQuestion v);

    @Insert
    public abstract Long creerVote(VDVote v);

    @Query("select * from VDVote where nomDuVotant")
    public abstract List<VDVote> tousLesVotes();

@Query("select * from VDQuestion")
public abstract List<VDQuestion> tousLesQuestions();

@Transaction
public Long CreerQuestionVote(VDQuestion qt, List<VDVote> vt){
    Long id = this.creerQuestion(qt);
    for (VDVote v: vt){
        v.questionId = id;
        this.creerVote(v);
    }
    return id;
}


//TODO Compléter les autres actions

}