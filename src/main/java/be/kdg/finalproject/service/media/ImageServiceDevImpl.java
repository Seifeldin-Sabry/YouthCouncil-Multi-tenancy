package be.kdg.finalproject.service.media;

import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Profile ({"dev", "test", "devpsql"})
public class ImageServiceDevImpl implements ImageService {


	private final String PARENT_DIR = "static";
	private final String PATH = new ClassPathResource(String.format("%s/images/image_db/", PARENT_DIR)).getPath()
	                                                                                                   .substring(PARENT_DIR.length());
	private final String FULL_PATH = new ClassPathResource(String.format("%s/images/image_db/", PARENT_DIR)).getPath();
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ImageServiceDevImpl.class);
	private volatile long imageId = 0;

	@PostConstruct
	public void init() {
		File directory = new File(FULL_PATH);
		logger.debug("Directory: {}", directory);
		logger.debug("Directory: {}", FULL_PATH);
		if (!directory.exists()) {
			logger.debug("Directory does not exist, creating directory");
			directory.mkdirs();
			return;
		}
		File[] files = directory.listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			if (file.isFile()) {
				file.delete();
			}
		}
	}

	@Override
	public List<String> saveImages(List<MultipartFile> images) {
		List<String> savedImages = new ArrayList<>();
		images.forEach(image -> {
			logger.debug("Saving image: {}", image.getOriginalFilename());
			String originalFilename = image.getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
			String imageName = generateImageName() + extension;
			Path imagePath = Paths.get(FULL_PATH + imageName);
			logger.debug("Saving image to {}", imagePath);
			try {
				image.transferTo(imagePath);
			} catch (IOException e) {
				logger.debug("Error while saving image");
				throw new RuntimeException(e);
			}
			savedImages.add(imagePath.toString().substring(PARENT_DIR.length()));
		});
		return savedImages;
	}

	private synchronized String generateImageName() {
		return "image_" + UUID.randomUUID() + "_" + imageId++;
	}
}
