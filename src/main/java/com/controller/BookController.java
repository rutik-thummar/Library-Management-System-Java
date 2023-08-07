package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.BookDTO;
import com.service.BookService;

@RestController
@RequestMapping("book")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping(path = "")
	public List<BookDTO> getBookList() {
		return bookService.getBookList();
	}

	@PostMapping(path = "")
	public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) {
		boolean flag = bookService.add(bookDTO);
		if (flag) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Insert Successful...");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Oops, Something Went Wrong. Please Try Again Later...");
		}
	}

	@DeleteMapping(path = "/{id}")
	public void deleteBook(@PathVariable("id") int id) {
		bookService.deleteById(id);
	}
}
