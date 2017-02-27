package com.aguila.controllers;

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

import com.aguila.model.Expense;
import com.aguila.service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpensesController {

	@Autowired
	ExpenseService expenseService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	List<Expense> getExpenses() {
		return expenseService.listExpenses();
	}
	
	@RequestMapping(value="/{id}" ,method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Expense getExpenseById(@PathVariable final long id) {
		return expenseService.getExpense(id);
	}
	
	@RequestMapping(method= RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	Expense createExpense(@RequestBody Expense expense){
		expenseService.createExpense(expense);
		System.out.println(expense);
		return expense;
	}
	
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Expense updateExpense(@PathVariable final long id,@RequestBody Expense expense){
		return expenseService.updateExpense(id, expense);
	}
	
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	void deleteExpense(@PathVariable final long id){
		expenseService.deleteExpense(id);
		

	}
	
}
