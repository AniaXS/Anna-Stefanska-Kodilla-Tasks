package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService service;

    @Mock
    private TrelloClient client;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        List<TrelloListDto> listDtos1 = new ArrayList<>();
        listDtos1.add(new TrelloListDto("1", "Math", false));
        boardDtos.add(new TrelloBoardDto("1", "Science", listDtos1));
        when(client.getTrelloBoards()).thenReturn(boardDtos);
        //When
        List<TrelloBoardDto> result = service.fetchTrelloBoards();
        //Then
        assertEquals(1, result.size());
        assertEquals("Science", result.get(0).getName());
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("Plural", "Plural Grammar Rules", "last", "13");
        CreatedTrelloCardDto createdCardDto = new CreatedTrelloCardDto("1", "Plural",
                "http://test.com", new BadgesDto());
        when(client.createNewCard(cardDto)).thenReturn(createdCardDto);
        //When
        CreatedTrelloCardDto result = service.createTrelloCard(cardDto);
        //Then
        assertEquals("Plural", result.getName());
    }
}