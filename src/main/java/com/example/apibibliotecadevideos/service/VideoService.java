package com.example.apibibliotecadevideos.service;

import com.example.apibibliotecadevideos.domain.video.Video;
import com.example.apibibliotecadevideos.domain.video.VideoRepository;
import com.example.apibibliotecadevideos.domain.video.dtos.CadastroVideoDTO;
import com.example.apibibliotecadevideos.domain.video.dtos.DetalhamentoVideoDTO;
import com.example.apibibliotecadevideos.domain.video.dtos.EditarVideoDTO;
import com.example.apibibliotecadevideos.domain.video.dtos.ListagemVideosDTO;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public DetalhamentoVideoDTO cadastrarVideo(CadastroVideoDTO dados) {

        var video = new Video(dados);

        if (verificarUrl(dados.url())) {
            videoRepository.save(video);
        } else {
            throw new IllegalArgumentException("Verifique se a URL está correta");
        }

        return new DetalhamentoVideoDTO(video);
    }


    public Page<ListagemVideosDTO> listarVideos(Pageable pageable) {

        var page = videoRepository.findAllByAtivoTrue(pageable).map(ListagemVideosDTO::new);
        return page;

    }

    public DetalhamentoVideoDTO detalharVideo(Long id) {

        var video = videoRepository.getReferenceById(id);
        return new DetalhamentoVideoDTO(video);

    }


    public DetalhamentoVideoDTO editarVideo(EditarVideoDTO dados) {

       var video = videoRepository.getReferenceById(dados.id());

       if (dados.titulo() != null){
           video.setTitulo(dados.titulo());
       }
       if (dados.descricao() != null){
           video.setDescricao(dados.descricao());
       }
       if (dados.url() != null){

           if (verificarUrl(dados.url())){
               video.setUrl(dados.url());
           } else {
               throw new NullPointerException("Verifique se a URL está correta");
           }

       }

       return new DetalhamentoVideoDTO(video);

    }

    private boolean verificarUrl(String url){
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }

    public void excluirVideo(Long id) {

        var video = videoRepository.getReferenceById(id);

        if (video != null) {
            video.setAtivo(false);
        } else {
            throw new EntityNotFoundException("Verifique se o ID está correto");
        }

    }
}
