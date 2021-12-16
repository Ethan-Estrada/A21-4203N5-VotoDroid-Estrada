package org.estrada.votedroid.bd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.estrada.votedroid.modele.VDQuestion;
import org.estrada.votedroid.modele.VDVote;

@Database(entities = {VDQuestion.class, VDVote.class}, version = 2)
public abstract class BD extends RoomDatabase {
    public abstract MonDao monDao();
}
