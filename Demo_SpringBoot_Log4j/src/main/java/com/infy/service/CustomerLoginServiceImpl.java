package com.infy.service;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.CustomerLoginDTO;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerLoginRepository;

@Service(value="customerLoginService")
public class CustomerLoginServiceImpl implements CustomerLoginService {

	@Autowired
	private CustomerLoginRepository customerLoginRepository;

	@Override
	public String authenticateCustomer(CustomerLoginDTO customerLogin) throws InfyBankException {
		try{
			String toRet = null;
			CustomerLoginDTO customerLoginFromRepository = customerLoginRepository
					.getCustomerLoginByLoginName(customerLogin.getLoginName());
			if (customerLogin.getPassword().equals(customerLoginFromRepository.getPassword())){
				toRet = "SUCCESS";
			}else{
				throw new InfyBankException("Service.WRONG_CREDENTIALS");
			}
			return toRet;
		}catch(InfyBankException exception){
			Logger LOGGER = LogManager.getLogger(this.getClass()); 
			LOGGER.error(exception.getMessage(),exception);
			throw exception;
		}
	}
}
