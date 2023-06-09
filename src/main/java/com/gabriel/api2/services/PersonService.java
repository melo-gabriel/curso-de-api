package com.gabriel.api2.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.api2.data.vo.v1.PersonVO;
import com.gabriel.api2.exceptions.ResourceNotFoundException;
import com.gabriel.api2.mapper.DozerMapper;
import com.gabriel.api2.model.Person;
import com.gabriel.api2.repositories.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	PersonRepository repository;
	
	public List<PersonVO> findAll() {

		logger.info("buscando todas pessoas!");

		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {

		logger.info("Encontrando uma pessoa!");
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nada encontrado/ id invalido"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}


	public PersonVO create(PersonVO person) {

		logger.info("criando uma pessoa!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	

	public PersonVO update(PersonVO person) {

		logger.info("atualizando uma pessoa!");
		
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Nada encontrado/ id invalido"));
	
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public void delete(Long id) {
		logger.info("deletando uma pessoa");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nada encontrado/ id invalido"));
		repository.delete(entity);
	}


}
