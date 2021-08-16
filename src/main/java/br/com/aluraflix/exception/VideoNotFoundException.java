package br.com.aluraflix.exception;

public class VideoNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 1L;

    public VideoNotFoundException(){
        super();
    }

    public VideoNotFoundException(String message){
        super(message);
    }

}
