package be.kdg.finalproject.service.media;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImageService {
	List<String> saveImages(List<MultipartFile> images);
}
