package com.aguila.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aguila.repository.Expense;
import com.aguila.repository.ExpensesRepository;

@RestController
@RequestMapping("/expense")
public class ExpensesController {

	@Autowired
	ExpensesRepository expensesRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	Iterable<Expense> getExpenses() {
		return expensesRepository.findAll();
	}
	
	@RequestMapping(value="/{id}" ,method = RequestMethod.GET)
	@ResponseBody
	Expense getExpenseById(@PathVariable final long id) {
		return expensesRepository.findOne(id);
	}
	
	@RequestMapping(method= RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	void createExpense(@RequestBody Expense expense){
		expensesRepository.save(expense);
		System.out.println(expense);
	}
	
	
}
