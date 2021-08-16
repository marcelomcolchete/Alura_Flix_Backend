package br.com.aluraflix.service;

import br.com.aluraflix.dto.request.VideoRequestDTO;
import br.com.aluraflix.dto.response.VideoResponseDTO;
import br.com.aluraflix.entity.Video;
import br.com.aluraflix.exception.VideoNotFoundException;
import br.com.aluraflix.repository.VideoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    private static final String MSG_VIDEO_NOT_FOUND = "O video não foi encontrado!";

    private final VideoRepository videoRepository;
    private final ObjectMapper objectMapper;

    public VideoService(VideoRepository videoRepository, ObjectMapper objectMapper) {
        this.videoRepository = videoRepository;
        this.objectMapper = objectMapper;
    }

    public VideoResponseDTO criaVideo(VideoRequestDTO videoRequestDTO){
        return new VideoResponseDTO(
                this.videoRepository.save(
                        videoRequestDTO.toEntity()
                )
        );
    }

    public List<VideoResponseDTO> exibirVideos(){
        List<VideoResponseDTO> videoResponseDTOS = new ArrayList<>();
        List<Video> videosEntity = this.videoRepository.findAll();
        videosEntity.forEach(video -> videoResponseDTOS.add(new VideoResponseDTO(video)));
        return videoResponseDTOS;
    }

    public VideoResponseDTO exibirVideo(Long videoId){
        return new VideoResponseDTO(this.videoRepository.findById(videoId).orElseThrow(() -> new VideoNotFoundException(MSG_VIDEO_NOT_FOUND)));
    }

    public void deletaVideo(Long videoId){
        this.videoRepository.deleteById(videoId);
    }

    public VideoResponseDTO atualizaVideo(Long videoId, VideoRequestDTO videoRequestDTO){
        Video videoDb = this.videoRepository.findById(videoId).orElseThrow(() -> new VideoNotFoundException(MSG_VIDEO_NOT_FOUND));
        videoDb.setTitulo(videoRequestDTO.getTitulo());
        videoDb.setDescricao(videoRequestDTO.getDescricao());
        videoDb.setUrl(videoRequestDTO.getUrl());
        return new VideoResponseDTO(this.videoRepository.save(videoDb));
    }

    public VideoResponseDTO atualizaVideo(Long videoId, JsonPatch jsonPatch){
        Video videoDb = this.videoRepository.findById(videoId).orElseThrow(() -> new VideoNotFoundException(MSG_VIDEO_NOT_FOUND));
        try{
            return new VideoResponseDTO(
                    this.videoRepository.save(
                            this.applyPatchToVideo(jsonPatch,videoDb)
                    )
            );
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    private Video applyPatchToVideo(JsonPatch jsonPatch, Video video) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(video, JsonNode.class));
        return objectMapper.treeToValue(patched, Video.class);
    }

}
