package com.aguila.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aguila.exception.MaxValueException;
import com.aguila.model.Expense;
import com.aguila.repository.ExpensesRepository;

@Service
/**
 * Implementation of the ExpenseService interface,
 * using the Expenses CrudRepository generated by Spring
 * 
 * @author javi
 *
 */
public class ExpenseServiceImpl implements ExpenseService {

	private static final double MAX_VALUE = 1000;

	@Autowired
	ExpensesRepository expensesRepository;

	/**
	 * Retrieves all expenses from repository and convert the iterable to a List
	 */
	@Override
	public List<Expense> listExpenses() {
		Iterable<Expense> expenses = expensesRepository.findAll();

		List<Expense> expensesList = new ArrayList<>();
		for (Expense expense : expenses) {
			expensesList.add(expense);
		}

		return expensesList;
	}

	/**
	 * Finds a particular expense
	 */
	@Override
	public Expense getExpense(final long id) {
		return expensesRepository.findOne(id);
	}

	/**
	 * Adds a new expense to te repository
	 */
	@Override
	public Expense createExpense(Expense expense) {
		validate(expense);
		return expensesRepository.save(expense);

	}
	/**
	 * Updates an existing Expense
	 */
	@Override
	public Expense updateExpense(final long id, final Expense expense) {
		validate(expense);
		Expense storedExpense = expensesRepository.findOne(id);
		storedExpense.setDate(expense.getDate());
		storedExpense.setDescription(expense.getDescription());
		storedExpense.setValue(expense.getValue());
		expensesRepository.save(storedExpense);
		return storedExpense;
	}
	/**
	 * Removes an expense from the repository
	 */
	@Override
	public void deleteExpense(final long id) {
		expensesRepository.delete(id);

	}
	/**
	 * Validates that the value is smaller than MAX_VALUE
	 * 
	 */
	private void validate(final Expense expense) {
		if (expense.getValue() > MAX_VALUE) {
			throw new MaxValueException();
		}
	}

}
