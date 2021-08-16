package br.com.aluraflix.dto.response;

import br.com.aluraflix.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoResponseDTO {
    private String titulo;
    private String descricao;
    private String url;

    public VideoResponseDTO(Video video){
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
    }
}
