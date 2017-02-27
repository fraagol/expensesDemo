package com.aguila.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aguila.model.Expense;



public interface ExpensesRepository extends CrudRepository<Expense, Long>{

}
	