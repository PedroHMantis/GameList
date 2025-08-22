package com.pedrodev.dslist.services;

import com.pedrodev.dslist.dto.GameListDTO;
import com.pedrodev.dslist.entities.Game;
import com.pedrodev.dslist.entities.GameList;
import com.pedrodev.dslist.projections.GameMinProjection;
import com.pedrodev.dslist.repositories.GameListRepository;
import com.pedrodev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {
    @Autowired
    GameListRepository gameListRepository;

    @Autowired
    GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex){
        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);

        list.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

        for (int i = min; i <= max; i++ ){
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }
}
