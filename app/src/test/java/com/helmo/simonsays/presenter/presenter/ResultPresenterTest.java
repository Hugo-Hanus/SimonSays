package com.helmo.simonsays.presenter.presenter;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


import com.helmo.simonsays.database.repository.ScoreRepository;
import com.helmo.simonsays.game.ResultFragmentVP;
import com.helmo.simonsays.game.ResultPresenter;
import com.helmo.simonsays.model.Score;
import com.helmo.simonsays.presenter.repository.RepositoryUtil;
import com.helmo.simonsays.share.ShareFragmentVP;
import com.helmo.simonsays.share.SharePresenter;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResultPresenterTest {

    @Mock
    private ScoreRepository repository;

    @Mock
    private ShareFragmentVP.View interfaceView;
    @Mock
    private ResultFragmentVP.View interfaceGameView;

    @Before
    public void setup(){
        RepositoryUtil.initScoreRepository(repository);
    }

    @Test
    public void testMockResultonBestScoreAdd(){
        SharePresenter sharePresenter=new SharePresenter(interfaceView);
        ResultPresenter resultPresenter = new ResultPresenter(interfaceGameView);
        MutableLiveData<Score> data = mock(MutableLiveData.class);
        when(repository.getBestScore()).thenReturn(data);
        sharePresenter.loadBestScore();
        ArgumentCaptor<Observer> argumentObs = ArgumentCaptor.forClass(Observer.class);
        verify(data).observeForever(argumentObs.capture());

        Score score = new Score();
        int point=20;
        String pseudo ="TEST";String diff="DIFFICILE";
        score.setPoint(point);
        score.setPseudo(pseudo);
        score.setDifficulty(diff);
        argumentObs.getValue().onChanged(score);
        verify(interfaceView).setBestScore(anyString(),anyString(),anyInt());
        resultPresenter.saveScore(diff,point,pseudo);
        ArgumentCaptor<Score> argumentScore = ArgumentCaptor.forClass(Score.class);
        verify(repository).insertScore(argumentScore.capture());
        assertEquals(point,argumentScore.getValue().getPoint());
        assertEquals(diff,argumentScore.getValue().getDifficulty());
        assertEquals(pseudo,argumentScore.getValue().getPseudo());
    }
}
