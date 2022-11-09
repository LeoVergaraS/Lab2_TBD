package com.app.voluntariosbe.services;

import com.app.voluntariosbe.models.Ranking;
import com.app.voluntariosbe.repositories.RankingRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class RankingService {
    private final RankingRepository rankingRepository;
    RankingService(RankingRepository rankingRepository){this.rankingRepository = rankingRepository;}

    @GetMapping("/rankings")
    public List<Ranking> getAllRankings(){ return rankingRepository.getAllRanking();}

    @GetMapping("/rankings/count")
    public String countRankings(){
        int total = rankingRepository.countRanking();
        return String.format("tienes %s rankings!", total );
    }

    @PostMapping("/rankings")
    @ResponseBody
    public Ranking createRanking(@RequestBody Ranking ranking){
        Ranking result = rankingRepository.createRanking(ranking);
        return result;
    }

    @PutMapping("/rankings")
    @ResponseBody
    public Ranking updateTask(@RequestBody Ranking ranking){
        Ranking result = rankingRepository.updateRanking(ranking);
        return result;
    }

    @DeleteMapping("/rankings/{id}")
    public void deleteRanking(@PathVariable int id){
        rankingRepository.deleteRankingById(id);
    }

    @GetMapping("/rankings/{id}")
    public Ranking getRankingById(@PathVariable int id){
        return rankingRepository.getRankingById(id);
    }

}
