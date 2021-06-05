package kodlamaio.hrms.core;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.services.FakeMernis;

public class IdentityValidation {
	public static Result isRealPerson(String tcno) {
		FakeMernis mernis = new FakeMernis();
		if(FakeMernis.validate(tcno) == true) {
			return new SuccessResult();
		}
		return new ErrorResult("Türkiye cumhuriyeti kimliği olmak zorunda.");
	}
}