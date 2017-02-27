package com.aguila.service;

import java.util.List;

import com.aguila.model.Expense;

public interface ExpenseService {

	List<Expense> listExpenses();
	
	Expense getExpense(long id);
	
	Expense createExpense( Expense expense);

	Expense updateExpense(long id, Expense expense);

	void deleteExpense(long id);
}
