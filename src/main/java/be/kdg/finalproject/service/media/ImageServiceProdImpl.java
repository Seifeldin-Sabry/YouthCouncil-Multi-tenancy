package be.kdg.finalproject.service.media;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Profile ("prod")
public class ImageServiceProdImpl implements ImageService {
	@Override
	public List<String> saveImages(List<MultipartFile> images) {
		return null;
	}
}
