package com.example.apibibliotecadevideos.controller;

import com.example.apibibliotecadevideos.domain.video.Video;
import com.example.apibibliotecadevideos.domain.video.VideoRepository;
import com.example.apibibliotecadevideos.domain.video.dtos.CadastroVideoDTO;
import com.example.apibibliotecadevideos.domain.video.dtos.DetalhamentoVideoDTO;
import com.example.apibibliotecadevideos.domain.video.dtos.EditarVideoDTO;
import com.example.apibibliotecadevideos.domain.video.dtos.ListagemVideosDTO;
import com.example.apibibliotecadevideos.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService service;

    @PostMapping
    public ResponseEntity<DetalhamentoVideoDTO> cadastrarVideo(@RequestBody @Valid CadastroVideoDTO dados) {

        var detalhamentoVideo= service.cadastrarVideo(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhamentoVideo);

    }

    @GetMapping
    public ResponseEntity<Page<ListagemVideosDTO>> listarVideos(@PageableDefault(size = 20)Pageable pageable){

        var page = service.listarVideos(pageable);
        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoVideoDTO> detalharVideo(@PathVariable Long id){

        var detalhamentoVideo = service.detalharVideo(id);
        return ResponseEntity.ok(detalhamentoVideo);

    }

    @PutMapping
    public ResponseEntity<DetalhamentoVideoDTO> editarVideo(@RequestBody @Valid EditarVideoDTO dados){

        var detalhamentoVideo = service.editarVideo(dados);
        return ResponseEntity.ok(detalhamentoVideo);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirVideo(@PathVariable Long id){
        service.excluirVideo(id);
        return ResponseEntity.noContent().build();
    }

}
