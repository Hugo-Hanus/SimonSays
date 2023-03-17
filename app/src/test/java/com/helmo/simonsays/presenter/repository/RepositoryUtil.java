package com.helmo.simonsays.presenter.repository;

import com.helmo.simonsays.database.repository.ScoreRepository;

public class RepositoryUtil {
    public static void initScoreRepository(ScoreRepository repository) {
        ScoreRepository.instance = repository;
    }
}
