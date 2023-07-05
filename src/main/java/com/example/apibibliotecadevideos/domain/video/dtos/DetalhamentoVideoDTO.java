package com.example.apibibliotecadevideos.domain.video.dtos;

import com.example.apibibliotecadevideos.domain.video.Video;

public record DetalhamentoVideoDTO(Long id, String titulo, String descricao, String url, boolean ativo) {

    public DetalhamentoVideoDTO(Video video){
        this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl(), video.isAtivo());
    }

}
