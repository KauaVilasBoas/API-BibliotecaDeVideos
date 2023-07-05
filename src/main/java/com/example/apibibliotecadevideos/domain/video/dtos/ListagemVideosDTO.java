package com.example.apibibliotecadevideos.domain.video.dtos;

import com.example.apibibliotecadevideos.domain.video.Video;

public record ListagemVideosDTO (Long id, String titulo, String url, boolean ativo){

    public ListagemVideosDTO(Video video){
        this(video.getId(), video.getTitulo(), video.getUrl(), video.isAtivo());
    }

}