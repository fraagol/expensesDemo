package com.aguila.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aguila.exception.MaxValueException;
import com.aguila.model.Expense;
import com.aguila.repository.ExpensesRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private static final double MAX_VALUE=1000;
	@Autowired
	ExpensesRepository expensesRepository;

	@Override
	public List<Expense> listExpenses() {
		Iterable<Expense> expenses = expensesRepository.findAll();

		List<Expense> expensesList = new ArrayList<>();
		for (Expense expense : expenses) {
			expensesList.add(expense);
		}

		return expensesList;
	}

	@Override
	public Expense getExpense(long id) {
		return expensesRepository.findOne(id);
	}

	@Override
	public Expense createExpense(Expense expense) {
		validate(expense);
		return expensesRepository.save(expense);

	}

	@Override
	public Expense updateExpense(long id, Expense expense) {
		validate(expense);
		Expense storedExpense = expensesRepository.findOne(id);
		storedExpense.setDate(expense.getDate());
		storedExpense.setDescription(expense.getDescription());
		storedExpense.setValue(expense.getValue());
		expensesRepository.save(storedExpense);
		return storedExpense;
	}

	@Override
	public void deleteExpense(long id) {
		expensesRepository.delete(id);

	}
	
	private void validate(Expense expense){
		if (expense.getValue()>MAX_VALUE){
			throw new MaxValueException();
		}
	}

}
