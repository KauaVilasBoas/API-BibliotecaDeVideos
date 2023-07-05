package com.example.apibibliotecadevideos.domain.video;

import com.example.apibibliotecadevideos.domain.video.dtos.CadastroVideoDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Video")
@Table(name = "videos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Video {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private boolean ativo;

    public Video (CadastroVideoDTO dados){
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.url = dados.url();
        this.ativo = true;
    }

}
