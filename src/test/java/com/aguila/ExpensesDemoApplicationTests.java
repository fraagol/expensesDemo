package com.aguila;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.aguila.controllers.ExpensesController;
import com.aguila.model.Expense;
import com.aguila.service.ExpenseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpensesDemoApplicationTests {

	@Autowired
	ExpensesController controller;
	
	@Autowired
	ExpenseService service;
	
	  JdbcTemplate jdbcTemplate;

	    @Autowired
	    public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(service).isNotNull();
	}

	@Test 
	public void createExpense(){
		int countBefore=countRowsInTable();
		Expense expense=newExpense();
		expense=service.createExpense(expense);
		int countAfter=countRowsInTable();	
		assertThat(countAfter).isEqualTo(countBefore+1);
	}
	
	

	@Test 
	public void deleteExpense(){
		Expense expense=newExpense();
		expense=service.createExpense(expense);
		long id= expense.getId();
		assertThat(expense).isNotNull();
		int countBefore=countRowsInTable();
		service.deleteExpense(id);
		expense= service.getExpense(id);
		int countAfter=countRowsInTable();	
		
		assertThat(expense).isNull();
		assertThat(countAfter).isEqualTo(countBefore-1);
	}

	@Test 
	public void updateExpense(){
		Expense expense=newExpense();
		expense=service.createExpense(expense);
		long id= expense.getId();
		assertThat(expense).isNotNull();
		int countBefore=countRowsInTable();
		expense.setDescription("new");
		service.updateExpense(id,expense);
		expense= service.getExpense(id);
		int countAfter=countRowsInTable();	
		assertThat(expense.getDescription()).isEqualTo("new");
		assertThat(countAfter).isEqualTo(countBefore);
	}
	
	private Expense newExpense(){
		Expense expense= new Expense();
		expense.setDate(new Date());
		expense.setDescription("Description");
		expense.setValue(4.5);
		return expense;
	}
	
	private int countRowsInTable() {
	        return JdbcTestUtils.countRowsInTable(this.jdbcTemplate, "expense");
	    }

}
