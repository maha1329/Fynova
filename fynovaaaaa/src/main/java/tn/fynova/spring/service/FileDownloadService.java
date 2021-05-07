package tn.fynova.spring.service;

import javax.faces.context.FacesContext;


import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileDownloadService {
	private StreamedContent file;

    public FileDownloadService() {
        file = DefaultStreamedContent.builder()
                .name("downloaded_boromir.jpg")
                .contentType("image/jpg")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/img/plan2.jpg"))
                .build();
    }

    public StreamedContent getFile() {
        return file;
    }

}
