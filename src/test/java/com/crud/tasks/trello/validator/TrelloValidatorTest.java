package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloValidatorTest {
    @Autowired
    private TrelloValidator validator;

    @Test
    public void shouldValidateCardWithTest() {
        //Given
        TrelloCard card = new TrelloCard("Test", "Testing methods", "top", "4");
        //When & Then
        validator.validateCard(card);
    }

    @Test
    public void shouldValidateCardWithoutTest() {
        //Given
        TrelloCard card = new TrelloCard("Plural", "Plural Grammar Rules", "last", "19");
        //When & Then
        validator.validateCard(card);
    }

    @Test
    public void shouldValidateTrelloBoards() {
        //Given
        List<TrelloBoard> boards = new ArrayList<>();
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("1", "course", false));
        boards.add(new TrelloBoard("1", "Science", lists));
        boards.add(new TrelloBoard("2", "Test", lists));
        boards.add(new TrelloBoard("3", "Languages", lists));
        //When
        List<TrelloBoard> validatedBoards = validator.validateTrelloBoards(boards);
        //Then
        assertEquals(2, validatedBoards.size());
    }
}