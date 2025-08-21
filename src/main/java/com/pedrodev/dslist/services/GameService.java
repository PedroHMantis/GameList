package com.pedrodev.dslist.services;


import com.pedrodev.dslist.dto.GameDTO;
import com.pedrodev.dslist.dto.GameMinDTO;
import com.pedrodev.dslist.entities.Game;
import com.pedrodev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//@Componente mesma coisa que Service fazendo a injeção de dependências

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

   @Transactional (readOnly = true)
    public GameDTO findById(Long id){
        Game result = gameRepository.findById(id).get();
        GameDTO dto = new GameDTO(result);

        return dto;
    }

    @Transactional (readOnly = true)
    public List<GameMinDTO> findAll(){
        List<Game> result = gameRepository.findAll();
        List<GameMinDTO> dto =  result.stream().map(x -> new GameMinDTO(x)).toList();

        return dto;

    }
}
