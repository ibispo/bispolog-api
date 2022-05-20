package com.bispo.bispolog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bispo.bispolog.domain.exception.EntidadeNaoEncontradaException;
import com.bispo.bispolog.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	//  @Autowired
	private MessageSource mensagemSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Problema.Campo> campos = new ArrayList<>();
		ex.getBindingResult().getAllErrors().stream()
			.forEach( oerror -> { 
				
					String nm =  oerror instanceof FieldError ? 
							((FieldError) oerror).getField() : "<field_unknown>";
					String msg = mensagemSource.getMessage(oerror, LocaleContextHolder.getLocale());
					
					campos.add(new Problema.Campo(nm, msg)); 
				});
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(mensagemSource.getMessage("campos.obrigatorios", null, 
			LocaleContextHolder.getLocale()));
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
		
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest webReq) {

		HttpStatus stat = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(stat.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), stat, webReq);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(NegocioException ex, WebRequest webReq) {

		HttpStatus stat = HttpStatus.NOT_FOUND;
		
		Problema problema = new Problema();
		problema.setStatus(stat.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), stat, webReq);
	}
	
	
}
