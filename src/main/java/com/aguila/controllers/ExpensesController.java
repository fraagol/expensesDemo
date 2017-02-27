package com.aguila.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aguila.exception.MaxValueException;
import com.aguila.model.Expense;
import com.aguila.service.ExpenseService;
import com.aguila.util.ErrorMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Expenses rest controller defining the rest API
 * 
 * @author javi
 *
 */

@RestController
@RequestMapping("/expense")
@Api(description = "Rest API for operations with Expenses", produces = "application/json")
public class ExpensesController {

	/** Service to be used */
	@Autowired
	ExpenseService expenseService;

	/** List all expenses*/
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation("Get a list with all Expenses")
	List<Expense> getExpenses() {
		return expenseService.listExpenses();
	}

	/** Get a particular Expense by id*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation("Get an Expense associated to the given id")
	Expense getExpenseById(@PathVariable final long id) {
		return expenseService.getExpense(id);
	}

	/**Create an expense */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	@ApiOperation("Create a new Expense")
	Expense createExpense(@RequestBody Expense expense) {
		expenseService.createExpense(expense);
		return expense;
	}
	
	/** Update an existing Expense*/
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation("Update an existing Expense")
	Expense updateExpense(@PathVariable final long id, @RequestBody Expense expense) {
		return expenseService.updateExpense(id, expense);
	}

	/** Delete an existing Expense*/
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation("Delete an existing Expense")
	void deleteExpense(@PathVariable final long id) {
		expenseService.deleteExpense(id);
	}

	/** Exception handler for HttpMessageNotReadableException */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ErrorMessage exceptionHandler() {
		ErrorMessage error = new ErrorMessage("Problem with the input values format");
		return error;
	}

	/** Exception handler for MaxValueException */
	@ExceptionHandler(MaxValueException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ErrorMessage maxValueHandler() {
		ErrorMessage error = new ErrorMessage("The Value is bigger than 1000");
		return error;
	}

	/** Generic Exception handler*/
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ErrorMessage defaultHandler(Exception ex) {

		ErrorMessage error = new ErrorMessage(ex.getMessage());
		return error;
	}

}
