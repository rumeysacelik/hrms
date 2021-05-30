package kodlamaio.hrms.business.abstracts;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerification;


public interface EmailVerificationService {
	void generateCode(EmailVerification code, Integer id);
	Result verify(String verificationCode, Integer id);
}
