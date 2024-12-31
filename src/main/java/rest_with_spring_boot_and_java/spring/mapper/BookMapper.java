package rest_with_spring_boot_and_java.spring.mapper;

import rest_with_spring_boot_and_java.spring.model.Books;
import rest_with_spring_boot_and_java.spring.model.Person;
import rest_with_spring_boot_and_java.spring.vo.BookVO;
import rest_with_spring_boot_and_java.spring.vo.PersonVO;

import java.util.List;

public class BookMapper {

    public static BookVO mapToVO(Books book) {
        BookVO vo = Mapper.parseObject(book, BookVO.class);
        vo.setKey(book.getId());
        return vo;
    }

    public static Books mapToEntity(BookVO bookVOVO) {
        Books book = Mapper.parseObject(bookVOVO, Books.class);
        book.setId(bookVOVO.getKey());
        return book;
    }

    public static List<BookVO> mapToVOList(List<Books> booksList) {
        List<BookVO> booksVOList = Mapper.parseListObjects(booksList, BookVO.class);
        for (int i = 0; i < booksList.size(); i++) {
            booksVOList.get(i).setKey(booksList.get(i).getId());
        }

        return booksVOList;
    }

    public static List<Books> mapToEntityList(List<BookVO> booksVOList) {
        List<Books> BooksList = Mapper.parseListObjects(booksVOList, Books.class);

        for (int i = 0; i < booksVOList.size(); i++) {
            BooksList.get(i).setId(booksVOList.get(i).getKey());
        }

        return BooksList;

    }

}
