package com.aguila.repository;

import org.springframework.data.repository.CrudRepository;
import com.aguila.model.Expense;

/**
 * Interface for crud repository creation by Spring
 * @author javi
 *
 */
public interface ExpensesRepository extends CrudRepository<Expense, Long> {

}
