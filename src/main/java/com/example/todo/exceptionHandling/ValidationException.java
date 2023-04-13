package com.example.todo.exceptionHandling;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.example.todo.dto.ErrorResponseDto;
import com.example.todo.entities.ErrorLoggerEntity;
import com.example.todo.entities.MethodEnum;
import com.example.todo.repository.ErrorloggerRepository;

@RestControllerAdvice
@ControllerAdvice
public class ValidationException {

	@Autowired
	private ErrorloggerRepository errorloggerRepository;

	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	void dataResourceNotFound(DataNotFoundException f) {
		f.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponseDto handleException(final Exception exception,
			HttpServletRequest httpServletRequest) throws IOException {

		ErrorLoggerEntity errorRequest = new ErrorLoggerEntity();
		errorRequest.setBody(httpServletRequest instanceof StandardMultipartHttpServletRequest ? null
				: httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		errorRequest.setHost(InetAddress.getLoopbackAddress().getHostAddress());
		errorRequest.setMessage(exception.getMessage());
		errorRequest.setMethod(Enum.valueOf(MethodEnum.class, httpServletRequest.getMethod()));
		errorRequest.setToken(httpServletRequest.getHeader(httpServletRequest.getHeader(errorRequest.getMessage())));
		errorRequest.setUrl(httpServletRequest.getRequestURI());
		errorloggerRepository.save(errorRequest);
		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("error");
		error.setMsgKey("found");
		return error;

	}

	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) 
	{
		Map<String, String> errorMap = new HashMap<>();
		System.out.println("hello1");
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			System.out.println("hello2");
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	public @ResponseBody ErrorResponseDto handleValidatioinException(final MethodArgumentNotValidException exception) {
//
//		List<String> details = new ArrayList<>();
//
//		for (ObjectError error : exception.getBindingResult().getAllErrors()) {
//
//			details.add(error.getDefaultMessage());
//
//		}
//
//		ErrorResponseDto error = new ErrorResponseDto();
//		error.setMessage(details.get(0).split("\\*", 2)[0]);
//		error.setMsgKey(details.get(0).split("\\*", 2)[1]);
//		return error;
//
//	}
}
