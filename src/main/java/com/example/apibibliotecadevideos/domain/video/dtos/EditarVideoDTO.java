package com.example.apibibliotecadevideos.domain.video.dtos;

import jakarta.validation.constraints.NotNull;

public record EditarVideoDTO(@NotNull Long id, String titulo, String descricao, String url) {
}
