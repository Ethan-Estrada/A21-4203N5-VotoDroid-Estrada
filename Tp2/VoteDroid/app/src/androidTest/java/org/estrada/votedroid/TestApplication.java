package org.estrada.votedroid;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.estrada.votedroid.bd.BD;
import org.estrada.votedroid.exceptions.MauvaisVote;
import org.estrada.votedroid.exceptions.MauvaiseQuestion;
import org.estrada.votedroid.modele.VDQuestion;
import org.estrada.votedroid.modele.VDVote;
import org.estrada.votedroid.service.ServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestApplication {

    private BD bd;
    private ServiceImplementation service;

    // S'exécute avant chacun des tests. Crée une BD en mémoire
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        bd = Room.inMemoryDatabaseBuilder(context, BD.class).build();
        service = ServiceImplementation.getInstance(bd);
    }


    @Test(expected = MauvaiseQuestion.class)
    public void ajoutQuestionKOVide() throws MauvaiseQuestion {
        VDQuestion question = new VDQuestion();
        question.texteQuestion = "";
        service.creerQuestion(question);

        Assert.fail("Exception MauvaiseQuestion non lancée");
    }


    @Test(expected = MauvaiseQuestion.class)
    public void ajoutQuestionKOCourte() throws MauvaiseQuestion {
        VDQuestion question = new VDQuestion();
        question.texteQuestion = "aa";
        service.creerQuestion(question);

        Assert.fail("Exception MauvaiseQuestion non lancée");
    }


    @Test(expected = MauvaiseQuestion.class)
    public void ajoutQuestionKOLongue() throws MauvaiseQuestion {
        VDQuestion question = new VDQuestion();
        for (int i = 0 ; i < 256 ; i ++) question.texteQuestion += "aa";
        service.creerQuestion(question);

        Assert.fail("Exception MauvaiseQuestion non lancée");
    }


    @Test(expected = MauvaiseQuestion.class)
    public void ajoutQuestionKOIDFixe() throws MauvaiseQuestion {
        VDQuestion question = new VDQuestion();
        question.texteQuestion = "aaaaaaaaaaaaaaaa";
        question.idQuestion = 5L;
        service.creerQuestion(question);

        Assert.fail("Exception MauvaiseQuestion non lancée");
    }


    @Test
    public void ajoutQuestionOK() throws MauvaiseQuestion {
        VDQuestion question = new VDQuestion();
        question.texteQuestion = "Aimes-tu les brownies au chocolat?";
        service.creerQuestion(question);

        Assert.assertNotNull(question.idQuestion);
    }


    @Test(expected = MauvaiseQuestion.class)
    public void ajoutQuestionKOExiste() throws MauvaiseQuestion {
        VDQuestion question = new VDQuestion();
        VDQuestion question2 = new VDQuestion();

        question.texteQuestion = "Aimes-tu les brownies au chocolat?";
        question2.texteQuestion = "Aimes-tu les BROWNIES au chocolAT?";

        service.creerQuestion(question);
        service.creerQuestion(question2);

        //TODO Ce test va fail tant que vous n'implémenterez pas toutesLesQuestions() dans ServiceImplementation
        Assert.fail("Exception MauvaiseQuestion non lancée");
    }

    //Test pour Vote

    @Test(expected = MauvaisVote.class)
    public void ajoutVoteKOVide() throws MauvaisVote {
        VDVote v = new VDVote();
        v.nomDuVotant = "";
        service.creerVote(v);

        Assert.fail("Exception MauvaisVote non lancée");
    }


    @Test(expected = MauvaisVote.class)
    public void ajoutVoteKOCourte() throws MauvaisVote {
        VDVote v = new VDVote();
        v.nomDuVotant = "aa";
        service.creerVote(v);

        Assert.fail("Exception MauvaiseVote non lancée");
    }


    @Test(expected = MauvaisVote.class)
    public void ajoutVoteKOLongue() throws MauvaisVote {
        VDVote v = new VDVote();
        for (int i = 0 ; i < 256 ; i ++) v.nomDuVotant += "aa";
        service.creerVote(v);

        Assert.fail("Exception MauvaiseVote non lancée");
    }


    @Test(expected = MauvaisVote.class)
    public void ajoutVoteKOIDFixe() throws MauvaisVote {
        VDVote v = new VDVote();
        v.nomDuVotant = "aaaaaaaaaaaaaaaa";
        v.idVote = 5L;
        service.creerVote(v);

        Assert.fail("Exception MauvaiseVote non lancée");
    }


    @Test
    public void ajoutVoteOK() throws MauvaisVote {
        VDVote v = new VDVote();
        v.nomDuVotant = "Aimes-tu les brownies au chocolat?";
        service.creerVote(v);

        Assert.assertNotNull(v.idVote);
    }


    @Test(expected = MauvaisVote.class)
    public void ajoutVoteKOExiste() throws MauvaisVote {
        VDVote v = new VDVote();
        VDVote v1 = new VDVote();

        v.nomDuVotant= "Aimes-tu les brownies au chocolat?";
        v1.nomDuVotant = "Aimes-tu les BROWNIES au chocolAT?";

        service.creerVote(v);
        service.creerVote(v1);

        //TODO Ce test va fail tant que vous n'implémenterez pas toutesLesQuestions() dans ServiceImplementation
        Assert.fail("Exception MauvaiseQuestion non lancée");
    }

    @Test(expected = MauvaisVote.class)
    public void ajoutVoteRatingNegatif() throws MauvaisVote {
        VDVote v = new VDVote();
        v.rating = -1;
        service.creerVote(v);

        Assert.fail("Exception MauvaiseVote non lancée");
    }

    @Test(expected = MauvaisVote.class)
    public void ajoutVoteRatingLong() throws MauvaisVote {
        VDVote v = new VDVote();
        v.rating =6;
        service.creerVote(v);

        Assert.fail("Exception MauvaiseVote non lancée");
    }



    /*
    @After
    public void closeDb() {
        bd.close();
    }
    */

}