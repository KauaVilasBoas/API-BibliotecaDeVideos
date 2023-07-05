package com.example.apibibliotecadevideos.domain.video.dtos;

import jakarta.validation.constraints.NotBlank;

public record CadastroVideoDTO(@NotBlank String titulo, @NotBlank String descricao, @NotBlank String url) {
}
