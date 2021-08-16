package br.com.aluraflix.controller;

import br.com.aluraflix.dto.request.VideoRequestDTO;
import br.com.aluraflix.dto.response.VideoResponseDTO;
import br.com.aluraflix.service.VideoService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public ResponseEntity<VideoResponseDTO> criaVideo(@RequestBody VideoRequestDTO videoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.videoService.criaVideo(videoRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<VideoResponseDTO>> exibirVideos(){
        return ResponseEntity.status(HttpStatus.OK).body(this.videoService.exibirVideos());
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoResponseDTO> exibirVideo(@PathVariable Long videoId){
        return ResponseEntity.status(HttpStatus.OK).body(this.videoService.exibirVideo(videoId));
    }

    @DeleteMapping("/{videoId}")
    public ResponseEntity deletaVideo(@PathVariable Long videoId){
        this.videoService.deletaVideo(videoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{videoId}")
    public ResponseEntity<VideoResponseDTO> atualizaVideo(@PathVariable Long videoId, @RequestBody VideoRequestDTO videoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.videoService.atualizaVideo(videoId, videoRequestDTO));
    }

    @PatchMapping(path = "/{videoId}", consumes = "application/json-patch+json")
    public ResponseEntity<VideoResponseDTO> atualizaVideo(@PathVariable Long videoId, @RequestBody JsonPatch jsonPatch){
        return ResponseEntity.status(HttpStatus.OK).body(this.videoService.atualizaVideo(videoId, jsonPatch));
    }

}
