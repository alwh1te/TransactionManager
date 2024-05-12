package org.liptsoft.transactionmanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.liptsoft.transactionmanager.model.*;
import org.liptsoft.transactionmanager.repository.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private MccRepository mccRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;


    private Category category;
    private Mcc mcc;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        mcc = new Mcc();
        mcc.setId(1L);
        mcc.setMcc(1111);

        transaction = new Transaction();
        transaction.setName("Test Transaction");
        transaction.setAmount(100.0);
        transaction.setMonth(5);
        transaction.setId(2L);
        transaction.setCategory(category);
    }

    @Test
    void testAddCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category savedCategory = expenseService.add(category);
        assertEquals(category, savedCategory);
    }

    @Test
    void testAddCategoryWithMcc() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(mccRepository.save(any(Mcc.class))).thenReturn(mcc);

        Category savedCategory = expenseService.add(category, mcc);
        assertEquals(category, savedCategory);
    }

    @Test
    void testSetParentCategory() {
        when(categoryRepository.existsById(1L)).thenReturn(true);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category newCategory = new Category();
        expenseService.setParentCategory(newCategory, 1L);

        assertEquals(category, newCategory.getParentCategory());
    }

    @Test
    void testRemoveCategory() {
        categoryRepository.save(category);
        categoryRepository.deleteById(category.getId());
        assertTrue(categoryRepository.findAll().isEmpty());
    }

    @Test
    void testRemoveCategory_Exists() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        when(categoryRepository.existsById(1L)).thenReturn(true);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        String result = expenseService.removeCategory(1L);

        assertEquals("Category: Test Category removed from:  without category", result);
    }

    @Test
    void testRemoveCategory_NotExists() {
        when(categoryRepository.existsById(1L)).thenReturn(false);

        String result = expenseService.removeCategory(1L);

        assertEquals("There is no category with this ID!", result);
    }

    @Test
    void testAddMcc() {

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(mccRepository.save(any(Mcc.class))).thenReturn(mcc);

        Category savedCategory = expenseService.addMcc(1L, mcc);

        assertEquals(category , savedCategory);
    }

    @Test
    void testAddParentCategory() {
        Category mainCategory = new Category();
        mainCategory.setId(1L);
        mainCategory.setName("Main Category");

        Category subCategory = new Category();
        subCategory.setId(2L);
        subCategory.setName("Sub Category");

        when(categoryRepository.existsById(1L)).thenReturn(true);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(mainCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(subCategory);
        when(mccRepository.findAllByParentCategoryId(1L)).thenReturn(Collections.emptyList());

        String result = expenseService.addParentCategory(1L, subCategory);

        assertEquals("added new group to: " + mainCategory + " []", result);
    }

    @Test
    void testShowCategories() {
        List<Category> categories = Collections.singletonList(new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = expenseService.showCategories();

        assertEquals(categories, result);
    }

    @Test
    void testShowByMonth() {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        when(transactionRepository.findAllByMonth(5)).thenReturn(transactions);

        List<Transaction> result = expenseService.showByMonth(5);

        assertEquals(transactions, result);
    }

    @Test
    void testShowByMonths() {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        when(transactionRepository.findAllByCategoryId(1L)).thenReturn(transactions);

        List<Transaction> result = expenseService.showByMonths(1L);

        assertEquals(transactions, result);
    }

    @Test
    void testShowAllTransactions() {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = expenseService.showAllTransactions();

        assertEquals(transactions, result);
    }

    @Test
    void testShowTransactionsInCategory() {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        when(transactionRepository.findAllByCategoryId(1L)).thenReturn(transactions);

        List<Transaction> result = expenseService.showTransactionsInCategory(1L);

        assertEquals(transactions, result);
    }

    @Test
    void testAddTransaction() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        Transaction transaction = new Transaction();
        transaction.setName("Test Transaction");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = expenseService.addTransaction(1L, transaction);

        assertEquals(transaction, result);
    }

    @Test
    void testRemoveTransaction_Exists() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setName("Test Transaction");

        when(transactionRepository.existsById(1L)).thenReturn(true);
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        String result = expenseService.removeTransaction(1L);

        assertEquals("Transaction: Test Transaction removed", result);
    }

    @Test
    void testRemoveTransaction_NotExists() {
        when(transactionRepository.existsById(1L)).thenReturn(false);

        String result = expenseService.removeTransaction(1L);

        assertEquals("There is no transactions with this ID!", result);
    }
}
