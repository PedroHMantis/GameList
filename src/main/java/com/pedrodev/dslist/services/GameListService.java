package com.pedrodev.dslist.services;

import com.pedrodev.dslist.dto.GameListDTO;
import com.pedrodev.dslist.entities.Game;
import com.pedrodev.dslist.entities.GameList;
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

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }
}
