package org.estrada.votedroid.bd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.estrada.votedroid.modele.VDQuestion;
import org.estrada.votedroid.modele.VDVote;

import java.util.List;

@Dao
public abstract class MonDao {

    @Insert
    public abstract Long insertQuestion(VDQuestion v);

    @Insert
    public abstract Long creerVote(VDVote v);

@Query("select * from VDQuestion")
public abstract List<VDQuestion> tousLesQuestions();


//TODO Compléter les autres actions

}