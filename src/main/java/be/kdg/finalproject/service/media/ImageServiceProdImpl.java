package be.kdg.finalproject.service.media;


import com.google.cloud.storage.*;
import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Profile ({"prod", "devpsql"})
public class ImageServiceProdImpl implements ImageService {

	private static final String BUCKET_NAME = "youth-council-image-bucket";
	private final String PROJECT_ID = "infra3-seifeldin-sabry";
	private final Storage storage;
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ImageServiceProdImpl.class);
	private final String dir = System.getProperty("user.dir");
	private volatile long imageId = 0;

	public ImageServiceProdImpl() {
		storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
	}

	public String uploadObject(String objectName, String filePath) throws IOException {

		BlobId blobId = BlobId.of(BUCKET_NAME, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

		Blob image = storage.createFrom(blobInfo, Paths.get(filePath));
		return image.getMediaLink();
	}


	@Override
	public List<String> saveImages(List<MultipartFile> images) throws IOException {
		List<String> urls = new ArrayList<>();
		logger.debug("Saving {} images", images.size());
		for (MultipartFile image : images) {
			String originalFilename = image.getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
			String imageName = generateImageName() + extension;
			String file_path = dir + "/" + imageName;
			image.transferTo(Path.of(file_path));
			urls.add(uploadObject(imageName, file_path));
		}
		logger.debug("Images saved {}", urls);
		return urls;
	}

	private synchronized String generateImageName() {
		return "image_" + UUID.randomUUID() + "_" + imageId++;
	}

}
