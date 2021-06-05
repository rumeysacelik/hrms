package kodlamaio.hrms.core.Business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;

@Service
public class CloudinaryManager implements ImageService{
	
	
	Cloudinary cloudinary;
	
	public CloudinaryManager() {
		cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dwygnwqg1", //db adı
				"api_key", "672443461331514", // anahtar
				"api_secret", "Fav_-5HizMMaubxYSvy4QTebYWA")); // gizli anahtar
	}
	
	@Override
	public DataResult<Map> upload(MultipartFile multipartFile) throws IOException{
		// TODO Auto-generated method stub
		Map<String , Object > options = new HashMap<>();
        var allowedFormats = Arrays.asList("png","jpg","jpeg");
        options.put("allowed_formats",allowedFormats);
        File file = convertToFile(multipartFile);
        Map uploader  = null;
        try {
        	uploader = cloudinary.uploader().upload(file, options);
        }
        catch(Exception e) {
        	return new ErrorDataResult<>(e.getMessage()); // hatanın mesajı yakala
        }
        
		return new SuccessDataResult<>(uploader);
	}

	@Override
	public DataResult<Map> delete(String id) throws IOException{
		// TODO Auto-generated method stub
		return null;
	}
	
	 private File convertToFile(MultipartFile multipartFile) throws IOException {
	        File file = new File(multipartFile.getOriginalFilename());
	        FileOutputStream stream = new FileOutputStream(file);
	        stream.write(multipartFile.getBytes());
	        stream.close();

	        return file;
	    }

}
