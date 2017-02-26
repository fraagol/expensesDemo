package com.aguila.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile(value="crudRepository")

@RepositoryRestResource(path="expensesData")
public interface ExpensesRepository extends CrudRepository<Expense, Long>{

}
	