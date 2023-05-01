package be.kdg.finalproject.service.media;


import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile ("prod")
public class ImageServiceProdImpl implements ImageService {

	private static final String BUCKET_NAME = "youth-council-image-bucket";
	private final String PROJECT_ID = "jovial-pod-377109";
	private final Storage storage;
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ImageServiceProdImpl.class);

	public ImageServiceProdImpl() {
		storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
	}

	public String uploadObject(String objectName, String filePath) throws IOException {
		// The ID of your GCP project
		// String projectId = "your-project-id";

		// The ID of your GCS bucket
		// String bucketName = "your-unique-bucket-name";

		// The ID of your GCS object
		// String objectName = "your-object-name";

		// The path to your file to upload
		// String filePath = "path/to/your/file"

		BlobId blobId = BlobId.of(BUCKET_NAME, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

		storage.createFrom(blobInfo, Paths.get(filePath));

		//		return URL no expiration
		return blobInfo.getMediaLink();
	}


	@Override
	public List<String> saveImages(List<MultipartFile> images) throws IOException {
		List<String> urls = new ArrayList<>();
		logger.debug("Saving {} images", images.size());
		for (MultipartFile image : images) {
			logger.debug("Saving image {}", image.getOriginalFilename());
			String file_path = "temp/" + image.getOriginalFilename();
			image.transferTo(Path.of(file_path));
			urls.add(uploadObject(image.getOriginalFilename(), file_path));
		}
		logger.debug("Images saved {}", urls);
		return urls;
	}
}
