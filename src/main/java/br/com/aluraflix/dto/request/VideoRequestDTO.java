package br.com.aluraflix.dto.request;

import br.com.aluraflix.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoRequestDTO {

    private String titulo;
    private String descricao;
    private String url;

    public Video toEntity(){
        Video video = new Video();
        video.setTitulo(this.titulo);
        video.setDescricao(this.descricao);
        video.setUrl(this.url);
        return video;
    }

}
