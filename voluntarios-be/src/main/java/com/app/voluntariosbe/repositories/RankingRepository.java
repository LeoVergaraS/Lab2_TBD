package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Ranking;
import java.util.List;

public interface RankingRepository {
    public int countRanking();
    public List<Ranking> getAllRanking();
    public Ranking getRankingById(int id);
    public Ranking createRanking(Ranking r);
    public Ranking updateRanking(Ranking r);
    public void deleteRankingById(int id);
}
