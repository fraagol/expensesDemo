package com.aguila.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aguila.model.Expense;
import com.aguila.repository.ExpensesRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expense createExpense(Expense expense) {
		return expensesRepository.save(expense);

	}

	@Override
	public Expense updateExpense(long id, Expense expense) {
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

}
