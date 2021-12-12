package org.estrada.votedroid.bd;

import androidx.room.Dao;
import androidx.room.Insert;

import org.estrada.votedroid.modele.VDQuestion;

@Dao
public interface MonDao {
    @Insert
    Long insertQuestion(VDQuestion v);

//TODO Compléter les autres actions

}