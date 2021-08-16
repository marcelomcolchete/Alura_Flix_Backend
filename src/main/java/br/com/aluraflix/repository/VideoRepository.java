package br.com.aluraflix.repository;

import br.com.aluraflix.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
