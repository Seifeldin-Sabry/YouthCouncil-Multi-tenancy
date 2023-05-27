package be.kdg.finalproject.service.media;


import com.google.cloud.storage.*;
import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
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
@Profile ({"prod"})
public class ImageServiceProdImpl implements ImageService {

	private static final String BUCKET_NAME = "youth-council-image-bucket";
	private final String PROJECT_ID = "infra3-seifeldin-sabry";
	private final Storage storage;
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ImageServiceProdImpl.class);
	private final String dir = System.getProperty("user.dir") + "/images";
	private volatile long imageId = 0;

	public ImageServiceProdImpl() {
		storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
	}

	public String uploadObject(String objectName, String filePath) throws IOException {

		BlobId blobId = BlobId.of(BUCKET_NAME, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
		                            .build();
		Blob image = storage.createFrom(blobInfo, Paths.get(filePath));
		return image.getMediaLink();
	}

	// TODO: uncomment if buckets work, or if ur brave enough to fix it
	// PERMISSION DENIED CRAP CAUSE OF BUCKETS, on my life I have the permissions
	@PostConstruct
	public void init() {
		//				clear user directory /images
		File directory = new File(dir);
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
	public List<String> saveImages(List<MultipartFile> images) throws IOException {
		List<String> urls = new ArrayList<>();
		logger.debug("Saving {} images", images.size());
		for (MultipartFile image : images) {
			String originalFilename = image.getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
			String imageName = generateImageName() + extension;
			String file_path = dir + "/" + imageName;
			image.transferTo(Path.of(file_path));
			//			urls.add(file_path);
			// TODO: uncomment if buckets work, or if ur brave enough to fix it
			urls.add(uploadObject(imageName, file_path));
			File file = new File(file_path);
			file.delete();
		}
		logger.debug("Images saved {}", urls);
		return urls;
	}

	private synchronized String generateImageName() {
		return "image_" + UUID.randomUUID() + "_" + imageId++;
	}

}
