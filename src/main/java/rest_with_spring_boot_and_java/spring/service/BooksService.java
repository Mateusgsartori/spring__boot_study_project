package rest_with_spring_boot_and_java.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest_with_spring_boot_and_java.spring.controller.BooksController;
import rest_with_spring_boot_and_java.spring.controller.PersonController;
import rest_with_spring_boot_and_java.spring.exceptions.RequiredObjectIsNullException;
import rest_with_spring_boot_and_java.spring.exceptions.ResourceNotFoundExeption;
import rest_with_spring_boot_and_java.spring.mapper.BookMapper;
import rest_with_spring_boot_and_java.spring.model.Books;
import rest_with_spring_boot_and_java.spring.repository.BookRepository;
import rest_with_spring_boot_and_java.spring.vo.BookVO;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.awt.print.Book;
import java.util.List;

@Service
public class BooksService {

    @Autowired
    BookRepository repository;

    public List<BookVO> findAll() {
        var books = BookMapper.mapToVOList(repository.findAll());

        books.forEach(b -> b.add(linkTo(methodOn(BooksController.class).findById(b.getKey())).withSelfRel()));

        return books;
    }

    public BookVO findById(Long id) {
        var vo =  repository.findById(id)
                .map(BookMapper::mapToVO)
                .orElseThrow(() -> new ResourceNotFoundExeption("Book not found!"));
        vo.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO book) {
        if (book == null) {
            throw new RequiredObjectIsNullException();
        }
        var entity = BookMapper.mapToEntity(book);
        var vo = BookMapper.mapToVO(repository.save(entity));
        vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book) {
        if (book == null) {
            throw new RequiredObjectIsNullException();
        }

        var entity =  repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundExeption("No record found for this ID!"));

       entity.setAuthor(book.getAuthor());
       entity.setPrice(book.getPrice());
       entity.setTitle(book.getTitle());
       entity.setLaunchDate(book.getLaunchDate());

       var vo = BookMapper.mapToVO(repository.save(entity));

       vo.add(linkTo(methodOn(BooksController.class).findById(book.getKey())).withSelfRel());

       return vo;

    }

    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("No record found for this ID!"));
        repository.delete(entity);

    }




}
