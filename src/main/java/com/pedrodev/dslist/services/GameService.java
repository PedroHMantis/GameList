package com.pedrodev.dslist.services;


import com.pedrodev.dslist.dto.GameMinDTO;
import com.pedrodev.dslist.entities.Game;
import com.pedrodev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


//@Componente mesma coisa que Service fazendo a injeção de dependências

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll(){

        List<Game> result = gameRepository.findAll();
        List<GameMinDTO> dto =  result.stream().map(x -> new GameMinDTO(x)).toList();

        return dto;

    }
}
