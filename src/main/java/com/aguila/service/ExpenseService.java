package com.aguila.service;

import java.util.List;

import com.aguila.model.Expense;

public interface ExpenseService {

	List<Expense> listExpenses();
	
	Expense getExpense(final long id);
	
	Expense createExpense(final  Expense expense);

	Expense updateExpense(final long id, Expense expense);

	void deleteExpense(final long id);
}
